package com.mlinvest.construction;

import com.mlinvest.construction.job.IssuerActionProcessor;
import com.mlinvest.construction.persistence.model.*;
import com.mlinvest.construction.persistence.repository.*;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class IssuerActionProcessorTest {

    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private IssuerRepository issuerRepository;

    @Autowired
    private BidderRepository bidderRepository;

    @Autowired
    private IssuerActionRepository issuerActionRepository;

    @Autowired
    private IssuerActionProcessor issuerActionProcessor;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private TenderResultRepository tenderResultRepository;

    private SavedData initData() {
        var issuer = Issuer.of("Milos");
        var savedIssuer = issuerRepository.save(issuer);
        var tender = Tender.of("Tender description", issuer, TenderStatus.OPEN);
        var savedTender = tenderRepository.save(tender);
        var bidder = Bidder.of("Milos");
        var savedBidder = bidderRepository.save(bidder);
        var offerOne = Offer.of("Offer 1", bidder, tender, OfferStatus.SUBMITTED);
        offerRepository.save(offerOne);
        var offerTwo = Offer.of("Offer 2", bidder, tender, OfferStatus.SUBMITTED);
        offerRepository.save(offerTwo);
        var offerThree = Offer.of("Offer 3", bidder, tender, OfferStatus.SUBMITTED);
        offerRepository.save(offerThree);
        var issuerAction = IssuerAction.of(ActionType.ACCEPT_OFFER, ActionStatus.PENDING, tender, offerThree, Instant.now());
        var savedIssuerAction = issuerActionRepository.save(issuerAction);
        return new SavedData(
                issuer,
                bidder,
                tender,
                offerOne,
                offerTwo,
                offerThree,
                issuerAction
        );
    }

    @Test
    public void onNewIssuerActionSuccessfulUpdates() {
        var savedData = initData();
        issuerActionProcessor.processAcceptOfferAction(savedData.getIssuerAction());

        var finishedTender = tenderRepository.findById(savedData.tender.getId()).get();
        assertEquals(TenderStatus.FINISHED, finishedTender.getStatus());
        var acceptedOffer = offerRepository.findById(savedData.getOfferThree().getId()).get();
        assertEquals(OfferStatus.ACCEPTED, acceptedOffer.getStatus());
        var rejectedOfferOne = offerRepository.findById(savedData.getOfferOne().getId()).get();
        assertEquals(OfferStatus.REJECTED, rejectedOfferOne.getStatus());
        var rejectedOfferTwo = offerRepository.findById(savedData.getOfferTwo().getId()).get();
        assertEquals(OfferStatus.REJECTED, rejectedOfferTwo.getStatus());
        var tenderResult = tenderResultRepository.findById(savedData.getTender().getId()).get();
        assertNotNull(tenderResult);
        assertEquals(acceptedOffer.getId(), tenderResult.getAcceptedOffer().getId());
    }

    @Getter
    class SavedData {
        private final Issuer issuer;
        private final Bidder bidder;
        private final Tender tender;
        private final Offer offerOne;
        private final Offer offerTwo;
        private final Offer offerThree;
        private final IssuerAction issuerAction;

        public SavedData(Issuer issuer, Bidder bidder, Tender tender, Offer offerOne, Offer offerTwo, Offer offerThree, IssuerAction issuerAction) {
            this.issuer = issuer;
            this.bidder = bidder;
            this.tender = tender;
            this.offerOne = offerOne;
            this.offerTwo = offerTwo;
            this.offerThree = offerThree;
            this.issuerAction = issuerAction;
        }
    }
}

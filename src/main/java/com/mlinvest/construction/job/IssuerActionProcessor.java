package com.mlinvest.construction.job;

import com.mlinvest.construction.persistence.model.IssuerAction;
import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.model.TenderResult;
import com.mlinvest.construction.service.IssuerActionService;
import com.mlinvest.construction.service.OfferService;
import com.mlinvest.construction.service.TenderResultService;
import com.mlinvest.construction.service.TenderService;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssuerActionProcessor {

    @Autowired
    private OfferService offerService;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private TenderResultService tenderResultService;

    @Autowired
    private IssuerActionService issuerActionService;

    @Transactional()
    public void processAcceptOfferAction(IssuerAction issuerAction) {
        try {
            var allOffersForTender = offerService.findAllByTender(issuerAction.getTender().getId());
            rejectOffers(getOffersToReject(allOffersForTender, issuerAction.getId()));
            var acceptedOffer = acceptOffer(issuerAction.getOfferToAccept());
            var finishedTender = finishTender(issuerAction.getTender());
            saveTenderResult(finishedTender, acceptedOffer);
            finishIssuerAction(issuerAction);
        } catch (TenderNotFoundException e) {
            setIssuerActionFailed(issuerAction);
        }
    }

    private void setIssuerActionFailed(IssuerAction issuerAction) {
        issuerActionService.setIssuerActionToFailed(issuerAction);
    }

    private void finishIssuerAction(IssuerAction issuerAction) {
        issuerActionService.setIssuerActionToFinished(issuerAction);
    }

    private List<Offer> getOffersToReject(List<Offer> offers, Long offerToAcceptId) {
        if (offers == null || offers.isEmpty()) {
            return Collections.emptyList();
        }

        return offers.stream().filter(o -> !o.getId().equals(offerToAcceptId)).collect(Collectors.toList());
    }

    private TenderResult saveTenderResult(Tender tender, Offer acceptedOffer) {
        return tenderResultService.save(tender, acceptedOffer);
    }

    private Tender finishTender(Tender tender) {
        return tenderService.finishTender(tender);
    }

    private void rejectOffers(List<Offer> offersToReject) {
        offerService.rejectOffers(offersToReject);
    }

    private Offer acceptOffer(Offer offerToAccept) {
        return offerService.acceptOffer(offerToAccept);
    }
}


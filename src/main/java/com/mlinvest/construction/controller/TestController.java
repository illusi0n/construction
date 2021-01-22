package com.mlinvest.construction.controller;

import com.mlinvest.construction.persistence.model.*;
import com.mlinvest.construction.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private IssuerRepository issuerRepository;

    @Autowired
    private BidderRepository bidderRepository;

    @Autowired
    private TenderRepository tenderRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private TenderResultRepository tenderResultRepository;

    @GetMapping("ping")
    public String pingPong() {
        return "pong";
    }

    @GetMapping("test")
    public void test() {
        var newIssuer = Issuer.of("Milos");
        var savedIssuer = issuerRepository.save(newIssuer);

        var newBidder = Bidder.of("Damir");
        var savedBidder = bidderRepository.save(newBidder);

        var newTender = Tender.of("This is a brand new tender", savedIssuer);
        var savedTender = tenderRepository.save(newTender);

        var offer = Offer.of("Real cool offer, 100% guarantee", savedBidder, savedTender);
        var savedOffer = offerRepository.save(offer);

        var tenderResult = TenderResult.of(newTender, offer);
        var savedTenderResult = tenderResultRepository.save(tenderResult);
    }
}

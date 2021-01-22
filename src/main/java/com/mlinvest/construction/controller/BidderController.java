package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.OffersDto;
import com.mlinvest.construction.service.OfferService;
import com.mlinvest.construction.service.exception.BidderNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bidders")
public class BidderController {

    @Autowired
    private OfferService offerService;

    @GetMapping("{bidderId}/offers")
    public ResponseEntity<?> allSubmittedOffers(@PathVariable Long bidderId) throws BidderNotFoundException {
        var allOffers = offerService.findAllByBidder(bidderId);
        return ResponseEntity.ok(OffersDto.of(allOffers));
    }

    @GetMapping("{bidderId}/tender/{tenderId}/offers")
    public ResponseEntity<?> allSubmittedOffersByTender(@PathVariable Long bidderId, @PathVariable Long tenderId)
            throws BidderNotFoundException, TenderNotFoundException {
        var allOffers = offerService.findAllByBidderAndTender(bidderId, tenderId);
        return ResponseEntity.ok(OffersDto.of(allOffers));
    }

}

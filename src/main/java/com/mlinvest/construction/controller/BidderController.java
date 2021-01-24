package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.OffersDto;
import com.mlinvest.construction.controller.response.RestResponder;
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
    public ResponseEntity<?> allSubmittedOffers(@PathVariable Long bidderId) {
        try {
            var allOffersByBidder = offerService.findAllByBidder(bidderId);
            return ResponseEntity.ok(OffersDto.of(allOffersByBidder));
        } catch (BidderNotFoundException e) {
            return RestResponder.createBidderNotFoundResponse(e.getBidderId());
        }
    }

    @GetMapping("{bidderId}/tenders/{tenderId}/offers")
    public ResponseEntity<?> allSubmittedOffersByTender(@PathVariable Long bidderId, @PathVariable Long tenderId) {
        try {
            var allOffersByBidderAndTender = offerService.findAllByBidderAndTender(bidderId, tenderId);
            return ResponseEntity.ok(OffersDto.of(allOffersByBidderAndTender));
        } catch (BidderNotFoundException e) {
            return RestResponder.createBidderNotFoundResponse(e.getBidderId());
        } catch (TenderNotFoundException e) {
            return RestResponder.createTenderNotFoundResponse(e.getTenderId());
        }
    }

}

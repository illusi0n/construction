package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.OffersDto;
import com.mlinvest.construction.service.BidderService;
import com.mlinvest.construction.service.exception.BidderNotFoundException;
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
    private BidderService bidderService;

    @GetMapping("{bidderId}/offers")
    public ResponseEntity<?> allSubmittedOffers(@PathVariable Long bidderId) throws BidderNotFoundException {
        var allOffers = bidderService.findAllOffers(bidderId);
        return ResponseEntity.ok(OffersDto.of(allOffers));
    }

}

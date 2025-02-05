package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.OfferDto;
import com.mlinvest.construction.controller.dto.SaveOfferRequestDto;
import com.mlinvest.construction.controller.response.RestResponder;
import com.mlinvest.construction.service.OfferService;
import com.mlinvest.construction.service.exception.BidderNotFoundException;
import com.mlinvest.construction.service.exception.NewOffersNotSubmittableException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping
    public ResponseEntity<?> saveOffer(@Valid @RequestBody SaveOfferRequestDto saveOfferRequest) {
        try {
            var savedOffer = offerService.save(saveOfferRequest);
            return ResponseEntity.ok(OfferDto.of(savedOffer));
        } catch (TenderNotFoundException e) {
            return RestResponder.createTenderNotFoundResponse(e.getTenderId());
        } catch (BidderNotFoundException e) {
            return RestResponder.createBidderNotFoundResponse(e.getBidderId());
        } catch (NewOffersNotSubmittableException e) {
            return RestResponder.createOfferNotSubmittableResponse();
        }
    }
}

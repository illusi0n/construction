package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.OffersDto;
import com.mlinvest.construction.controller.dto.SaveTenderRequestDto;
import com.mlinvest.construction.controller.dto.TenderDto;
import com.mlinvest.construction.controller.dto.TenderResultDto;
import com.mlinvest.construction.controller.response.RestResponder;
import com.mlinvest.construction.service.OfferService;
import com.mlinvest.construction.service.TenderResultService;
import com.mlinvest.construction.service.TenderService;
import com.mlinvest.construction.service.exception.IssuerNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import com.mlinvest.construction.service.exception.TenderResultNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private TenderResultService tenderResultService;

    @PostMapping
    public ResponseEntity<?> saveTender(@Valid @RequestBody SaveTenderRequestDto saveTenderRequest) {
        try {
            var savedTender = tenderService.save(saveTenderRequest);
            return ResponseEntity.ok(TenderDto.of(savedTender));
        } catch (IssuerNotFoundException e) {
            return RestResponder.createIssuerNotFoundResponse(e.getIssuerId());
        }
    }

    @GetMapping("{tenderId}")
    public ResponseEntity<?> findTender(@PathVariable Long tenderId) {
        try {
            var tender = tenderService.findById(tenderId);
            return ResponseEntity.ok(TenderDto.of(tender));
        } catch (TenderNotFoundException e) {
            return RestResponder.createTenderNotFoundResponse(e.getTenderId());
        }
    }

    @GetMapping("{tenderId}/offers")
    public ResponseEntity<?> findSubmittedOffersByTender(@PathVariable Long tenderId) {
        try {
            var allOffersByTender = offerService.findAllByTender(tenderId);
            return ResponseEntity.ok(OffersDto.of(allOffersByTender));
        } catch (TenderNotFoundException e) {
            return RestResponder.createTenderNotFoundResponse(e.getTenderId());
        }
    }

    @GetMapping("{tenderId}/result")
    public ResponseEntity<?> findTenderResult(@PathVariable Long tenderId) {
        try {
            var tenderResult = tenderResultService.findById(tenderId);
            return ResponseEntity.ok(TenderResultDto.of(tenderResult));
        } catch (TenderResultNotFoundException e) {
            return RestResponder.createTenderResultNotFoundResponse(e.getTenderResultId());
        }
    }
}

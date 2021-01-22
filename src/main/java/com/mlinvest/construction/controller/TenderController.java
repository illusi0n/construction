package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.OffersDto;
import com.mlinvest.construction.controller.dto.SaveTenderRequestDto;
import com.mlinvest.construction.controller.dto.TenderDto;
import com.mlinvest.construction.service.OfferService;
import com.mlinvest.construction.service.TenderService;
import com.mlinvest.construction.service.exception.IssuerNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
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

    @PostMapping
    public ResponseEntity<?> saveTender(@Valid @RequestBody SaveTenderRequestDto saveTenderRequest) throws IssuerNotFoundException {
        var savedTender = tenderService.save(saveTenderRequest);
        return ResponseEntity.ok(TenderDto.of(savedTender));
    }

    @GetMapping("{tenderId}/offers")
    public ResponseEntity<?> allSubmittedOffersByTender(@PathVariable Long tenderId) throws TenderNotFoundException {
        var allOffersByTender = offerService.findAllByTender(tenderId);
        return ResponseEntity.ok(OffersDto.of(allOffersByTender));
    }
}

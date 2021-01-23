package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.IssuerActionDto;
import com.mlinvest.construction.controller.dto.SaveIssuerActionRequestDto;
import com.mlinvest.construction.controller.response.RestResponder;
import com.mlinvest.construction.persistence.model.IssuerAction;
import com.mlinvest.construction.service.IssuerActionService;
import com.mlinvest.construction.service.exception.IssuerActionNotFoundException;
import com.mlinvest.construction.service.exception.OfferNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("actions")
public class IssuerActionController {

    @Autowired
    private IssuerActionService issuerActionService;

    @PostMapping
    public ResponseEntity<?> saveIssuerAction(@Valid @RequestBody SaveIssuerActionRequestDto saveIssuerActionRequest) {
        try {
            var savedIssuerAction = issuerActionService.save(saveIssuerActionRequest);
            return ResponseEntity.ok(IssuerActionDto.of(savedIssuerAction));
        } catch (TenderNotFoundException e) {
            return RestResponder.createTenderNotFoundResponse(e.getTenderId());
        } catch (OfferNotFoundException e) {
            return RestResponder.createOfferNotFoundResponse(e.getOfferId());
        }
    }

    @GetMapping("{issuerActionId}")
    public ResponseEntity<?> findIssuerAction(@PathVariable Long issuerActionId) {
        try {
            var issuerAction = issuerActionService.findById(issuerActionId);
            return ResponseEntity.ok(IssuerActionDto.of(issuerAction));
        } catch (IssuerActionNotFoundException e) {
            return RestResponder.createIssuerActionNotFoundResponse(e.getIssuerActionId());
        }
    }
}

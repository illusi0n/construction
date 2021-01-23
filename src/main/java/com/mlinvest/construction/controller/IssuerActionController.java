package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.IssuerActionDto;
import com.mlinvest.construction.controller.dto.SaveIssuerActionRequestDto;
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
    public ResponseEntity<?> saveIssuerAction(@Valid @RequestBody SaveIssuerActionRequestDto saveIssuerActionRequest) throws OfferNotFoundException, TenderNotFoundException {
        var savedIssuerAction = issuerActionService.save(saveIssuerActionRequest);
        return ResponseEntity.ok(IssuerActionDto.of(savedIssuerAction));
    }

    @GetMapping("{issuerActionId}")
    public ResponseEntity<?> findIssuerAction(@PathVariable Long issuerActionId) throws IssuerActionNotFoundException {
        var issuerAction = issuerActionService.findById(issuerActionId);
        return ResponseEntity.ok(IssuerActionDto.of(issuerAction));
    }
}

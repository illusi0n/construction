package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.TendersDto;
import com.mlinvest.construction.service.TenderService;
import com.mlinvest.construction.service.exception.IssuerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("issuers")
public class IssuerController {

    @Autowired
    private TenderService tenderService;

    @GetMapping("{issuerId}/tenders")
    public ResponseEntity<?> allTendersByIssuer(@PathVariable Long issuerId) throws IssuerNotFoundException {
        var allTendersByIssuer = tenderService.findAllByIssuer(issuerId);
        return ResponseEntity.ok(TendersDto.of(allTendersByIssuer));
    }
}

package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.dto.SaveTenderRequestDto;
import com.mlinvest.construction.controller.dto.TenderDto;
import com.mlinvest.construction.service.TenderService;
import com.mlinvest.construction.service.exception.IssuerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("tenders")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody SaveTenderRequestDto saveTenderRequest) throws IssuerNotFoundException {
        var savedTender = tenderService.save(saveTenderRequest);
        return ResponseEntity.ok(TenderDto.of(savedTender));
    }
}

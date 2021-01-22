package com.mlinvest.construction.service;

import com.mlinvest.construction.controller.dto.SaveTenderRequestDto;
import com.mlinvest.construction.persistence.model.Issuer;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.repository.TenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderService {

    @Autowired
    private TenderRepository tenderRepository;

    public Tender save(SaveTenderRequestDto saveTenderRequest) {
        var newTender = Tender.of(saveTenderRequest.getDescription(), Issuer.of(saveTenderRequest.getIssuerId()));
        return tenderRepository.save(newTender);
    }
}

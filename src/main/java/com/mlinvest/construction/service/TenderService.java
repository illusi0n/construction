package com.mlinvest.construction.service;

import com.mlinvest.construction.controller.dto.SaveTenderRequestDto;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.repository.TenderRepository;
import com.mlinvest.construction.service.exception.IssuerNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderService {

    @Autowired
    private IssuerService issuerService;

    @Autowired
    private TenderRepository tenderRepository;

    public Tender save(SaveTenderRequestDto saveTenderRequest) throws IssuerNotFoundException {
        var issuer = issuerService.findById(saveTenderRequest.getIssuerId());
        var newTender = Tender.of(saveTenderRequest.getDescription(), issuer);
        return tenderRepository.save(newTender);
    }

    public Tender findById(Long tenderId) throws TenderNotFoundException {
        var findResult = tenderRepository.findById(tenderId);
        if (findResult.isEmpty()) {
            throw new TenderNotFoundException(tenderId);
        }

        return findResult.get();
    }
}

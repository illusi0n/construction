package com.mlinvest.construction.service;

import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.model.TenderResult;
import com.mlinvest.construction.persistence.repository.TenderResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenderResultService {

    @Autowired
    private TenderResultRepository tenderResultRepository;

    public TenderResult save(Tender tender, Offer acceptedOffer) {
        var newTenderResult = TenderResult.of(tender, acceptedOffer);
        return tenderResultRepository.save(newTenderResult);
    }
}

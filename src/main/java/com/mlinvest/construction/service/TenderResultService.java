package com.mlinvest.construction.service;

import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.model.Tender;
import com.mlinvest.construction.persistence.model.TenderResult;
import com.mlinvest.construction.persistence.repository.TenderResultRepository;
import com.mlinvest.construction.service.exception.TenderResultNotFoundException;
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

    public TenderResult findById(Long tenderResultId) throws TenderResultNotFoundException {
        var findResult = tenderResultRepository.findById(tenderResultId);
        if (findResult.isEmpty()) {
            throw new TenderResultNotFoundException(tenderResultId);
        }

        return findResult.get();
    }
}

package com.mlinvest.construction.service;

import com.mlinvest.construction.persistence.model.Bidder;
import com.mlinvest.construction.persistence.repository.BidderRepository;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidderService {

    @Autowired
    private BidderRepository bidderRepository;

    public Bidder findById(Long tenderId) throws TenderNotFoundException {
        var findResult = bidderRepository.findById(tenderId);
        if (findResult.isEmpty()) {
            throw new TenderNotFoundException(tenderId);
        }

        return findResult.get();
    }
}

package com.mlinvest.construction.service;

import com.mlinvest.construction.persistence.model.Bidder;
import com.mlinvest.construction.persistence.repository.BidderRepository;
import com.mlinvest.construction.service.exception.BidderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidderService {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private BidderRepository bidderRepository;

    public Bidder findById(Long bidderId) throws BidderNotFoundException {
        var findResult = bidderRepository.findById(bidderId);
        if (findResult.isEmpty()) {
            throw new BidderNotFoundException(bidderId);
        }

        return findResult.get();
    }
}

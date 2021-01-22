package com.mlinvest.construction.service;

import com.mlinvest.construction.controller.dto.SaveOfferRequestDto;
import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.repository.OfferRepository;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private BidderService bidderService;

    @Autowired
    private OfferRepository offerRepository;

    public Offer save(SaveOfferRequestDto saveOfferRequest) throws TenderNotFoundException {
        var tender = tenderService.findById(saveOfferRequest.getTenderId());
        var bidder = bidderService.findById(saveOfferRequest.getBidderId());
        var newOffer = Offer.of(saveOfferRequest.getDescription(), bidder, tender);
        return offerRepository.save(newOffer);
    }
}

package com.mlinvest.construction.service;

import com.mlinvest.construction.controller.dto.SaveOfferRequestDto;
import com.mlinvest.construction.persistence.model.Offer;
import com.mlinvest.construction.persistence.repository.OfferRepository;
import com.mlinvest.construction.service.exception.BidderNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private BidderService bidderService;

    @Autowired
    private OfferRepository offerRepository;

    public Offer save(SaveOfferRequestDto saveOfferRequest) throws TenderNotFoundException, BidderNotFoundException {
        var tender = tenderService.findById(saveOfferRequest.getTenderId());
        var bidder = bidderService.findById(saveOfferRequest.getBidderId());
        var newOffer = Offer.of(saveOfferRequest.getDescription(), bidder, tender);
        return offerRepository.save(newOffer);
    }

    public List<Offer> findAllByBidder(Long bidderId) throws BidderNotFoundException {
        var bidder = bidderService.findById(bidderId);
        return offerRepository.findByBidder(bidder);
    }

    public List<Offer> findAllByBidderAndTender(Long bidderId, Long tenderId) throws BidderNotFoundException, TenderNotFoundException {
        var bidder = bidderService.findById(bidderId);
        var tender = tenderService.findById(tenderId);
        return offerRepository.findByBidderAndTender(bidder, tender);
    }

    public List<Offer> findAllByTender(Long tenderId) throws TenderNotFoundException {
        var tender = tenderService.findById(tenderId);
        return offerRepository.findByTender(tender);
    }
}

package com.mlinvest.construction.controller;

import com.mlinvest.construction.persistence.model.Bidder;
import com.mlinvest.construction.persistence.model.Issuer;
import com.mlinvest.construction.persistence.repository.BidderRepository;
import com.mlinvest.construction.persistence.repository.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private IssuerRepository issuerRepository;

    @Autowired
    private BidderRepository bidderRepository;

    @GetMapping("ping")
    public String pingPong() {
        return "pong";
    }

    @GetMapping("test")
    public void test() {
        var newIssuer = Issuer.of("Milos");
        var savedIssuer = issuerRepository.save(newIssuer);

        var newBidder = Bidder.of("Damir");
        var savedBidder = bidderRepository.save(newBidder);
    }
}

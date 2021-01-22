package com.mlinvest.construction;

import com.mlinvest.construction.persistence.model.Bidder;
import com.mlinvest.construction.persistence.model.Issuer;
import com.mlinvest.construction.persistence.repository.BidderRepository;
import com.mlinvest.construction.persistence.repository.IssuerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer implements CommandLineRunner {

    @Autowired
    private IssuerRepository issuerRepository;

    @Autowired
    private BidderRepository bidderRepository;

    @Override
    public void run(String... args) throws Exception {
        issuerRepository.save(Issuer.of("Milos"));
        issuerRepository.save(Issuer.of("Jelena"));
        bidderRepository.save(Bidder.of("Damir"));
        bidderRepository.save(Bidder.of("Marko"));
        bidderRepository.save(Bidder.of("Neil_"));
    }
}

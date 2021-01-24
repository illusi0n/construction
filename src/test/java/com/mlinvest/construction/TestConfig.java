package com.mlinvest.construction;

import com.mlinvest.construction.job.IssuerActionProcessor;
import com.mlinvest.construction.service.*;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public IssuerActionProcessor issuerActionProcessor() {
        return new IssuerActionProcessor();
    }

    @Bean
    public TenderService tenderService() {
        return new TenderService();
    }

    @Bean
    public OfferService offerService() {
        return new OfferService();
    }

    @Bean
    public TenderResultService tenderResultService() {
        return new TenderResultService();
    }

    @Bean
    public IssuerActionService issuerActionService() {
        return new IssuerActionService();
    }

    @Bean
    public IssuerService issuerService() {
        return new IssuerService();
    }

    @Bean
    public BidderService bidderService() {
        return new BidderService();
    }
}

package com.mlinvest.construction.job;

import com.mlinvest.construction.persistence.model.IssuerAction;
import com.mlinvest.construction.service.IssuerActionService;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProcessIssuerActionJob {

    @Autowired
    private IssuerActionProcessor issuerActionProcessor;

    @Autowired
    private IssuerActionService issuerActionService;

    // 1 second in millis
    private static final int POLLING_RATE_IN_MS = 1000;

    @Scheduled(fixedRate = POLLING_RATE_IN_MS)
    public void processIssuerAction() throws TenderNotFoundException {
        // 1. get oldest issuer action which has a pending status
        Optional<IssuerAction> findOldestPendingIssuerActionResult = issuerActionService.findOldestPendingIssuerAction();
        if (findOldestPendingIssuerActionResult.isEmpty()) {
            // nothing to do
            return;
        }

        processIssuerAction(findOldestPendingIssuerActionResult.get());
    }

    private void processIssuerAction(IssuerAction issuerAction) throws TenderNotFoundException {
        switch (issuerAction.getType()) {
            case ACCEPT_OFFER -> issuerActionProcessor.processAcceptOfferAction(issuerAction);
        }
    }
}

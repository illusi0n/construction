package com.mlinvest.construction.service;

import com.mlinvest.construction.controller.dto.SaveIssuerActionRequestDto;
import com.mlinvest.construction.persistence.model.ActionStatus;
import com.mlinvest.construction.persistence.model.IssuerAction;
import com.mlinvest.construction.persistence.repository.IssuerActionRepository;
import com.mlinvest.construction.service.exception.OfferNotFoundException;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Optional;

@Service
public class IssuerActionService {

    @Autowired
    private IssuerActionRepository issuerActionRepository;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private OfferService offerService;

    public IssuerAction save(@Valid @RequestBody SaveIssuerActionRequestDto saveIssuerActionRequest) throws TenderNotFoundException, OfferNotFoundException {
        var tender = tenderService.findById(saveIssuerActionRequest.getTenderId());
        var offerToAccept = offerService.findById(saveIssuerActionRequest.getOfferToAcceptId());
        var newIssuerAction = IssuerAction.of(
                saveIssuerActionRequest.getActionType(),
                ActionStatus.PENDING,
                tender,
                offerToAccept,
                Instant.now()
        );
        return issuerActionRepository.save(newIssuerAction);
    }

    public Optional<IssuerAction> findOldestPendingIssuerAction() {
        return issuerActionRepository.findFirstByStatusOrderByCreatedAtAsc(ActionStatus.PENDING);
    }

    public void setIssuerActionToFinished(IssuerAction issuerAction) {
        issuerAction.setStatus(ActionStatus.FINISHED);
        issuerActionRepository.save(issuerAction);
    }

    public void setIssuerActionToFailed(IssuerAction issuerAction) {
        issuerAction.setStatus(ActionStatus.FAILED);
        issuerActionRepository.save(issuerAction);
    }
}

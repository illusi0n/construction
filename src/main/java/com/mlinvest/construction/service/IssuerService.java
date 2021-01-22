package com.mlinvest.construction.service;

import com.mlinvest.construction.persistence.model.Issuer;
import com.mlinvest.construction.persistence.repository.IssuerRepository;
import com.mlinvest.construction.service.exception.IssuerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuerService {

    @Autowired
    private IssuerRepository issuerRepository;

    public Issuer findById(Long issuerId) throws IssuerNotFoundException {
        var findResult = issuerRepository.findById(issuerId);
        if (findResult.isEmpty()) {
            throw new IssuerNotFoundException(issuerId);
        }

        return findResult.get();
    }
}

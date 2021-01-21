package com.mlinvest.construction.controller;

import com.mlinvest.construction.persistence.model.Issuer;
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

    @GetMapping("ping")
    public String pingPong() {
        return "pong";
    }

    @GetMapping("test")
    public String test() {
        var newIssuer = Issuer.of("Milos");
        var savedIssuer = issuerRepository.save(newIssuer);
        return "pong";
    }
}

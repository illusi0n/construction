package com.mlinvest.construction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlinvest.construction.persistence.model.*;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

public class TestUtil {

    public static ResultActions matchBidder(String prefix, Bidder bidder, ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(jsonPath(prefix + ".id", is(bidder.getId().intValue())));

    }

    public static ResultActions matchOffer(String prefix, Offer offer, ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(jsonPath(prefix + ".id", is(offer.getId().intValue())))
                .andExpect(jsonPath( prefix + ".description", is(offer.getDescription())))
                .andExpect(jsonPath(prefix + ".status", is(offer.getStatus().toString())));
    }

    public static ResultActions matchAction(String prefix, IssuerAction issuerAction, ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(jsonPath(prefix + ".id", is(issuerAction.getId().intValue())))
                .andExpect(jsonPath( prefix + ".type", is(issuerAction.getType().toString())))
                .andExpect(jsonPath(prefix + ".status", is(issuerAction.getStatus().toString())))
                .andExpect(jsonPath(prefix + ".createdAt", is(issuerAction.getCreatedAt().toString())));
    }

    public static ResultActions matchTender(String prefix, Tender tender, ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(jsonPath(prefix + ".id", is(tender.getId().intValue())))
                .andExpect(jsonPath(prefix + ".description", is(tender.getDescription())))
                .andExpect(jsonPath(prefix + ".status", is(tender.getStatus().toString())));
    }

    public static ResultActions matchIssuer(String prefix, Issuer issuer, ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(jsonPath(prefix + ".id", is(issuer.getId().intValue())));
    }

    public static ResultActions matchTenderResult(String prefix, TenderResult tenderResult, ResultActions resultActions) throws Exception {
        return resultActions
                .andExpect(jsonPath(prefix + ".id", is(tenderResult.getId().intValue())));

    }

    public static String serializeAsJson(Object object) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}

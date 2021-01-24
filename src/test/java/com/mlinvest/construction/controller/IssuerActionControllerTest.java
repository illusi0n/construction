package com.mlinvest.construction.controller;

import com.mlinvest.construction.controller.IssuerActionController;
import com.mlinvest.construction.controller.dto.SaveIssuerActionRequestDto;
import com.mlinvest.construction.dto.*;
import com.mlinvest.construction.persistence.model.ActionStatus;
import com.mlinvest.construction.persistence.model.ActionType;
import com.mlinvest.construction.persistence.model.OfferStatus;
import com.mlinvest.construction.persistence.model.TenderStatus;
import com.mlinvest.construction.service.IssuerActionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static com.mlinvest.construction.TestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IssuerActionController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class IssuerActionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssuerActionService issuerActionService;

    @Test
    public void saveIssuerActionValidDataSuccess() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);
        var dummyIssuerAction = DummyIssuerAction.of(ActionType.ACCEPT_OFFER, ActionStatus.PENDING, dummyTender,
                dummyOffer, Instant.now(), 1L);
        var saveIssuerActionRequest = SaveIssuerActionRequestDto.of(ActionType.ACCEPT_OFFER, dummyTender.getId(), dummyOffer.getId());

        when(issuerActionService.save(any())).thenReturn(dummyIssuerAction);

        var resultActions = this.mockMvc.perform(post("/actions/")
                .contentType(APPLICATION_JSON)
                .content(serializeAsJson(saveIssuerActionRequest)))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("actions-save-ok"));
        matchAction("$", dummyIssuerAction, resultActions);
        matchTender("$.tender", dummyTender, resultActions);
        matchOffer("$.offerToAccept", dummyOffer, resultActions);
    }

    @Test
    public void returnExistingTender() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);
        var dummyIssuerAction = DummyIssuerAction.of(ActionType.ACCEPT_OFFER, ActionStatus.PENDING, dummyTender,
                dummyOffer, Instant.now(), 1L);

        when(issuerActionService.findById(dummyTender.getId())).thenReturn(dummyIssuerAction);

        var resultActions = this.mockMvc.perform(get("/actions/" + dummyIssuerAction.getId()).contentType(APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("actions-get-one-ok"));
        matchAction("$", dummyIssuerAction, resultActions);
        matchTender("$.tender", dummyTender, resultActions);
        matchOffer("$.offerToAccept", dummyOffer, resultActions);
    }

}

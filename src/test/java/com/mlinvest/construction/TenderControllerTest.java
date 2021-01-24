package com.mlinvest.construction;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mlinvest.construction.controller.TenderController;
import com.mlinvest.construction.controller.dto.SaveTenderRequestDto;
import com.mlinvest.construction.controller.response.ErrorCodes;
import com.mlinvest.construction.dto.*;
import com.mlinvest.construction.persistence.model.OfferStatus;
import com.mlinvest.construction.persistence.model.TenderStatus;
import com.mlinvest.construction.service.OfferService;
import com.mlinvest.construction.service.TenderResultService;
import com.mlinvest.construction.service.TenderService;
import com.mlinvest.construction.service.exception.TenderNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.mlinvest.construction.TestUtil.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TenderController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class TenderControllerTest {

    @MockBean
    private TenderService tenderService;

    @MockBean
    private OfferService offerService;

    @MockBean
    private TenderResultService tenderResultService;

    @Autowired
    private MockMvc mockMvc;

    private static String serializeAsJson(SaveTenderRequestDto saveTenderRequest) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(saveTenderRequest);
    }

    @Test
    public void returnExistingTender() throws Exception {
        var dummyExistingTender = 1L;
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(dummyExistingTender, "Test desc", dummyIssuer, TenderStatus.OPEN);

        when(tenderService.findById(dummyExistingTender)).thenReturn(dummyTender);
        var resultActions = this.mockMvc.perform(get("/tenders/" + dummyExistingTender).contentType(APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("tenders-get-one-ok"));
        matchTender("$", dummyTender, resultActions);
        matchIssuer("$.issuer", dummyIssuer, resultActions);
    }

    @Test
    public void nonExistingTenderReturnNotFound() throws Exception {

        when(tenderService.findById(1L)).thenThrow(new TenderNotFoundException(1L));

        this.mockMvc.perform(get("/tenders/1").contentType(APPLICATION_JSON)).andDo(print()).andExpect(status().isNotFound())
                .andDo(document("tenders-get-one-notfound"))
                .andExpect(jsonPath("errorCode", is(ErrorCodes.TENDER_NOT_FOUND)));
    }

    @Test
    public void saveTenderValidDataSuccess() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var saveRequest = SaveTenderRequestDto.of(1L, "Test desc");
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        when(tenderService.save(any()))
                .thenReturn(dummyTender);

        var resultActions = this.mockMvc.perform(post("/tenders/")
                .contentType(APPLICATION_JSON)
                .content(serializeAsJson(saveRequest)))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("tenders-save-ok"));
        matchTender("$", dummyTender, resultActions);
        matchIssuer("$.issuer", dummyIssuer, resultActions);
    }

    @Test
    public void findAllOffersForExistingTenderAndSubmittedOffers() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);

        when(offerService.findAllByTender(dummyTender.getId())).thenReturn(List.of(dummyOffer));

        var resultActions  = this.mockMvc
                .perform(
                    get("/tenders/" + dummyTender.getId() + "/offers").
                    contentType(APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("tenders-find-offers-ok"));
        matchOffer("$.offers.[0]", dummyOffer, resultActions);
        matchTender("$.offers.[0].tender", dummyTender, resultActions);
        matchBidder("$.offers.[0].bidder", dummyBidder, resultActions);
    }

    @Test
    public void findTenderResultForFinishedTender() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);
        var dummyTenderResult = DummyTenderResult.of(dummyTender, dummyOffer, 1L);

        when(tenderResultService.findById(dummyTenderResult.getId())).thenReturn(dummyTenderResult);

        var resultActions  = this.mockMvc
                .perform(
                        get("/tenders/" + dummyTender.getId() + "/result").
                                contentType(APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("tenders-result-tender-finished-ok"));
        matchTenderResult("$", dummyTenderResult, resultActions);
        matchOffer("$.acceptedOffer", dummyOffer, resultActions);
        matchTender("$.tender", dummyTender, resultActions);
    }
}

package com.mlinvest.construction;

import com.mlinvest.construction.controller.BidderController;
import com.mlinvest.construction.dto.DummyBidder;
import com.mlinvest.construction.dto.DummyIssuer;
import com.mlinvest.construction.dto.DummyOffer;
import com.mlinvest.construction.dto.DummyTender;
import com.mlinvest.construction.persistence.model.OfferStatus;
import com.mlinvest.construction.persistence.model.TenderStatus;
import com.mlinvest.construction.service.OfferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.mlinvest.construction.TestUtil.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BidderController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class BidderControllerTest {

    @MockBean
    private OfferService offerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllSubmittedOffersForExistingBidder() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);
        var dummyOfferTwo = DummyOffer.of("Dummy offer Two", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 2L);

        when(offerService.findAllByBidder(dummyBidder.getId())).thenReturn(List.of(dummyOffer, dummyOfferTwo));

        var resultActions = this.mockMvc
                .perform(
                        get("/bidders/" + dummyBidder.getId() + "/offers").
                                contentType(APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("bidders-find-offers-ok"));
        matchOffer("$.offers.[0]", dummyOffer, resultActions);
        matchTender("$.offers.[0].tender", dummyTender, resultActions);
        matchBidder("$.offers.[0].bidder", dummyBidder, resultActions);
        matchOffer("$.offers.[1]", dummyOfferTwo, resultActions);
        matchTender("$.offers.[1].tender", dummyTender, resultActions);
        matchBidder("$.offers.[1].bidder", dummyBidder, resultActions);
    }

    @Test
    public void findAllSubmittedOffersForExistingBidderAndTender() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);

        when(offerService.findAllByBidderAndTender(dummyBidder.getId(), dummyTender.getId())).thenReturn(List.of(dummyOffer));

        var resultActions = this.mockMvc
                .perform(
                        get("/bidders/" + dummyBidder.getId() + "/tenders/" + dummyTender.getId() + "/offers").
                                contentType(APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("bidders-find-offers-for-tender-ok"));
        matchOffer("$.offers.[0]", dummyOffer, resultActions);
        matchTender("$.offers.[0].tender", dummyTender, resultActions);
        matchBidder("$.offers.[0].bidder", dummyBidder, resultActions);
    }
}

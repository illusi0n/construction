package com.mlinvest.construction;

import com.mlinvest.construction.controller.OfferController;
import com.mlinvest.construction.controller.dto.SaveOfferRequestDto;
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

import static com.mlinvest.construction.TestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OfferController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class OfferControllerTest {

    @MockBean
    private OfferService offerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void saveOfferValidDataSuccess() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);
        var dummyBidder = DummyBidder.of("Milos", 1L);
        var dummyOffer = DummyOffer.of("Dummy offer", dummyBidder, dummyTender, OfferStatus.SUBMITTED, 1L);
        var saveOfferRequest = SaveOfferRequestDto.of("Test offer desc", dummyBidder.getId(), dummyTender.getId());

        when(offerService.save(any())).thenReturn(dummyOffer);

        var resultActions = this.mockMvc.perform(post("/offers/")
                .contentType(APPLICATION_JSON)
                .content(serializeAsJson(saveOfferRequest)))
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("offers-save-ok"));
        matchOffer("$", dummyOffer, resultActions);
        matchBidder("$.bidder", dummyBidder, resultActions);
        matchTender("$.tender", dummyTender, resultActions);
    }

}

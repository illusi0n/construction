package com.mlinvest.construction;

import com.mlinvest.construction.controller.IssuerController;
import com.mlinvest.construction.controller.TenderController;
import com.mlinvest.construction.dto.DummyIssuer;
import com.mlinvest.construction.dto.DummyTender;
import com.mlinvest.construction.persistence.model.TenderStatus;
import com.mlinvest.construction.service.TenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.mlinvest.construction.TestUtil.matchIssuer;
import static com.mlinvest.construction.TestUtil.matchTender;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IssuerController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class IssuerControllerTest {

    @MockBean
    private TenderService tenderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistingTendersForExistingIssuer() throws Exception {
        var dummyIssuer = DummyIssuer.of("Milos", 1L);
        var dummyTender = DummyTender.of(1L, "Test desc", dummyIssuer, TenderStatus.OPEN);

        when(tenderService.findAllByIssuer(dummyIssuer.getId())).thenReturn(List.of(dummyTender));

        var resultActions = this.mockMvc
                .perform(
                        get("/issuers/" + dummyIssuer.getId() + "/tenders").
                                contentType(APPLICATION_JSON)
                )
                .andDo(print()).andExpect(status().isOk())
                .andDo(document("issuers-find-tenders-ok"));
        matchTender("$.tenders.[0]", dummyTender, resultActions);
        matchIssuer("$.tenders.[0].issuer", dummyIssuer, resultActions);
    }
}

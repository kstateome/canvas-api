package edu.ksu.canvas.tests.accountReportSummary;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AccountReportSummaryImpl;
import edu.ksu.canvas.interfaces.AccountReportSummaryReader;
import edu.ksu.canvas.model.report.AccountReportSummary;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountReportSummaryUTest extends CanvasTestBase {

    @Autowired
    private FakeRestClient fakeRestClient;
    private AccountReportSummaryReader accountReportSummaryReader;

    private static final String ROOT_ACCOUNT_ID = "1";

    @BeforeEach
    void setupReader() {
        accountReportSummaryReader = new AccountReportSummaryImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testListAvailableReportsForSisExportCsv() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + ROOT_ACCOUNT_ID + "/reports", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/accountReportSummary/AccountReportSummary.json");
        List<AccountReportSummary> summaryList = accountReportSummaryReader.listAvailableReports(ROOT_ACCOUNT_ID);

        assertEquals(1, summaryList.size());

        AccountReportSummary summary = summaryList.get(0);
        assertNotNull(summary.getLastRun());
        assertNotNull(summary.getParameters());
        assertNotNull(summary.getTitle());
        assertNotNull(summary.getReport());
        assertEquals(15, summary.getParameters().size());
        for(Map.Entry<String, Object> parameter : summary.getParameters().entrySet()) {
            // Each parameter entry here is a map of maps.
            assertTrue(((Map) parameter.getValue()).containsKey("description"));
            assertTrue(((Map) parameter.getValue()).containsKey("required"));
        }
    }

}

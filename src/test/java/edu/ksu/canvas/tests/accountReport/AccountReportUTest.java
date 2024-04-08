package edu.ksu.canvas.tests.accountReport;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AccountReportImpl;
import edu.ksu.canvas.interfaces.AccountReportReader;
import edu.ksu.canvas.model.report.AccountReport;
import edu.ksu.canvas.model.report.AccountReportAttachment;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountReportUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private AccountReportReader accountReportReader;

    private static final String ROOT_ACCOUNT_ID = "1";

    @BeforeEach
    void setupReader() {
        accountReportReader = new AccountReportImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testListReportsNoLastRun() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + ROOT_ACCOUNT_ID + "/reports/sis_export_csv", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/accountReport/AccountReportListing.json");
        List<AccountReport> accountReports = accountReportReader.listReports(ROOT_ACCOUNT_ID, "sis_export_csv");
        assertEquals(3, accountReports.size());

        // First record is a cancellation object
        AccountReport firstReport = accountReports.get(0);
        assertEquals("error", firstReport.getStatus());

        assertTrue(firstReport.getParameters().containsKey("extra_text"));
        assertNotNull(firstReport.getParameters().get("extra_text"));

        assertNull(firstReport.getCurrentLine());
        assertNull(firstReport.getFileUrl());

        // Second record is a report that's still running
        AccountReport secondReport = accountReports.get(1);
        assertEquals("running", secondReport.getStatus());

        assertTrue(secondReport.getParameters().containsKey("enrollment_term_id"));
        assertTrue(secondReport.getParameters().containsKey("enrollments"));
        assertTrue(secondReport.getParameters().containsKey("extra_text"));

        assertNotNull(secondReport.getParameters().get("enrollment_term_id"));
        assertNotNull(secondReport.getParameters().get("enrollments"));
        assertNotNull(secondReport.getParameters().get("extra_text"));

        assertNotNull(secondReport.getFileUrl());
        assertNotNull(secondReport.getCurrentLine());

        assertNull(secondReport.getAttachment());

        // Third report is a completed report
        AccountReport thirdReport = accountReports.get(2);
        assertEquals("complete", thirdReport.getStatus());

        assertTrue(thirdReport.getParameters().containsKey("enrollment_term_id"));
        assertTrue(thirdReport.getParameters().containsKey("users"));
        assertTrue(thirdReport.getParameters().containsKey("extra_text"));

        assertNotNull(thirdReport.getParameters().get("enrollment_term_id"));
        assertNotNull(thirdReport.getParameters().get("users"));
        assertNotNull(thirdReport.getParameters().get("extra_text"));

        assertNotNull(thirdReport.getFileUrl());
        assertNotNull(thirdReport.getCurrentLine());

        assertNotNull(thirdReport.getAttachment());

        // Inspecting information about the attachment itself
        AccountReportAttachment attachment = thirdReport.getAttachment();

        assertNotNull(attachment.getUrl());
        assertNotNull(attachment.getContentType());
        assertNotNull(attachment.getSize());

    }

    @Test
    void testReportStatus() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + ROOT_ACCOUNT_ID + "/reports/sis_export_csv/2", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/accountReport/SingleAccountReportListing.json");
        Optional<AccountReport> accountReport = accountReportReader.reportStatus(ROOT_ACCOUNT_ID, "sis_export_csv", 2L);

        assertTrue(accountReport.isPresent());
    }

    @Test
    void testListReportsReportAsEmptyString() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            accountReportReader.listReports(ROOT_ACCOUNT_ID, "");
        });

    }

    @Test
    void testListReportsReportAsNullValue() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            accountReportReader.listReports(ROOT_ACCOUNT_ID, null);
        });
    }
}

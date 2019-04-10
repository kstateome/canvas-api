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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountReportUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private AccountReportReader accountReportReader;

    private static final String ROOT_ACCOUNT_ID = "1";

    @Before
    public void setupReader() {
        accountReportReader = new AccountReportImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testListReportsNoLastRun() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + ROOT_ACCOUNT_ID + "/reports/sis_export_csv", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/accountReport/AccountReportListing.json");
        List<AccountReport> accountReports = accountReportReader.listReports(ROOT_ACCOUNT_ID, "sis_export_csv");
        Assert.assertEquals(3, accountReports.size());

        // First record is a cancellation object
        AccountReport firstReport = accountReports.get(0);
        Assert.assertEquals("error", firstReport.getStatus());

        Assert.assertTrue(firstReport.getParameters().containsKey("extra_text"));
        Assert.assertNotNull(firstReport.getParameters().get("extra_text"));

        Assert.assertNull(firstReport.getCurrentLine());
        Assert.assertNull(firstReport.getFileUrl());

        // Second record is a report that's still running
        AccountReport secondReport = accountReports.get(1);
        Assert.assertEquals("running", secondReport.getStatus());

        Assert.assertTrue(secondReport.getParameters().containsKey("enrollment_term_id"));
        Assert.assertTrue(secondReport.getParameters().containsKey("enrollments"));
        Assert.assertTrue(secondReport.getParameters().containsKey("extra_text"));

        Assert.assertNotNull(secondReport.getParameters().get("enrollment_term_id"));
        Assert.assertNotNull(secondReport.getParameters().get("enrollments"));
        Assert.assertNotNull(secondReport.getParameters().get("extra_text"));

        Assert.assertNotNull(secondReport.getFileUrl());
        Assert.assertNotNull(secondReport.getCurrentLine());

        Assert.assertNull(secondReport.getAttachment());

        // Third report is a completed report
        AccountReport thirdReport = accountReports.get(2);
        Assert.assertEquals("complete", thirdReport.getStatus());

        Assert.assertTrue(thirdReport.getParameters().containsKey("enrollment_term_id"));
        Assert.assertTrue(thirdReport.getParameters().containsKey("users"));
        Assert.assertTrue(thirdReport.getParameters().containsKey("extra_text"));

        Assert.assertNotNull(thirdReport.getParameters().get("enrollment_term_id"));
        Assert.assertNotNull(thirdReport.getParameters().get("users"));
        Assert.assertNotNull(thirdReport.getParameters().get("extra_text"));

        Assert.assertNotNull(thirdReport.getFileUrl());
        Assert.assertNotNull(thirdReport.getCurrentLine());

        Assert.assertNotNull(thirdReport.getAttachment());

        // Inspecting information about the attachment itself
        AccountReportAttachment attachment = thirdReport.getAttachment();

        Assert.assertNotNull(attachment.getUrl());
        Assert.assertNotNull(attachment.getContentType());
        Assert.assertNotNull(attachment.getSize());

    }

    @Test
    public void testReportStatus() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + ROOT_ACCOUNT_ID + "/reports/sis_export_csv/2", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/accountReport/SingleAccountReportListing.json");
        Optional<AccountReport> accountReport = accountReportReader.reportStatus(ROOT_ACCOUNT_ID, "sis_export_csv", 2);

        Assert.assertTrue(accountReport.isPresent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListReportsReportAsEmptyString() throws Exception {
        accountReportReader.listReports(ROOT_ACCOUNT_ID, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListReportsReportAsNullValue() throws Exception {
        accountReportReader.listReports(ROOT_ACCOUNT_ID, null);
    }
}

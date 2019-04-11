package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.report.AccountReportSummary;

public interface AccountReportSummaryReader extends CanvasReader<AccountReportSummary, AccountReportSummaryReader> {

    /**
     * Generate a list of reports available for the specified account.
     * See <a href="https://canvas.instructure.com/doc/api/account_reports.html#method.account_reports.index">the Canvas API documentation</a> for more details.
     * @param accountId the account ID to run the reports against
     * @return a summary list of reports, showing the most recent runs if any exist
     * @throws IOException When there is an error communicating with Canvas
     */
    List<AccountReportSummary> listAvailableReports(String accountId) throws IOException;
}

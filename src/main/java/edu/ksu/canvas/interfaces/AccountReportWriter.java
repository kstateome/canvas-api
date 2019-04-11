package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.report.AccountReport;
import edu.ksu.canvas.requestOptions.AccountReportOptions;

public interface AccountReportWriter extends CanvasWriter<AccountReport, AccountReportWriter> {

    /**
     * Start a new report, specifying a set of options to run with.
     * <p>
     * Note that the options used vary depending on the report you wish to start.
     * @param options options that should be passed to start the report
     * @return an <code>Optional&lt;AccountReport&gt;</code> which contains a reference to the generated report
     * @throws IOException When there is an error communicating with Canvas
     * @see <a href="https://canvas.instructure.com/doc/api/account_reports.html">Account Report API documentation</a>
     * @see AccountReportOptions
     */
    Optional<AccountReport> startReport(AccountReportOptions options) throws IOException;


    /**
     * Delete a report.  This can also be used to stop the run of a report.
     * @param accountId the account id used to generate the report
     * @param report the name of the report
     * @param reportId the ID of the report
     * @return an <code>Optional&lt;AccountReport&gt;</code> which contains a reference to the deleted report
     * @throws IOException When there is an error communicating with Canvas
     * @see <a href="https://canvas.instructure.com/doc/api/account_reports.html#method.account_reports.destroy">Account Report API documentation</a>
     */
    Optional<AccountReport> deleteReport(String accountId, String report, Integer reportId) throws IOException;
}

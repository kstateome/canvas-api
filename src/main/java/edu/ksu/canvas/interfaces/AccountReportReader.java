package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.report.AccountReport;

public interface AccountReportReader extends CanvasReader<AccountReport, AccountReportReader> {

    /**
     * Generate a list of previously run reports for a given report type.
     * @param accountId the account ID to run the reports against
     * @param report the specific name of the report (e.g. sis_export_csv)
     * @return a list of run reports specified by the report ID
     * @throws IOException When there is an error communicating with Canvas
     */
    List<AccountReport> listReports(String accountId, String report) throws IOException;

    /**
     * Pull back the status of a given report.
     * @param accountId the account ID to run the reports against
     * @param report the specific name of the report (e.g. sis_export_csv)
     * @param id the ID of the running report
     * @return a report outlining the status of its run, or a summary of its completed run
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AccountReport> reportStatus(String accountId, String report, Integer id) throws IOException;
}

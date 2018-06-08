package edu.ksu.canvas.model.report;

import java.io.Serializable;
import java.util.Map;

import edu.ksu.canvas.model.BaseCanvasModel;

/**
 * A class to represent the top level report entity from Canvas.
 * See <a href="https://canvas.instructure.com/doc/api/account_reports.html#method.account_reports.available_reports">Account Reports#List Available Reports</a> for more information.
 */
public class AccountReportSummary extends BaseCanvasModel implements Serializable {

    private String title;
    private Map<String, Object> parameters;
    private String report;
    private AccountReport lastRun;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public AccountReport getLastRun() {
        return lastRun;
    }

    public void setLastRun(AccountReport lastRun) {
        this.lastRun = lastRun;
    }
}

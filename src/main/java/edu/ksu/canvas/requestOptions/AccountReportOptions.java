package edu.ksu.canvas.requestOptions;

/**
 * Account report form options to create a new report.
 * <p>
 * Note that these options vary depending on the type of report you wish to generate,
 * and not all options are required.  However, you may require at <i>least</i> one option
 * to ensure that your report is created successfully.
 * <p>
 * See <a href="https://canvas.instructure.com/doc/api/account_reports.html#method.account_reports.create">Start a Report</a>
 * for more details.
 */
public class AccountReportOptions extends BaseOptions {
    private String reportType;
    private String accountId;

    public AccountReportOptions(String reportType, String accountId) {
        this.reportType = reportType;
        this.accountId = accountId;
    }

    public String getReportType() {
        return reportType;
    }

    public String getAccountId() {
        return accountId;
    }

    public AccountReportOptions enrollmentTermId(String parameter) {
        addSingleItem("parameters[enrollment_term_id]", parameter);
        return this;
    }

    public AccountReportOptions users(String parameter) {
        addSingleItem("parameters[users]", parameter);
        return this;
    }

    public AccountReportOptions accounts(String parameter) {
        addSingleItem("parameters[accounts]", parameter);
        return this;
    }

    public AccountReportOptions terms(String parameter) {
        addSingleItem("parameters[terms]", parameter);
        return this;
    }

    public AccountReportOptions courses(String parameter) {
        addSingleItem("parameters[courses]", parameter);
        return this;
    }

    public AccountReportOptions sections(String parameter) {
        addSingleItem("parameters[sections]", parameter);
        return this;
    }

    public AccountReportOptions enrollments(String parameter) {
        addSingleItem("parameters[enrollments]", parameter);
        return this;
    }

    public AccountReportOptions groups(String parameter) {
        addSingleItem("parameters[sections]", parameter);
        return this;
    }

    public AccountReportOptions groupCategories(String parameter) {
        addSingleItem("parameters[group_categories]", parameter);
        return this;
    }

    public AccountReportOptions groupMembership(String parameter) {
        addSingleItem("parameters[group_membership]", parameter);
        return this;
    }

    public AccountReportOptions xlist(String parameter) {
        addSingleItem("parameters[xlist]", parameter);
        return this;
    }


    public AccountReportOptions userObservers(String parameter) {
        addSingleItem("parameters[user_observers]", parameter);
        return this;
    }


    public AccountReportOptions admins(String parameter) {
        addSingleItem("parameters[admins]", parameter);
        return this;
    }


    public AccountReportOptions createdBySis(String parameter) {
        addSingleItem("parameters[created_by_sis]", parameter);
        return this;
    }


    public AccountReportOptions includeDeleted(String parameter) {
        addSingleItem("parameters[include_deleted]", parameter);
        return this;
    }
}

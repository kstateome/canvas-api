package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AccountReportReader;
import edu.ksu.canvas.interfaces.AccountReportWriter;
import edu.ksu.canvas.model.report.AccountReport;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.AccountReportOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountReportImpl extends BaseImpl<AccountReport, AccountReportReader, AccountReportWriter> implements AccountReportReader, AccountReportWriter {
    private static final Logger LOG = Logger.getLogger(AccountReport.class);

    public AccountReportImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public List<AccountReport> listReports(String accountId, String report) throws IOException {
        LOG.debug("Retrieving information about all " + report + " reports for account " + accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports/" + report, Collections.emptyMap());

        return getListFromCanvas(url);
    }

    @Override
    public Optional<AccountReport> reportStatus(String accountId, String report, Integer id) throws IOException {
        LOG.debug("Retrieving information about report ID " + id + " of report " + report + " for account " + accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports/" + report + "/" + id, Collections.emptyMap());

        return getFromCanvas(url);
    }

    @Override
    public Optional<AccountReport> startReport(AccountReportOptions options) throws IOException {
        LOG.debug("Starting new report of type " + options.getReportType() + " for account " + options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/reports/" + options.getReportType(), Collections.emptyMap());
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(objectType(), response);
    }

    @Override
    public Optional<AccountReport> deleteReport(String accountId, String report, Integer reportId) throws IOException {
        LOG.debug("Deleting report ID " + reportId + " for report " + report + " on behalf of account " + accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports/" + report + "/" + reportId, Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, Collections.emptyMap());
        return responseParser.parseToObject(objectType(), response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AccountReport>>() {
        }.getType();
    }

    @Override
    protected Class<AccountReport> objectType() {
        return AccountReport.class;
    }
}

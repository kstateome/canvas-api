package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AccountReportSummaryReader;
import edu.ksu.canvas.interfaces.AccountReportSummaryWriter;
import edu.ksu.canvas.model.report.AccountReportSummary;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class AccountReportSummaryImpl extends BaseImpl<AccountReportSummary, AccountReportSummaryReader, AccountReportSummaryWriter> implements AccountReportSummaryReader, AccountReportSummaryWriter {
    private static final Logger LOG = LoggerFactory.getLogger(AccountReportSummaryImpl.class);

    public AccountReportSummaryImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                                    int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public List<AccountReportSummary> listAvailableReports(String accountId) throws IOException {
        LOG.debug("Retrieving available reports for account " + accountId);
        String url = buildCanvasUrl("accounts/" + accountId + "/reports", Collections.emptyMap());

        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AccountReportSummary>>() {
        }.getType();
    }

    @Override
    protected Class<AccountReportSummary> objectType() {
        return AccountReportSummary.class;
    }
}

package edu.ksu.canvas.impl;


import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.GetSubAccountsOptions;
import edu.ksu.canvas.requestOptions.ListAccountOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountImpl extends BaseImpl<Account, AccountReader, CanvasWriter> implements AccountReader {
    private static final Logger LOG = Logger.getLogger(AccountImpl.class);

    public AccountImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                       int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<Account> getSingleAccount(String accountId) throws IOException {
        LOG.debug("getting account " + accountId);
        String url = buildCanvasUrl("accounts/" + accountId, Collections.emptyMap());

        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(Account.class, response);
    }

    @Override
    public List<Account> listAccounts(ListAccountOptions options) throws IOException {
        LOG.debug("Listing accounts for current user ");
        String url = buildCanvasUrl("accounts", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Account> getSubAccounts(GetSubAccountsOptions options) throws IOException {
        LOG.debug("Getting list of sub-accounts for account " + options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/sub_accounts", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<Account> listAccountsForCourseAdmins() throws IOException {
        LOG.debug("Getting list of accounts by admin course enrollments");
        String url = buildCanvasUrl("course_accounts", Collections.emptyMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Account>>(){}.getType();
    }

    @Override
    protected Class<Account> objectType() {
        return Account.class;
    }
}

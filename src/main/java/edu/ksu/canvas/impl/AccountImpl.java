package edu.ksu.canvas.impl;


import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountImpl extends BaseImpl<Account, AccountReader, CanvasWriter> implements AccountReader {
    private static final Logger LOG = Logger.getLogger(AccountImpl.class);

    public AccountImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize);
    }

    @Override
    public Optional<Account> getSingleAccount(String accountId) throws IOException {
        LOG.debug("getting account " + accountId);
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "accounts/" + accountId, Collections.EMPTY_MAP);
        LOG.debug("Final URL of API call: " + url);

        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(Account.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Account>>(){}.getType();
    }

    @Override
    protected Class objectType() {
        return Account.class;
    }
}

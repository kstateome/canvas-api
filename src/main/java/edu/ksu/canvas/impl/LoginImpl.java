package edu.ksu.canvas.impl;

import static java.util.Collections.emptyMap;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.Login;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import edu.ksu.canvas.requestOptions.UpdateLoginOptions;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class LoginImpl extends BaseImpl<Login, LoginReader, LoginWriter> implements LoginReader, LoginWriter {
    private static final Logger LOG = Logger.getLogger(LoginImpl.class);

    public LoginImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                     int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<Login> getLoginForUser(String userId) throws IOException {
        LOG.debug("Retrieving logins for user id " + userId);
        String url = buildCanvasUrl("users/" + userId + "/logins", emptyMap());

        return getListFromCanvas(url);
    }

    @Override
    public Optional<Login> updateLogin(UpdateLoginOptions options) throws IOException {
        LOG.debug("Updating login belonging to account ID " + options.getAccountId() + " for user associated with login " + options.getLoginId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/logins/" + options.getLoginId(), emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, options.getOptionsMap());
        return responseParser.parseToObject(Login.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Login>>() {
        }.getType();
    }

    @Override
    protected Class<Login> objectType() {
        return Login.class;
    }
}

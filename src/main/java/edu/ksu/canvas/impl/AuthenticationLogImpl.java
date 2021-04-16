package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.AuthenticationLogReader;
import edu.ksu.canvas.model.AuthenticationLog;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AuthenticationLogImpl extends BaseImpl<AuthenticationLog, AuthenticationLogReader, CanvasWriter> implements AuthenticationLogReader {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationLogImpl.class);

    public AuthenticationLogImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<AuthenticationLog> getAuthenticationLogForAccount(String accountId) throws IOException {
        String url = buildCanvasUrl("/audit/authentication/accounts/" + accountId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(AuthenticationLog.class, response);
    }

    @Override
    public Optional<AuthenticationLog> getAuthenticationLogForLogin(String loginId) throws IOException {
        String url = buildCanvasUrl("/audit/authentication/logins/" + loginId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(AuthenticationLog.class, response);
    }

    @Override
    public Optional<AuthenticationLog> getAuthenticationLogForUser(String userId) throws IOException {
        String url = buildCanvasUrl("/audit/authentication/users/" + userId, Collections.emptyMap());
        Response response = canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(AuthenticationLog.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<AuthenticationLog>>(){}.getType();
    }

    @Override
    protected Class<AuthenticationLog> objectType() {
        return AuthenticationLog.class;
    }

}

package edu.ksu.canvas.impl;

import static java.util.Collections.emptyMap;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.Login;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class LoginImpl extends BaseImpl<Login, LoginReader, LoginWriter> implements LoginReader {
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
    protected Type listType() {
        return new TypeToken<List<Login>>() {
        }.getType();
    }

    @Override
    protected Class<Login> objectType() {
        return Login.class;
    }
}

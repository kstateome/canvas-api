package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;

import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.Role;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListRolesOptions;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class RoleImpl extends BaseImpl<Role, RoleReader, RoleWriter> implements RoleReader, RoleWriter {
    private static final Logger LOG = Logger.getLogger(RoleImpl.class);

    public RoleImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                    int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public List<Role> listRoles(ListRolesOptions options) throws IOException {
        LOG.debug("Retrieving roles for account " + options.getAccountId());
        String url = buildCanvasUrl("accounts/" + options.getAccountId() + "/roles", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Role>>(){}.getType();
    }

    @Override
    protected Class<Role> objectType() {
        return Role.class;
    }

}

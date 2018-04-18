package edu.ksu.canvas.tests.roles;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.RoleImpl;
import edu.ksu.canvas.interfaces.RoleReader;
import edu.ksu.canvas.model.Role;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListRolesOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;

public class RolesUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private RoleReader roleReader;
    
    private static final String ROOT_ACCOUNT_ID = "1";
    
    @Before
    public void setupReader() {
         roleReader = new RoleImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                 SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testListRoles() throws Exception {
        ListRolesOptions options = new ListRolesOptions(ROOT_ACCOUNT_ID);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"accounts/" + options.getAccountId() + "/roles", options.getOptionsMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/role/Role.json");
        List<Role> rolesList = roleReader.listRoles(options);
        Assert.assertNotNull(rolesList);
        Assert.assertEquals(1, rolesList.size());
    }

}

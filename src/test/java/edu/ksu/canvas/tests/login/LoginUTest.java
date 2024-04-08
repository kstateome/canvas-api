package edu.ksu.canvas.tests.login;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.LoginImpl;
import edu.ksu.canvas.interfaces.LoginReader;
import edu.ksu.canvas.interfaces.LoginWriter;
import edu.ksu.canvas.model.Login;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private LoginReader loginReader;
    private LoginWriter loginWriter;

    private static final String someAccountId = "1";
    private static final String someUserId = "1111";
    private static final String someLoginId = "1235";
    private static final String updatedId = "testUpdated@email.com";

    @BeforeEach
    void setupData() {
        loginReader = new LoginImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
        loginWriter = new LoginImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testGetLoginForUser() throws Exception {
        Response notErroredResponse = new Response();
        notErroredResponse.setErrorHappened(false);
        notErroredResponse.setResponseCode(200);
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "users/" + someUserId + "/logins", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/login/GetLoginForUser.json");

        List<Login> logins = loginReader.getLoginForUser(someUserId);
        assertEquals(2, logins.size());
        assertTrue(logins.stream().map(Login::getUniqueId).filter("test@email.com"::equals).findFirst().isPresent());
        assertTrue(logins.stream().map(Login::getUniqueId).filter("test2@email.com"::equals).findFirst().isPresent());
    }

    @Test
    void testUpdateLogin() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + someAccountId + "/logins/" + someLoginId, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/login/UpdateLogin.json");

        Login login = new Login();
        login.setId(someLoginId);
        login.setAccountId(someAccountId);
        login.setUniqueId(updatedId);
        login.setSisUserId(updatedId);
        Optional<Login> loginResponse = loginWriter.updateLogin(login);
        assertEquals(updatedId, loginResponse.get().getUniqueId());
        assertEquals(updatedId, loginResponse.get().getSisUserId());
    }

    @Test
    void testDeleteLogin() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "users/" + someUserId + "/logins/" + someLoginId, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/login/DeleteLogin.json");

        Login login = new Login();
        login.setId(someLoginId);
        login.setUserId(someUserId);
        login.setUniqueId(updatedId);
        login.setSisUserId(updatedId);
        Optional<Login> loginResponse = loginWriter.deleteLogin(login);
        assertEquals(someLoginId, loginResponse.get().getId());
    }
}

package edu.ksu.canvas.tests.authenticationLog;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AuthenticationLogImpl;
import edu.ksu.canvas.interfaces.AuthenticationLogReader;
import edu.ksu.canvas.model.AuthenticationLog;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AuthenticationLogUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private AuthenticationLogReader authenticationLogReader;

    private static final String ARBITRARY_ACCOUNT_ID = "1";
    private static final String ARBITRARY_USER_ID = "165";
    private static final String ARBITRARY_LOGIN_ID = "170";

    @Before
    public void setupData() {
        authenticationLogReader = new AuthenticationLogImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testGetAuthenticationLogForUser() throws IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "audit/authentication/users/" + ARBITRARY_USER_ID, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/authenticationLog/AuthenticationLogForUser.json");

        Optional<AuthenticationLog> response = authenticationLogReader.getAuthenticationLogForUser(ARBITRARY_USER_ID);
        AuthenticationLog al = response.get();
        assertNotNull(al);
        assertNotNull(al.getEvents());
        assertEquals(al.getEvents().size(), 3);
        assertNotNull(al.getEvents().stream().map(AuthenticationLog.AuthenticationEvent::getEventType).filter(AuthenticationLog.EventType.login::equals).findFirst());
        assertNotNull(al.getEvents().stream().map(AuthenticationLog.AuthenticationEvent::getEventType).filter(AuthenticationLog.EventType.logout::equals).findFirst());
        AuthenticationLog.AuthenticationEvent ae = al.getEvents().get(0);
        assertNotNull(ae);
        assertNotNull(ae.getLinks());
        assertEquals(String.valueOf(ae.getLinks().getAccount()), ARBITRARY_ACCOUNT_ID);
        assertEquals(String.valueOf(ae.getLinks().getLogin()), ARBITRARY_LOGIN_ID);
        assertEquals(String.valueOf(ae.getLinks().getUser()), ARBITRARY_USER_ID);
    }

    @Test
    public void testGetAuthenticationLogForAccount() throws IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "audit/authentication/accounts/" + ARBITRARY_ACCOUNT_ID, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/authenticationLog/AuthenticationLogForAccount.json");

        Optional<AuthenticationLog> response = authenticationLogReader.getAuthenticationLogForAccount(ARBITRARY_ACCOUNT_ID);
        AuthenticationLog al = response.get();
        assertNotNull(al);
        assertNotNull(al.getEvents());
        assertEquals(al.getEvents().size(), 3);
        assertNotNull(al.getEvents().stream().map(AuthenticationLog.AuthenticationEvent::getEventType).filter(AuthenticationLog.EventType.login::equals).findFirst());
        assertNotNull(al.getEvents().stream().map(AuthenticationLog.AuthenticationEvent::getEventType).filter(AuthenticationLog.EventType.logout::equals).findFirst());
        AuthenticationLog.AuthenticationEvent ae = al.getEvents().get(0);
        assertNotNull(ae);
        assertNotNull(ae.getLinks());
        assertEquals(String.valueOf(ae.getLinks().getAccount()), ARBITRARY_ACCOUNT_ID);
        assertEquals(String.valueOf(ae.getLinks().getLogin()), ARBITRARY_LOGIN_ID);
        assertEquals(String.valueOf(ae.getLinks().getUser()), ARBITRARY_USER_ID);
    }

    @Test
    public void testGetAuthenticationLogForLogin() throws IOException {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "audit/authentication/logins/" + ARBITRARY_LOGIN_ID, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/authenticationLog/AuthenticationLogForLogin.json");

        Optional<AuthenticationLog> response = authenticationLogReader.getAuthenticationLogForLogin(ARBITRARY_LOGIN_ID);
        AuthenticationLog al = response.get();
        assertNotNull(al);
        assertNotNull(al.getEvents());
        assertEquals(al.getEvents().size(), 3);
        assertNotNull(al.getEvents().stream().map(AuthenticationLog.AuthenticationEvent::getEventType).filter(AuthenticationLog.EventType.login::equals).findFirst());
        assertNotNull(al.getEvents().stream().map(AuthenticationLog.AuthenticationEvent::getEventType).filter(AuthenticationLog.EventType.logout::equals).findFirst());
        AuthenticationLog.AuthenticationEvent ae = al.getEvents().get(0);
        assertNotNull(ae);
        assertEquals(String.valueOf(ae.getLinks().getAccount()), ARBITRARY_ACCOUNT_ID);
        assertEquals(String.valueOf(ae.getLinks().getLogin()), ARBITRARY_LOGIN_ID);
        assertEquals(String.valueOf(ae.getLinks().getUser()), ARBITRARY_USER_ID);
    }
}

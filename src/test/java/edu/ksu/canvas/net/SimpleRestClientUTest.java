package edu.ksu.canvas.net;

import edu.ksu.canvas.LocalServerTestBase;
import edu.ksu.canvas.exception.CanvasException;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.UnauthorizedException;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.http.HttpHeaders;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertNull;



@RunWith(JUnit4.class)
public class SimpleRestClientUTest extends LocalServerTestBase {

    private OauthToken emptyAdminToken = new NonRefreshableOauthToken("");
    SimpleRestClient restClient = new SimpleRestClient();

    @Test(expected=UnauthorizedException.class)
    public void http401UnauthorizedThrowsException() throws Exception {
        String url = "/unauthorizedUrl";
        registerUrlResponse(url, "/SampleJson/oauth/UserUnauthorizedResponse.json", 401, Collections.emptyMap());

        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }

    @Test(expected=InvalidOauthTokenException.class)
    public void http401InvalidTokenThrowsException() throws Exception {
        String url = "/invalidAccessToken";
        registerUrlResponse(url, "/SampleJson/oauth/InvalidAccessTokenResponse.json", 401, Collections.singletonMap(HttpHeaders.WWW_AUTHENTICATE, ""));

        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }

    @Test(expected = IOException.class)
    public void http503ServiceTemporarilyUnavailableException() throws Exception {
        String url = "/unavailableServiceUrl";
        registerUrlResponse(url, "", 503, Collections.emptyMap());

        final Response response = restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        assertNull(response.getContent());

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testErrorMessageWithoutErrorArray() throws Exception{
        // The url can be any string
        String url = "/canvasException";
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        String jsonFilePath = "/SampleJson/sampleErrorMessageWithoutErrorArray.json";
        registerUrlResponse(url, jsonFilePath, 400, map);
        expectedException.expect(CanvasException.class);
        String errorMessage = "Sample error message";
        expectedException.expectMessage(errorMessage);
        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }

    @Test
    public void testErrorMessageWithErrorArray() throws Exception{
        String url = "/canvasException";
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        String jsonFilePath = "/SampleJson/sampleErrorMessageWithErrorArray.json";
        registerUrlResponse(url, jsonFilePath, 400, map);
        expectedException.expect(CanvasException.class);
        String errorMessages = "sample error message 1, sample error message 2";
        expectedException.expectMessage(errorMessages);
        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }

    @Test
    public void testErrorMessageWithWrongContentType() throws Exception{
        String url = "/canvasException";
        // It doesn't matter what file because error message will be null if content type isn't json
        String jsonFilePath = "/SampleJson/sampleErrorMessageWithoutErrorArray.json";
        // By default, the content type will be plain text if just using Collections.emptyMap()
        registerUrlResponse(url, jsonFilePath, 400, Collections.emptyMap());
        expectedException.expect(CanvasException.class);
        expectedException.expectMessage("null");
        restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
    }

}

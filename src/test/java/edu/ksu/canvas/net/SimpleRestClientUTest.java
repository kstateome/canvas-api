package edu.ksu.canvas.net;

import edu.ksu.canvas.LocalServerTestBase;
import edu.ksu.canvas.errors.UserErrorResponse;
import edu.ksu.canvas.exception.CanvasException;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.exception.RetriableException;
import edu.ksu.canvas.exception.ThrottlingException;
import edu.ksu.canvas.exception.UnauthorizedException;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.oauth.OauthToken;
import org.apache.hc.core5.http.HttpHeaders;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SimpleRestClientUTest extends LocalServerTestBase {

    private OauthToken emptyAdminToken = new NonRefreshableOauthToken("");
    SimpleRestClient restClient = new SimpleRestClient();

    @Test
    void http401UnauthorizedThrowsException() throws Exception {
        String url = "/unauthorizedUrl";
        registerUrlResponse(url, "/SampleJson/oauth/UserUnauthorizedResponse.json", 401, Collections.emptyMap());

        assertThrows(UnauthorizedException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });

    }

    @Test
    void http401InvalidTokenThrowsException() throws Exception {
        String url = "/invalidAccessToken";
        registerUrlResponse(url, "/SampleJson/oauth/InvalidAccessTokenResponse.json", 401, Collections.singletonMap(HttpHeaders.WWW_AUTHENTICATE, ""));

        assertThrows(InvalidOauthTokenException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });

    }

    @Test
    void http403ThrottlingThrowsException() throws Exception {
        String url = "/throttledUrl";
        //Using blank JSON because I haven't been able to observe this error so I don't know what payload it returns
        registerUrlResponse(url, "/SampleJson/BlankResponse.json", 403, Collections.emptyMap());

        assertThrows(ThrottlingException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });
    }

    @Test
    void http504GatewayTimeoutThrowsException() throws Exception {
        String url = "/timeoutUrl";
        //Using blank JSON because I haven't been able to observe this error so I don't know what payload it returns
        registerUrlResponse(url, "/SampleJson/BlankResponse.json", 504, Collections.emptyMap());

        assertThrows(RetriableException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });
    }

    @Test
    void http503ServiceTemporarilyUnavailableException() throws Exception {
        String url = "/unavailableServiceUrl";
        registerUrlResponse(url, "", 503, Collections.emptyMap());

        assertThrows(IOException.class, () -> {
            final Response response = restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });
    }

    @Test
    void testErrorMessageWithoutErrorArray() throws Exception{
        // The url can be any string
        String url = "/canvasException";
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        String jsonFilePath = "/SampleJson/sampleErrorMessageWithoutErrorArray.json";
        registerUrlResponse(url, jsonFilePath, 400, map);

        assertThrows(CanvasException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });
    }

    @Test
    void testErrorMessageWithErrorArray() throws Exception{
        String url = "/canvasException";
        HashMap<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        String jsonFilePath = "/SampleJson/sampleErrorMessageWithErrorArray.json";
        registerUrlResponse(url, jsonFilePath, 400, map);
        assertThrows(CanvasException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });

    }

    @Test
    void testErrorMessageWithWrongContentType() throws Exception{
        String url = "/canvasException";
        // It doesn't matter what file because error message will be null if content type isn't json
        String jsonFilePath = "/SampleJson/sampleErrorMessageWithoutErrorArray.json";
        // By default, the content type will be plain text if just using Collections.emptyMap()
        registerUrlResponse(url, jsonFilePath, 400, Collections.emptyMap());
        assertThrows(CanvasException.class, () -> {
            restClient.sendApiGet(emptyAdminToken, baseUrl + url, 100, 100);
        });

    }

    @Test
    void userCreationError() throws Exception {
        // This test can't easily be in the user part as we need too much control over the response
        String url = "/api/v1/accounts/1/users";
        registerUrlResponse(url, "/SampleJson/user/UserCreateFailedDuplicateId.json", 400, Collections.singletonMap("Content-Type", "application/json"));
        try {
            restClient.sendApiPost(emptyAdminToken, baseUrl + url, Collections.emptyMap(), 100, 100);
        } catch (CanvasException e) {
            Object o = e.getError();
            assertNotNull(o);
            // Validate that we did correctly parse the response
            assertInstanceOf(UserErrorResponse.class, o);
        }
    }

    @Test
    void userCreationErrorNormalError() throws Exception {
        // This test can't easily be in the user part as we need too much control over the response
        String url = "/api/v1/accounts/1/users";
        registerUrlResponse(url, "/SampleJson/sampleErrorMessageWithoutErrorArray.json", 400, Collections.singletonMap("Content-Type", "application/json"));
        try {
            restClient.sendApiPost(emptyAdminToken, baseUrl + url, Collections.emptyMap(), 100, 100);
        } catch (CanvasException e) {
            Object o = e.getError();
            // We shouldn't have helpful error object as this is a generic error.
            assertNull(o);
        }
    }
}

package edu.ksu.canvas.net;

import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.oauth.OauthToken;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefreshingRestClientUTest {
    @Mock
    private RestClient mockRestClient;
    @InjectMocks
    private RefreshingRestClient refreshRestClientUTest;
    @Mock
    private OauthToken mockToken;

    private final String arbitraryString = "arbitraryString";
    private final int arbitraryInt = 1;
    private final Map<String, List<String>> arbitraryMap = Collections.emptyMap();
    private final Response response = new Response();

    @BeforeEach
    void setupResponse() {
        response.setContent(arbitraryString);
    }

    @Test
    void invalidOauthTokenCausesRefreshForApiGet() throws Exception {
        when(mockRestClient.sendApiGet(any(OauthToken.class), anyString(), anyInt(), anyInt()))
                .thenThrow(InvalidOauthTokenException.class)
                .thenReturn(response);

        Response returnedResponse = refreshRestClientUTest.sendApiGet(mockToken, arbitraryString, arbitraryInt, arbitraryInt);

        verify(mockToken, times(1)).refresh();
        assertEquals(response, returnedResponse, "Expected response to be response returned from mockRestClient");
    }

    @Test
    void invalidOauthTokenCausesRefreshForApiPost() throws Exception {
        when(mockRestClient.sendApiPost(any(OauthToken.class), any(), any(), anyInt(), anyInt()))
                .thenThrow(InvalidOauthTokenException.class)
                .thenReturn(response);

        Response returnedResponse = refreshRestClientUTest.sendApiPost(mockToken, arbitraryString, arbitraryMap,  arbitraryInt, arbitraryInt);

        verify(mockToken, times(1)).refresh();
        assertEquals(response, returnedResponse, "Expected response to be response returned from mockRestClient");
    }

    @Test
    void invalidOauthTokenCausesRefreshForApiPut() throws Exception {
        when(mockRestClient.sendApiPut(any(OauthToken.class), any(), any(), anyInt(), anyInt()))
                .thenThrow(InvalidOauthTokenException.class)
                .thenReturn(response);

        Response returnedResponse = refreshRestClientUTest.sendApiPut(mockToken, arbitraryString, arbitraryMap, arbitraryInt, arbitraryInt);

        verify(mockToken, times(1)).refresh();
        assertEquals(response, returnedResponse, "Expected response to be response returned from mockRestClient");
    }

    @Test
    void invalidOauthTokenCausesRefreshForApiDelete() throws Exception {
        when(mockRestClient.sendApiDelete(any(OauthToken.class), anyString(), anyMap(), anyInt(), anyInt()))
                .thenThrow(InvalidOauthTokenException.class)
                .thenReturn(response);

        Response returnedResponse = refreshRestClientUTest.sendApiDelete(mockToken, arbitraryString, arbitraryMap, arbitraryInt, arbitraryInt);

        verify(mockToken, times(1)).refresh();
        assertEquals(response, returnedResponse, "Expected response to be response returned from mockRestClient");
    }

    @Test
    void invalidOauthTokenCausesRefreshForJsonPost() throws Exception {
        when(mockRestClient.sendJsonPost(any(OauthToken.class), anyString(), anyString(), anyInt(), anyInt()))
                .thenThrow(InvalidOauthTokenException.class)
                .thenReturn(response);

        Response returnedResponse = refreshRestClientUTest.sendJsonPost(mockToken, arbitraryString, arbitraryString, arbitraryInt, arbitraryInt);

        verify(mockToken, times(1)).refresh();
        assertEquals(response, returnedResponse, "Expected response to be response returned from mockRestClient");
    }

    @Test
    void invalidOauthTokenCausesRefreshForJsonPut() throws Exception {
        when(mockRestClient.sendJsonPut(any(OauthToken.class), anyString(), anyString(), anyInt(), anyInt()))
                .thenThrow(InvalidOauthTokenException.class)
                .thenReturn(response);

        Response returnedResponse = refreshRestClientUTest.sendJsonPut(mockToken, arbitraryString, arbitraryString, arbitraryInt, arbitraryInt);

        verify(mockToken, times(1)).refresh();
        assertEquals(response, returnedResponse, "Expected response to be response returned from mockRestClient");
    }


}

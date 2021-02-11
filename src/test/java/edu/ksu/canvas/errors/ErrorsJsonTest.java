package edu.ksu.canvas.errors;

import com.google.gson.Gson;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.junit.Test;

import java.io.InputStreamReader;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ErrorsJsonTest {

    @Test
    public void testFailedDuplicateId() {
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/SampleJson/user/UserCreateFailedDuplicateId.json"));
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        UserErrorResponse response = gson.fromJson(reader, UserErrorResponse.class);
        assertNotNull(response);
        assertNotNull(response.getErrors());
        assertNotNull(response.getErrors().getUser());
        assertNotNull(response.getErrors().getUser().getPseudonyms());
        assertEquals(1, response.getErrors().getUser().getPseudonyms().size());
        assertEquals("pseudonyms", response.getErrors().getUser().getPseudonyms().get(0).getAttribute());
        List<UserErrorResponse.Errors.Error> errors = response.getErrors().getPseudonym().getUniqueId();
        assertNotNull(errors);
        UserErrorResponse.Errors.Error error = errors.get(0);
        assertEquals("unique_id", error.getAttribute());
        assertNotNull(error.getMessage());
        assertNotNull(error.getType());
    }

    @Test
    public void testFailedIntegrationId() {
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/SampleJson/user/UserCreateFailedIntegrationId.json"));
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        UserErrorResponse response = gson.fromJson(reader, UserErrorResponse.class);
        assertNotNull(response);
        assertNotNull(response.getErrors());
        assertNotNull(response.getErrors().getUser());
        assertNotNull(response.getErrors().getUser().getPseudonyms());
        assertEquals(1, response.getErrors().getUser().getPseudonyms().size());
        assertEquals("pseudonyms", response.getErrors().getUser().getPseudonyms().get(0).getAttribute());
        List<UserErrorResponse.Errors.Error> errors = response.getErrors().getPseudonym().getIntegrationId();
        assertNotNull(errors);
        UserErrorResponse.Errors.Error error = errors.get(0);
        assertEquals("integration_id", error.getAttribute());
        assertNotNull(error.getMessage());
        assertNotNull(error.getType());
    }

    @Test
    public void testFailedSisId() {
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/SampleJson/user/UserCreateFailedSisId.json"));
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        UserErrorResponse response = gson.fromJson(reader, UserErrorResponse.class);
        assertNotNull(response);
        assertNotNull(response.getErrors());
        assertNotNull(response.getErrors().getUser());
        assertNotNull(response.getErrors().getUser().getPseudonyms());
        assertEquals(1, response.getErrors().getUser().getPseudonyms().size());
        assertEquals("pseudonyms", response.getErrors().getUser().getPseudonyms().get(0).getAttribute());
        List<UserErrorResponse.Errors.Error> errors = response.getErrors().getPseudonym().getSisUserId();
        assertNotNull(errors);
        UserErrorResponse.Errors.Error error = errors.get(0);
        assertEquals("sis_user_id", error.getAttribute());
        assertNotNull(error.getMessage());
        assertNotNull(error.getType());
    }
}

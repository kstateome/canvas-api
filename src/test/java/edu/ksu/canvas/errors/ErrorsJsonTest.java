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
        assertNotNull(response.getErrors().getUser().get("pseudonyms"));
        assertEquals(1, response.getErrors().getUser().get("pseudonyms").size());
        assertEquals("pseudonyms", response.getErrors().getUser().get("pseudonyms").get(0).getAttribute());
        List<ErrorDetails> errors = response.getErrors().getPseudonym().get("unique_id");
        assertNotNull(errors);
        ErrorDetails error = errors.get(0);
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
        assertNotNull(response.getErrors().getUser().get("pseudonyms"));
        assertEquals(1, response.getErrors().getUser().get("pseudonyms").size());
        assertEquals("pseudonyms", response.getErrors().getUser().get("pseudonyms").get(0).getAttribute());
        List<ErrorDetails> errors = response.getErrors().getPseudonym().get("integration_id");
        assertNotNull(errors);
        ErrorDetails error = errors.get(0);
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
        assertNotNull(response.getErrors().getUser().get("pseudonyms"));
        assertEquals(1, response.getErrors().getUser().get("pseudonyms").size());
        assertEquals("pseudonyms", response.getErrors().getUser().get("pseudonyms").get(0).getAttribute());
        List<ErrorDetails> errors = response.getErrors().getPseudonym().get("sis_user_id");
        assertNotNull(errors);
        ErrorDetails error = errors.get(0);
        assertEquals("sis_user_id", error.getAttribute());
        assertNotNull(error.getMessage());
        assertNotNull(error.getType());
    }

    @Test
    public void testFailedLoginUniqueId() {
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/SampleJson/login/LoginUpdateFailedUniqueId.json"));
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        GenericErrorResponse response = gson.fromJson(reader, GenericErrorResponse.class);
        assertNotNull(response);
        assertNotNull(response.getErrors());
        assertNotNull(response.getErrors().get("unique_id"));
        assertEquals(1, response.getErrors().get("unique_id").size());
        List<ErrorDetails> errors = response.getErrors().get("unique_id");
        assertNotNull(errors);
        ErrorDetails error = errors.get(0);
        assertEquals("unique_id", error.getAttribute());
        assertNotNull(error.getMessage());
        assertNotNull(error.getType());
    }

    @Test
    public void testFailedCourseDateError() {
        InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream("/SampleJson/course/CreateCourseDateError.json"));
        Gson gson = GsonResponseParser.getDefaultGsonParser(false);
        GenericErrorResponse response = gson.fromJson(reader, GenericErrorResponse.class);
        assertNotNull(response);
        assertNotNull(response.getErrors());
        assertNotNull(response.getErrors().get("conclude_at"));
        assertEquals(1, response.getErrors().get("conclude_at").size());
        List<ErrorDetails> errors = response.getErrors().get("conclude_at");
        assertNotNull(errors);
        ErrorDetails error = errors.get(0);
        assertEquals("conclude_at", error.getAttribute());
        assertNotNull(error.getMessage());
        assertNotNull(error.getType());
    }
}

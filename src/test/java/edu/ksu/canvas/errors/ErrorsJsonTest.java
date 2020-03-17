package edu.ksu.canvas.errors;

import com.google.gson.Gson;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.junit.Test;

import java.io.InputStreamReader;

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
    }
}

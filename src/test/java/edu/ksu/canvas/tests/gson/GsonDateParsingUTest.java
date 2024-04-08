package edu.ksu.canvas.tests.gson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.GsonResponseParser;
import edu.ksu.canvas.model.assignment.Assignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Unit tests for custom serialization/deserialization functionality
 */
class GsonDateParsingUTest extends CanvasTestBase {
    private static final long UNIX_TIME = 1486425500000l;
    private static final String ISO_DATE_STRING = "2017-02-06T23:58:20Z";
    private static final String SERIALIZED_ASSIGNMENT_EMPTY_DATE = "{\"name\":\"Assignment 1\",\"due_at\":\"\"}";
    private static final String SERIALIZED_ASSIGNMENT = "{\"name\":\"Assignment 1\",\"due_at\":\"" + ISO_DATE_STRING + "\"}";

    private Assignment assignment; //Dummy object to put dates into for parsing
    private Gson gson;

    @BeforeEach
    void setUpGson() {
        gson = GsonResponseParser.getDefaultGsonParser(false);
        assignment = new Assignment();
        assignment.setName("Assignment 1");
    }

    @Test
    void serializeDateToIsoFormat() throws Exception {
        assignment.setDueAt(new Date(UNIX_TIME));
        String jsonString = gson.toJson(assignment);

        assertThat(jsonString, containsString(ISO_DATE_STRING));
    }

    @Test
    void deSerializeFromIsoFormat() throws Exception {
        Assignment assignment = gson.fromJson(SERIALIZED_ASSIGNMENT, Assignment.class);

        assertEquals(UNIX_TIME, assignment.getDueAt().getTime(), "Date parsing failed");
    }

    @Test
    void nullDateNotSerialized() throws Exception {
        assignment.setDueAt(null);
        String jsonString = gson.toJson(assignment);

        assertThat(jsonString, not(containsString("due_at")));
    }

    //Gson by default throws an exception if you ask it to parse an empty string as a date.
    //Our custom type adapter should prevent this and put in a null date object instead
    @Test
    void parseEmptyDateToNull() throws Exception {
        Assignment parsedAssignment = null;
        try {
            parsedAssignment = gson.fromJson(SERIALIZED_ASSIGNMENT_EMPTY_DATE, Assignment.class);
        } catch (JsonSyntaxException e) {
            fail("JSON parsing of a blank date caused an exception");
        }
        assertNull(parsedAssignment.getDueAt());
    }
}

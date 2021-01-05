package edu.ksu.canvas.impl;

import com.google.gson.Gson;
import junit.framework.TestCase;

import java.time.Instant;

public class GsonResponseParserInstantTest extends TestCase {

    /**
     * Simple helper to make JSON literals easier to write in Java
     *
     * @param json JSON with ' as the string quote character.
     * @return JSON with " as the string quote character.
     */
    static String quote(String json) {
        return json.replace("'", "\"");
    }

    public void testInstantParsingNoZone() {
        Gson defaultGsonParser = GsonResponseParser.getDefaultGsonParser(false);
        Sample sample = defaultGsonParser.fromJson(quote("{'instant': '2020-02-20T01:02:03Z'}"), Sample.class);
        assertNotNull(sample);
        assertEquals(Instant.parse("2020-02-20T01:02:03Z"), sample.instant);
    }

    public void testInstantParsingZone() {
        Gson defaultGsonParser = GsonResponseParser.getDefaultGsonParser(false);
        Sample sample = defaultGsonParser.fromJson(quote("{'instant': '2020-02-20T01:02:03-04:00'}"), Sample.class);
        assertNotNull(sample);
        assertEquals(Instant.parse("2020-02-20T05:02:03Z"), sample.instant);
    }

    public void testInstantParsingZonePositive() {
        Gson defaultGsonParser = GsonResponseParser.getDefaultGsonParser(false);
        Sample sample = defaultGsonParser.fromJson(quote("{'instant': '2020-02-20T01:02:03+04:00'}"), Sample.class);
        assertNotNull(sample);
        assertEquals(Instant.parse("2020-02-19T21:02:03Z"), sample.instant);
    }

    static class Sample {
        public Instant instant;
    }
}
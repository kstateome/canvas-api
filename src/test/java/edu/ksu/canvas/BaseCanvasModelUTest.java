package edu.ksu.canvas;

import edu.ksu.canvas.model.TestCanvasModel;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseCanvasModelUTest {
    public static final String CLASS_POST_KEY = "test";
    public static final String CLASS_POST_KEY_OVERRIDE = "override";
    public static final String FIELD1_POST_KEY = "field1Key";
    public static final String FIELD2_POST_KEY = "field2Key";
    public static final String FIELD3_POST_KEY = "field3Key";
    public static final String FIELD4_POST_KEY = "field4Key";
    public static final String FIELD5_POST_KEY = "field5Key";
    public static final String FIELD1_VALUE = "field1Value";
    public static final String FIELD2_VALUE = "field2Value";
    public static final String FIELD3_VALUE = "field3Value";
    public static final Long FIELD4_VALUE = 6789L;
    public static final List<Long> FIELD5_VALUE = ImmutableList.of(1354L, 9823L, 5387L);

    /* Make sure canvasFields with arrays = true are of form object[field] */
    @Test
    void postKeysAsArraysSetCorrectly() throws Exception {
        String expectedKey = CLASS_POST_KEY + "[" + FIELD1_POST_KEY + "]";
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField1(FIELD1_VALUE);

        Map<String, List<String>> postMap = canvasModel.toPostMap(false);

        assertTrue(postMap.containsKey(expectedKey), "PostKey from canvasModel.toPostMap() did not have expected key for field1");
        assertArrayEquals(new String[] { FIELD1_VALUE }, postMap.get(expectedKey).toArray(), "Field1Value from canvasModel.toPostMap() did not have expected value for field1");
    }

    /* Make sure canvasFields with arrays = false are of form 'field' */
    @Test
    void postKeysAsNonArraysSetCorrectly() throws Exception {
        String expectedKey = FIELD2_POST_KEY;
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField2(FIELD2_VALUE);

        Map<String, List<String>> postMap = canvasModel.toPostMap(false);

        assertTrue(postMap.containsKey(expectedKey),
                "PostKey from canvasModel.toPostMap() did not have expected key for field2");
        assertArrayEquals(new String[] { FIELD2_VALUE }, postMap.get(expectedKey).toArray(), "Field2Value from canvasModel.toPostMap() did not have expected value for field2");
    }

    /* Make sure canvasFields with arrays = true and an override key are of form override[field] */
    @Test
    void postKeysAsArraysWithOverrideKeySetCorrectly() throws Exception {
        final String expectedKey = CLASS_POST_KEY_OVERRIDE + "[" + FIELD3_POST_KEY + "]";
        final TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField3(FIELD3_VALUE);

        final Map<String, List<String>> postMap = canvasModel.toPostMap(false);

        assertTrue(postMap.containsKey(expectedKey), "PostKey from canvasModel.toPostMap() did not have expected key for field3");
        assertArrayEquals(new String[] { FIELD3_VALUE }, postMap.get(expectedKey).toArray(), "Field3Value from canvasModel.toPostMap() did not have expected value for field3");
    }

    @Test
    void postKeysAsArraysWithNonStringTypeSetCorrectly() throws Exception {
        final String expectedKey = CLASS_POST_KEY + "[" + FIELD4_POST_KEY + "]";
        final TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField4(FIELD4_VALUE);

        final Map<String, List<String>> postMap = canvasModel.toPostMap(false);

        assertTrue(postMap.containsKey(expectedKey), "PostKey from canvasModel.toPostMap() did not have expected key for field4");
        assertArrayEquals(new String[] { String.valueOf(FIELD4_VALUE) }, postMap.get(expectedKey).toArray(), "Field3Value from canvasModel.toPostMap() did not have expected value for field4");
    }

    @Test
    void postKeysAsArraysWithMultipleValuesSetCorrectly() throws Exception {
        final String expectedKey = CLASS_POST_KEY + "[" + FIELD5_POST_KEY + "]";
        final TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField5(FIELD5_VALUE);

        final Map<String, List<String>> postMap = canvasModel.toPostMap(false);
        final String[] expectedValue = FIELD5_VALUE.stream().map(String::valueOf).collect(Collectors.toList()).toArray(new String[] {});

        assertTrue(postMap.containsKey(expectedKey), "PostKey from canvasModel.toPostMap() did not have expected key for field5");
        assertArrayEquals(expectedValue, postMap.get(expectedKey).toArray(), "Field3Value from canvasModel.toPostMap() did not have expected value for field5");
    }

    @Test
    void postKeysAsArraysContainsNoNullValues() throws Exception {
        final TestCanvasModel canvasModel = new TestCanvasModel();
        final Map<String, List<String>> postMap = canvasModel.toPostMap(false);
        assertTrue(postMap.isEmpty(), "Result of canvasModel.toPostMap() is not empty as expected");
    }

    @Test
    void jsonObjectWrapping() throws Exception {
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField1(FIELD1_VALUE);

        JsonObject jsonObject = canvasModel.toJsonObject(false);
        JsonElement element = jsonObject.get(CLASS_POST_KEY);

        assertNotNull(element, "JSON object should have a top level element: " + CLASS_POST_KEY);
    }

    @Test
    void jsonField1Value() throws Exception {
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField1(FIELD1_VALUE);

        JsonObject jsonObject = canvasModel.toJsonObject(false);
        JsonElement element = jsonObject.get(CLASS_POST_KEY);
        JsonObject field1Obj = element.getAsJsonObject();
        String field1Value = field1Obj.get("field1").getAsString();

        assertEquals( FIELD1_VALUE, field1Value, "JSON object should have a 'field1' value of " + FIELD1_VALUE);
    }
}
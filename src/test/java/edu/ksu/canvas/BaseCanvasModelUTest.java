package edu.ksu.canvas;

import edu.ksu.canvas.model.TestCanvasModel;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class BaseCanvasModelUTest {
    public static final String CLASS_POST_KEY = "test";
    public static final String CLASS_POST_KEY_OVERRIDE = "override";
    public static final String FIELD1_POST_KEY = "field1Key";
    public static final String FIELD2_POST_KEY = "field2Key";
    public static final String FIELD3_POST_KEY = "field3Key";
    public static final String FIELD1_VALUE = "field1Value";
    public static final String FIELD2_VALUE = "field2Value";
    public static final String FIELD3_VALUE = "field3Value";


    /* Make sure canvasFields with arrays = true are of form object[field] */
    @Test
    public void postKeysAsArraysSetCorrectly() throws Exception {
        String expectedKey = CLASS_POST_KEY + "[" + FIELD1_POST_KEY + "]";
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField1(FIELD1_VALUE);

        Map<String, Object> postMap = canvasModel.toPostMap();

        Assert.assertTrue("PostKey from canvasModel.toPostMap() did not have expected key for field1", postMap.containsKey(expectedKey));
        Assert.assertEquals("Field1Value from canvasModel.toPostMap() did not have expected value for field1", FIELD1_VALUE, postMap.get(expectedKey));
    }

    /* Make sure canvasFields with arrays = false are of form 'field' */
    @Test
    public void postKeysAsNonArraysSetCorrectly() throws Exception {
        String expectedKey = FIELD2_POST_KEY;
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField2(FIELD2_VALUE);

        Map<String, Object> postMap = canvasModel.toPostMap();

        Assert.assertTrue("PostKey from canvasModel.toPostMap() did not have expected key for field2", postMap.containsKey(expectedKey));
        Assert.assertEquals("Field2Value from canvasModel.toPostMap() did not have expected value for field2", FIELD2_VALUE, postMap.get(expectedKey));
    }

    /* Make sure canvasFields with arrays = true and an override key are of form override[field] */
    @Test
    public void postKeysAsArraysWithOverrideKeySetCorrectly() throws Exception {
        final String expectedKey = CLASS_POST_KEY_OVERRIDE + "[" + FIELD3_POST_KEY + "]";
        final TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField3(FIELD3_VALUE);

        final Map<String, Object> postMap = canvasModel.toPostMap();

        Assert.assertTrue("PostKey from canvasModel.toPostMap() did not have expected key for field3", postMap.containsKey(expectedKey));
        Assert.assertEquals("Field3Value from canvasModel.toPostMap() did not have expected value for field3", FIELD3_VALUE, postMap.get(expectedKey));
    }

    @Test
    public void jsonObjectWrapping() throws Exception {
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField1(FIELD1_VALUE);

        JsonObject jsonObject = canvasModel.toJsonObject();
        JsonElement element = jsonObject.get(CLASS_POST_KEY);

        Assert.assertNotNull("JSON object should have a top level element: " + CLASS_POST_KEY, element);
    }

    @Test
    public void jsonField1Value() throws Exception {
        TestCanvasModel canvasModel = new TestCanvasModel();
        canvasModel.setField1(FIELD1_VALUE);

        JsonObject jsonObject = canvasModel.toJsonObject();
        JsonElement element = jsonObject.get(CLASS_POST_KEY);
        JsonObject field1Obj = element.getAsJsonObject();
        String field1Value = field1Obj.get("field1").getAsString();

        Assert.assertEquals("JSON object should have a 'field1' value of " + FIELD1_VALUE, FIELD1_VALUE, field1Value);
    }
}
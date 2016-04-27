package edu.ksu.canvas;

import edu.ksu.canvas.model.TestCanvasModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BaseCanvasModelUTest {
    public static final String CLASS_POST_KEY = "test";
    public static final String FIELD1_POST_KEY = "field1Key";
    public static final String FIELD2_POST_KEY = "field2Key";
    public static final String FIELD1_VALUE = "field1Value";
    public static final String FIELD2_VALUE = "field2Value";


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

}
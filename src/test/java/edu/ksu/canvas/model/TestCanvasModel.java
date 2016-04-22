package edu.ksu.canvas.model;

import edu.ksu.canvas.BaseCanvasModelUTest;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

/*
 * Class was created to help test BaseCanvasModel. The class needs to be public in order for the
 * reflection in BaseCanvasModel to work
 */
@CanvasObject(postKey = BaseCanvasModelUTest.CLASS_POST_KEY)
public class TestCanvasModel extends BaseCanvasModel {
    private String field1;
    private String field2;

    @CanvasField(postKey = BaseCanvasModelUTest.FIELD1_POST_KEY)
    public String getField1() {
        return field1;
    }

    public void setArrayPostKey(String field1) {
        this.field1 = field1;
    }

    @CanvasField(postKey = BaseCanvasModelUTest.FIELD2_POST_KEY, array = false)
    public String getField2() {
        return field2;
    }

    public void setNonArrayPostKey(String field2) {
        this.field2 = field2;
    }
}

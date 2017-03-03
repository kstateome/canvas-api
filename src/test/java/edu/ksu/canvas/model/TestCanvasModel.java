package edu.ksu.canvas.model;

import edu.ksu.canvas.BaseCanvasModelUTest;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.util.List;

/*
 * Class was created to help test BaseCanvasModel. The class needs to be public in order for the
 * reflection in BaseCanvasModel to work
 */
@CanvasObject(postKey = BaseCanvasModelUTest.CLASS_POST_KEY)
public class TestCanvasModel extends BaseCanvasModel {
    private String field1;
    private String field2;
    private String field3;
    private Integer field4;
    private List<Integer> field5;

    @CanvasField(postKey = BaseCanvasModelUTest.FIELD1_POST_KEY)
    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    @CanvasField(postKey = BaseCanvasModelUTest.FIELD2_POST_KEY, array = false)
    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    @CanvasField(overrideObjectKey = BaseCanvasModelUTest.CLASS_POST_KEY_OVERRIDE, postKey = BaseCanvasModelUTest.FIELD3_POST_KEY)
    public String getField3() {
        return field3;
    }

    public void setField3(final String field3) {
        this.field3 = field3;
    }

    @CanvasField(postKey = BaseCanvasModelUTest.FIELD4_POST_KEY)
    public Integer getField4() {
        return field4;
    }

    public void setField4(final Integer field4) {
        this.field4 = field4;
    }

    @CanvasField(postKey = BaseCanvasModelUTest.FIELD5_POST_KEY)
    public List<Integer> getField5() {
        return field5;
    }

    public void setField5(final List<Integer> field5) {
        this.field5 = field5;
    }
}

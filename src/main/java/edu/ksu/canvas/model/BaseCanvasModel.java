package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseCanvasModel {
    /* Canvas has post parameter keys in non consistent formats. Occasionally they are 'class[field]' and other times
     * they are just 'field'. This method will create a map with the correct post keys and values based on the
     * @CanvasField and @CanvasObject annotations.
     */
    public Map<String, Object> toPostMap() throws IllegalAccessException, InvocationTargetException {
        Class<? extends BaseCanvasModel> clazz = this.getClass();
        Map<String, Object> postMap = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            CanvasField canvasFieldAnnotation = method.getAnnotation(CanvasField.class);
            if (canvasFieldAnnotation != null && canvasFieldAnnotation.postKey() != null) {
                String postKey = getPostKey(canvasFieldAnnotation);
                Object value = method.invoke(this, null);
                postMap.put(postKey, value);
            }
        }
        return postMap;
    }

    private String getPostKey(CanvasField canvasFieldAnnotation) {
        if (!canvasFieldAnnotation.array()) {
            return canvasFieldAnnotation.postKey();
        } else {
            return makeArrayPostKey(canvasFieldAnnotation);
        }
    }

    private String makeArrayPostKey(CanvasField canvasFieldAnnotation) {
        if (!canvasFieldAnnotation.overrideObjectKey().isEmpty()) {
            return canvasFieldAnnotation.overrideObjectKey() + "[" + canvasFieldAnnotation.postKey() + "]";
        }
        CanvasObject canvasObjectAnnotation = this.getClass().getAnnotation(CanvasObject.class);
        if (canvasObjectAnnotation == null || canvasObjectAnnotation.postKey() == null) {
            throw new IllegalArgumentException("CanvasObject does not contain postKey for " + this.getClass().getName());
        }
        return canvasObjectAnnotation.postKey() + "[" + canvasFieldAnnotation.postKey() + "]";
    }
}

package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.impl.GsonResponseParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

    /**
     * Wraps a Canvas model inside of a JSON object so that the resulting serialized object
     * can be pushed to Canvas create/edit endpoints. For example, to create an assignment, the JSON
     * must look like: <pre>{assignment: {name: "Assignment 1"}}</pre>.
     * This method adds the outer "assignment" container based on CanvasObject notations on the model classes
     * @return A JsonObject suitable for serializing out to the Canvas API
     */
    public JsonObject toJsonObject() {
        Class<? extends BaseCanvasModel> clazz = this.getClass();
        CanvasObject canvasObjectAnnotation = clazz.getAnnotation(CanvasObject.class);
        if(canvasObjectAnnotation == null || canvasObjectAnnotation.postKey() == null) {
            throw new IllegalArgumentException("Object to wrap must have a CanvasObject annotation with a postKey");
        }
        String objectPostKey = canvasObjectAnnotation.postKey();
        JsonElement element = GsonResponseParser.getDefaultGsonParser().toJsonTree(this);
        JsonObject jsonObject = new JsonObject();
        jsonObject.add(objectPostKey, element);
        return jsonObject;
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

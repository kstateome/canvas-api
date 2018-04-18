package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.impl.GsonResponseParser;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class BaseCanvasModel {
    private static final Logger LOG = Logger.getLogger(BaseCanvasModel.class);

    /* Canvas has post parameter keys in non consistent formats. Occasionally they are 'class[field]' and other times
     * they are just 'field'. This method will create a map with the correct post keys and values based on the
     * @CanvasField and @CanvasObject annotations.
     */
    public Map<String, List<String>> toPostMap() {
        Class<? extends BaseCanvasModel> clazz = this.getClass();
        Map<String, List<String>> postMap = new HashMap<>();
        for (Method method : clazz.getMethods()) {
            CanvasField canvasFieldAnnotation = method.getAnnotation(CanvasField.class);
            if (canvasFieldAnnotation != null && canvasFieldAnnotation.postKey() != null) {
                final String postKey = getPostKey(canvasFieldAnnotation);
                try {
                    final List<String> fieldValues = getFieldValues(method);
                    if (fieldValues != null) {
                        if (postMap.containsKey(postKey)) {
                            postMap.get(postKey).addAll(fieldValues);
                        } else {
                            postMap.put(postKey, fieldValues);
                        }
                    }
                } catch (final IllegalAccessException | InvocationTargetException e) {
                    final String message = "Could not access Canvas model getter for" + postKey;
                    LOG.error(message, e);
                    throw new IllegalStateException(message, e);
                }
            }
        }
        return postMap;
    }

    /**
     * Wraps a Canvas model inside of a JSON object so that the resulting serialized object
     * can be pushed to Canvas create/edit endpoints. For example, to create an assignment, the JSON
     * must look like: <pre>{assignment: {name: "Assignment 1"}}</pre>.
     * This method adds the outer "assignment" container based on CanvasObject notations on the model classes
     * @param serializeNulls Whether or not to include null fields in the serialized JSON. Defaults to false if null
     * @return A JsonObject suitable for serializing out to the Canvas API
     */
    public JsonObject toJsonObject(Boolean serializeNulls) {
        Class<? extends BaseCanvasModel> clazz = this.getClass();
        CanvasObject canvasObjectAnnotation = clazz.getAnnotation(CanvasObject.class);
        if(canvasObjectAnnotation == null || canvasObjectAnnotation.postKey() == null) {
            throw new IllegalArgumentException("Object to wrap must have a CanvasObject annotation with a postKey");
        }
        String objectPostKey = canvasObjectAnnotation.postKey();
        JsonElement element = GsonResponseParser.getDefaultGsonParser(serializeNulls).toJsonTree(this);
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

    private List<String> getFieldValues(final Method getter) throws InvocationTargetException, IllegalAccessException {
        final List<String> fieldValues = new ArrayList<>(1);
        final Class<?> returnType = getter.getReturnType();
        final Object returnValue = getter.invoke(this);

        if (returnValue == null) {
           return null;
        }

        if (Iterable.class.isAssignableFrom(returnType)) {
            for (final Object value : (Iterable) returnValue) {
                fieldValues.add(String.valueOf(value));
            }
        }
        else {
            fieldValues.add(String.valueOf(returnValue));
        }
        return fieldValues;
    }
}

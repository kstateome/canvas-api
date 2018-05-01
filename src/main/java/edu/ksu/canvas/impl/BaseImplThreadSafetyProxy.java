package edu.ksu.canvas.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonSyntaxException;
import edu.ksu.canvas.constants.CanvasConstants;
import edu.ksu.canvas.interfaces.CanvasMessenger;
import edu.ksu.canvas.interfaces.ResponseParser;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseImplThreadSafetyProxy implements InvocationHandler {

    private ThreadLocal<String> masqueradeAs = new ThreadLocal<>();
    private ThreadLocal<String> masqueradeType = new ThreadLocal<>();
    private BaseImpl target;
    private final CanvasMessenger canvasMessenger;
    private final ResponseParser responseParser;
    private final OauthToken oauthToken;
    private final Class<?> objectType;

    public BaseImplThreadSafetyProxy(Object target, CanvasMessenger canvasMessenger, ResponseParser responseParser, OauthToken oauthToken, Class<?> objectType, String masqueradeAs, String masqueradeType) {
        this.target = (BaseImpl) target;
        this.canvasMessenger = canvasMessenger;
        this.responseParser = responseParser;
        this.oauthToken = oauthToken;
        this.objectType = objectType;
        this.masqueradeAs.set(masqueradeAs);
        this.masqueradeType.set(masqueradeType);
    }

    //TODO: If this route is chosen then use this on all similar functionality in BaseImpl
    //TODO: If this route is chosen then cleanup, add finer grain tests, and handle errors
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable, JsonSyntaxException {
        //Only handle the test method for POC
        if (method.getName().matches("getTestModel.*")) {
            Map<String, List<String>> parameters = new HashMap<String, List<String>>();
            if (CanvasConstants.MASQUERADE_CANVAS_USER.equals(masqueradeType.get())) {
                parameters.put("as_user_id", Arrays.asList(masqueradeAs.get()));
            } else if (CanvasConstants.MASQUERADE_SIS_USER.equals(masqueradeType.get())) {
                parameters.put("as_user_id", Arrays.asList("sis_user_id:" + masqueradeAs.get()));
            }
            // This obviously only handles the test case for the POC.
            String objectId = (String) args[0];
            return responseParser.parseToObject(objectType, this.canvasMessenger.getSingleResponseFromCanvas(oauthToken, buildCanvasUrl("testModels/" + objectId, parameters)));
        } else {
            try {
                return method.invoke(target, args);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            }
        }

    }

    protected String buildCanvasUrl(String canvasMethod, Map<String, List<String>> parameters) {
        Map<String, List<String>> allParameters = new HashMap<>();
        allParameters.putAll(parameters);

        // Add in possible URL parameters for masquerading and pagination page size
        if (StringUtils.isNotBlank(masqueradeAs.get())) {
            if(CanvasConstants.MASQUERADE_CANVAS_USER.equals(masqueradeType.get())) {
                allParameters.put("as_user_id", Arrays.asList(masqueradeAs.get()));
            } else if(CanvasConstants.MASQUERADE_SIS_USER.equals(masqueradeType.get())) {
                allParameters.put("as_user_id", Arrays.asList("sis_user_id:" + masqueradeAs.get()));
            }
            //Since masquerading options are added on a per-call basis, blank them out after using them for this call
            masqueradeAs = null;
            masqueradeType = null;
        }
        Integer paginationPageSize = ((BaseImpl)target).getPaginationPageSize();
        if(paginationPageSize != null) {
            allParameters.put("per_page", Arrays.asList(paginationPageSize.toString()));
        }

        Map<String, List<String>> nonEmptyParams = stripEmptyParams(allParameters);

        String finalUrl = CanvasURLBuilder.buildCanvasUrl(target.getCanvasBaseUrl(), target.getApiVersion(), canvasMethod, nonEmptyParams);
        return finalUrl;
    }

    /**
     * Returns URL parameters after removing any that have an empty list in them.
     * For optional list arguments, we pass an empty list to the API method. This generates
     * an entry like includes[]= with no value. This function strips them out so we only
     * pass parameters to Canvas that have a value to send along.
     */
    private Map<String, List<String>> stripEmptyParams(Map<String, List<String>> parameters) {
        ImmutableMap.Builder<String, List<String>> paramsBuilder = ImmutableMap.<String, List<String>>builder();
        for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
            if(entry.getValue() != null && !entry.getValue().isEmpty()) {
                paramsBuilder.put(entry.getKey(), entry.getValue());
            }
        }
        return paramsBuilder.build();
    }
}

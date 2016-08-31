package edu.ksu.canvas.requestOptions;

import java.util.List;
import java.util.stream.Collectors;

public class ListAccountOptions extends BaseOptions {

    public enum Include {
        lti_guid, registration_settings
    }

    public ListAccountOptions includes(List<Include> includes) {
        List<String> includeStrings = includes.stream().map(i -> i.name()).collect(Collectors.toList());
        optionsMap.put("include[]", includeStrings);
        return this;
    }
}

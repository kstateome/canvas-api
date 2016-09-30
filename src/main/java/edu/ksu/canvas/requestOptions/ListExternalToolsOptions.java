package edu.ksu.canvas.requestOptions;

import java.util.Arrays;

public class ListExternalToolsOptions extends BaseOptions {

    private String id;

    public ListExternalToolsOptions(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public ListExternalToolsOptions searchTerm(String searchTerm) {
        if(searchTerm == null || searchTerm.length() < 3) {
            throw new IllegalArgumentException("Search term must be at least 3 characters");
        }
        optionsMap.put("search_term", Arrays.asList(searchTerm));
        return this;
    }

    public ListExternalToolsOptions isSelectable(Boolean selectable) {
        optionsMap.put("selectable", Arrays.asList(selectable.toString()));
        return this;
    }

}

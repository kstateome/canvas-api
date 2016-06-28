package edu.ksu.canvas.requestmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseOptions {

    protected Map<String, List<String>> optionsMap = new HashMap<>();

    public Map<String, List<String>> getOptionsMap() {
        return optionsMap;
    }
}

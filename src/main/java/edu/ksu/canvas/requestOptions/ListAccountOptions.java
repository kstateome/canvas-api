package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListAccountOptions extends BaseOptions {

    public enum Include {
        LTI_GUID, REGISTRATION_SETTINGS;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public ListAccountOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }
}

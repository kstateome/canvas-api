package edu.ksu.canvas.model.status;

import java.io.Serializable;

/**
 * Class to parse the response on some object deletion methods.  For certain
 * objects, such as courses, the Canvas API overloads the DELETE verb for
 * either true deletion or a "deleted" state such as conclusion, and returns
 * a response "conclude": true.
 */
public class Conclude implements Serializable {
    public static final long serialVersionUID = 1L;

    private Boolean conclude;

    public Boolean getConclude() {
        return conclude;
    }

    public void setConclude(Boolean conclude) {
        this.conclude = conclude;
    }
}

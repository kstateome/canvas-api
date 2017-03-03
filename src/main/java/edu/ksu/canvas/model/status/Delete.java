package edu.ksu.canvas.model.status;

import java.io.Serializable;

/**
 * Class to parse the response on some object deletion methods.
 * Some deletion methods return the deleted object upon completion
 * but some return an object with just "deleted: true" in it.
 *
 */
public class Delete implements Serializable {
    public static final long serialVersionUID = 1L;

    private Boolean delete;

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}

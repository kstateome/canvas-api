package edu.ksu.canvas.model;

import java.io.Serializable;

/**
 * Created by prakashreddy on 10/7/15.
 */
public class Delete implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean delete;

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }
}

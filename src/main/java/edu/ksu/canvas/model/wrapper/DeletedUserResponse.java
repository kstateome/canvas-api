package edu.ksu.canvas.model.wrapper;

import edu.ksu.canvas.model.User;

import java.io.Serializable;

public class DeletedUserResponse implements Serializable {
    public static final long serialVersionUID = 1L;
    private User user;

    public User getUser() {
        return user;
    }
}

package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.exception.OauthTokenRequiredException;
import edu.ksu.canvas.model.User;

import java.io.IOException;
import java.util.List;

public interface UserReader extends CanvasBase {
    List<User> getUsersInCourse(String oauthToken, String courseId) throws OauthTokenRequiredException, IOException;

}

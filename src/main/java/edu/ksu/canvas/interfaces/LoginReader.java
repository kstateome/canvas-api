package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Login;

import java.io.IOException;
import java.util.List;

public interface LoginReader extends CanvasReader<Login, LoginReader> {
	public List<Login> getLoginForUser(String userId) throws IOException;
}

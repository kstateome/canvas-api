package edu.ksu.canvas.exception;

/**
 * Thrown if Canvas returns a 401 response with a WWW-Authenticate header present.
 * Sometimes this is just because the access token has expired and
 * needs to be refreshed. If this is the case, the exception will
 * be caught in the RefreshingRestClient class and handled. If this
 * exception makes it back up to the calling code, it probably means
 * that the user's refresh token (or the manually generated admin token)
 * is invalid.
 */
public class InvalidOauthTokenException extends CanvasException {
    private static final long serialVersionUID = 1L;
}

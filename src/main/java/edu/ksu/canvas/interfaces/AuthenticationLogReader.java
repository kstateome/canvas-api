package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.AuthenticationLog;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface AuthenticationLogReader extends CanvasReader<AuthenticationLog, AuthenticationLogReader> {

    /**
     * Returns an authentication log.
     * @param accountId The account ID for the authentication log
     * @return An authentication log
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AuthenticationLog> getAuthenticationLogForAccount(String accountId) throws IOException, URISyntaxException, ParseException;

    /**
     * Returns an authentication log.
     * @param loginId The login ID for the authentication log
     * @return An authentication log
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AuthenticationLog> getAuthenticationLogForLogin(String loginId) throws IOException, URISyntaxException, ParseException;

    /**
     * Returns an authentication log.
     * @param userId The user ID for the authentication log
     * @return An authentication log
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<AuthenticationLog> getAuthenticationLogForUser(String userId) throws IOException, URISyntaxException, ParseException;
}

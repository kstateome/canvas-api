package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Account;

import java.io.IOException;
import java.util.Optional;

/**
 * Methods to read information from and about accounts
 */
public interface AccountReader extends CanvasReader<Account, AccountReader> {

    /**
     * Retrieve information on an individual account
     * @param accountId accountId Required. Account's id or sis_account_id
     * @return The requested account from Canvas
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Account> getSingleAccount(String accountId) throws IOException;

}

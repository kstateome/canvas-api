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
     * @param accountId Required. account's id or sis_account_id
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<Account> getSingleAccount(String accountId) throws IOException;

}

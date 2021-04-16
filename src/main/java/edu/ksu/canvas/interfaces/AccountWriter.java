package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Account;

import java.io.IOException;
import java.util.Optional;

public interface AccountWriter extends CanvasWriter<Account, AccountWriter> {
    /**
     * Create a new account in Canvas
     * @param accountId the account ID of the account under which to place this account
     * @param account A account object containing the information needed to create a account in Canvas
     * @return The newly created account
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<Account> createAccount(String accountId, Account account) throws IOException;

     /**
      * Update a account in Canvas
      * @param account A account object containing the information needed to update a account in Canvas
      * @return The newly updated account
      * @throws IOException When there is an error communicating with Canvas
      */
      Optional<Account> updateAccount(Account account) throws IOException;

    /**
     * @param parentAccountId The ID of the parent account to the account to delete.
     * @param accountId The ID of the account you wish to delete
     * @return true if the account was deleted
     * @throws IOException When there is an error communicating with Canvas
     */
     Boolean deleteAccount(String parentAccountId, String accountId) throws IOException;
}

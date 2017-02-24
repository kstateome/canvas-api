package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.requestOptions.GetSubAccountsOptions;
import edu.ksu.canvas.requestOptions.ListAccountOptions;

import java.io.IOException;
import java.util.List;
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

    /**
     * Return a list of accounts that the current user can view or manage.
     * @param options Object encapsulating parameters to the list accounts API call
     * @return List of accounts
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Account> listAccounts(ListAccountOptions options) throws IOException;

    /**
     * Return a list of sub-accounts to the given account
     * @param options options object encapsulating parameters to the sub-accounts API call
     * @return List of sub-accounts
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Account> getSubAccounts(GetSubAccountsOptions options) throws IOException;

    /**
     * List accounts that the current user can view through their admin course enrollments
     * @return List of accounts
     * @throws IOException When there is an error communicating with Canvas
     */
    List<Account> listAccountsForCourseAdmins() throws IOException;
}

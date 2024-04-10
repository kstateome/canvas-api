package edu.ksu.canvas.tests.account;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AccountImpl;
import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.GetSubAccountsOptions;
import edu.ksu.canvas.requestOptions.ListAccountOptions;
import edu.ksu.canvas.requestOptions.ListAccountOptions.Include;
import edu.ksu.canvas.util.CanvasURLBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountReaderUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private AccountReader accountReader;

    private static final String ROOT_ACCOUNT_ID = "1";

    @BeforeEach
    void setupReader() {
        accountReader = new AccountImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testGetSingleAccount() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"accounts/" + ROOT_ACCOUNT_ID, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/account/RootAccount.json");
        Optional<Account> optionalAccount = accountReader.getSingleAccount(ROOT_ACCOUNT_ID);
        assertTrue(optionalAccount.isPresent());
        assertEquals(new Long(1), optionalAccount.get().getId());
    }

    @Test
    void testGetSubAccounts() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion, "accounts/" + ROOT_ACCOUNT_ID + "/sub_accounts", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/account/FiveSubAccounts.json");
        List<Account> accountList = accountReader.getSubAccounts(new GetSubAccountsOptions(ROOT_ACCOUNT_ID));
        assertEquals(5, accountList.size());
    }

    @Test
    void listAccountWithLtiGuidInclude() throws Exception {
        Map<String, List<String>> optionsMap = new HashMap<>();
        optionsMap.put("include[]", Arrays.asList("lti_guid"));
        String url = baseUrl + "/api/v1/accounts?include[]=lti_guid";
        fakeRestClient.addSuccessResponse(url, "SampleJson/account/AccountWithLtiGuid.json");
        List<Account> accountList = accountReader.listAccounts(new ListAccountOptions().includes(Arrays.asList(Include.LTI_GUID)));
        Account account = accountList.get(0);
        assertEquals("f22a4332-3d40-427b-846d-9bc1fa5ab9b4.canvas.example.edu", account.getLtiGuid());
    }

}

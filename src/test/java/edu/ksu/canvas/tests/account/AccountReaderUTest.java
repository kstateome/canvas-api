package edu.ksu.canvas.tests.account;

import java.util.Collections;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.AccountImpl;
import edu.ksu.canvas.interfaces.AccountReader;
import edu.ksu.canvas.model.Account;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.util.CanvasURLBuilder;

public class AccountReaderUTest extends CanvasTestBase {
    private static final Logger LOG = Logger.getLogger(AccountReaderUTest.class);
    @Autowired
    private FakeRestClient fakeRestClient;
    private AccountReader accountReader;

    @Before
    public void setupReader() {
        accountReader = new AccountImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE);
    }

    @Test
    public void testGetSingleAccount() throws Exception {
        String rootAccountId = "1";
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"accounts/" + rootAccountId, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/account/RootAccount.json");
        Optional<Account> optionalAccount = accountReader.getSingleAccount(rootAccountId);
        Assert.assertTrue(optionalAccount.isPresent());
        Assert.assertEquals(new Integer(1), optionalAccount.get().getAccountId());
    }

}

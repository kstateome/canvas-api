package edu.ksu.canvas.requestOptions;

import java.util.Arrays;

public class GetSubAccountsOptions extends BaseOptions {

    String accountId;

    public GetSubAccountsOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public GetSubAccountsOptions recursive(Boolean recursive) {
        optionsMap.put("recursive", Arrays.asList(recursive.toString()));
        return this;
    }
}

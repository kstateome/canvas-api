package edu.ksu.canvas.requestOptions;

public class GetSubAccountsOptions extends BaseOptions {

    String accountId;

    public GetSubAccountsOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public GetSubAccountsOptions recursive(Boolean recursive) {
        addSingleItem("recursive", recursive.toString());
        return this;
    }
}

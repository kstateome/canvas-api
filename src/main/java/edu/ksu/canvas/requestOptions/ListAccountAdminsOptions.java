package edu.ksu.canvas.requestOptions;

import static java.util.Arrays.asList;

public class ListAccountAdminsOptions extends BaseOptions {
    private String accountId;

    public ListAccountAdminsOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public ListAccountAdminsOptions includes(Integer... userIds) {
        addNumberList("user_id[]", asList(userIds));
        return this;
    }
}

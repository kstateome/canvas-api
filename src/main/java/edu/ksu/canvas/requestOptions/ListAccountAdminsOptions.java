package edu.ksu.canvas.requestOptions;

import java.util.List;

public class ListAccountAdminsOptions extends BaseOptions {
    private String accountId;

    public ListAccountAdminsOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public ListAccountAdminsOptions includes(List<Integer> userIds) {
        addNumberList("user_id[]", userIds);
        return this;
    }
}

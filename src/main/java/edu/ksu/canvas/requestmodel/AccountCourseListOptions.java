package edu.ksu.canvas.requestmodel;

import java.util.Arrays;

public class AccountCourseListOptions extends BaseOptions {

    private String accountId;

    public AccountCourseListOptions(String accountId) {
        this.accountId = accountId;
    }

    public AccountCourseListOptions addEnrollmentTermId(String enrollmentTermId) {
        optionsMap.put("enrollment_term_id", Arrays.asList(enrollmentTermId));
        return this;
    }

    public String getAccountId() {
        return accountId;
    }
}

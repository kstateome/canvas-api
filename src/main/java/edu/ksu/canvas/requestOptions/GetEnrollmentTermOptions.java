package edu.ksu.canvas.requestOptions;


import java.util.List;

public class GetEnrollmentTermOptions extends BaseOptions {

    private String accountId;

    public enum Include {
        OVERRIDES;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    /**
     * Create an options object for retrieving enrollment terms. Must have the account ID set.
     * @param accountID Required: ID of the canvas account.
     */
    public GetEnrollmentTermOptions(String accountID) {
        this.accountId = accountID;
    }

    /**
     * Include additional optional fields in the enrollment term objects
     * @param includes List of additional fields to include
     * @return This object to allow adding more options
     */
    public GetEnrollmentTermOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    public String getAccountId() { return accountId; }


}



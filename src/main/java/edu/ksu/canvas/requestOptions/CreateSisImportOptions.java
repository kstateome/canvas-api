package edu.ksu.canvas.requestOptions;

import java.io.InputStream;

public class CreateSisImportOptions extends BaseOptions {

    public enum ImportType {
        instructure_csv;
        // With a standard Canvas install, this option can only be 'instructure_csv', and if unprovided, will be assumed to be so.

        @Override
        public String toString() { return name().toLowerCase(); }

    }

    public static final String ATTACHMENT = "attachment";

    private final String accountId;
    private final String filePath;
    private final InputStream is;
    private final ImportType importType;

    public CreateSisImportOptions(String accountId, String filePath, ImportType importType, String diffingDataSetIdentifier, InputStream is) {
        this(accountId, filePath, importType, diffingDataSetIdentifier, is, false);
    }

    public CreateSisImportOptions(String accountId, String filePath, ImportType importType, String diffingDataSetIdentifier, InputStream is, boolean overrideSisStickiness) {
        this.accountId = accountId;
        this.importType = importType;
        this.filePath = filePath;
        this.is = is;
        addSingleItem("import_type", importType.toString().toLowerCase());
        if(diffingDataSetIdentifier != null) {
            addSingleItem("diffing_data_set_identifier", diffingDataSetIdentifier);
        }
        if (overrideSisStickiness) {
            addSingleItem("override_sis_stickiness", Boolean.toString(overrideSisStickiness));
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public String getFilePath() {
        return filePath;
    }

    public InputStream getInputStream() {
        return is;
    }

    public ImportType getImportType() {
        return importType;
    }

}

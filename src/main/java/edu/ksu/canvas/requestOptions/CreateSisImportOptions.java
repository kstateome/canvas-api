package edu.ksu.canvas.requestOptions;

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
    private final ImportType importType;
    
    public CreateSisImportOptions(String accountId, String filePath, ImportType importType) {
        this.accountId = accountId;
        this.importType = importType;
        this.filePath = filePath;
        addSingleItem("import_type", importType.toString().toLowerCase());
    }

    public String getAccountId() {
        return accountId;
    }

    public String getFilePath() {
        return filePath;
    }

    public ImportType getImportType() {
        return importType;
    }
}
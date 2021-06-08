package edu.ksu.canvas.requestOptions;

public class GetSelectiveDataOptions extends BaseOptions {

    private String itemId;
    private Long migrationId;
    private String type;

    /**
     * Constructs object to hold API options for the selective data calls.
     *
     * @param itemId             The id of the item (course, user, group, account) involved in the migration process
     * @param migrationId        The id of the migration
     * @param type               Optional value for filtering the available selective data by type, i.e. assignments, quizzes, discussion_topics, etc.
     */
    public GetSelectiveDataOptions(final String itemId, final Long migrationId, String type) {
        this.itemId = itemId;
        this.migrationId = migrationId;
        this.type = type;
        if(type != null) {
            addSingleItem("type", type);
        }
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    public Long getMigrationId() {
        return migrationId;
    }

    public void setMigrationId(final Long migrationId) {
        this.migrationId = migrationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

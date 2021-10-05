package edu.ksu.canvas.requestOptions;

public class CreateContentMigrationOptions<T extends CreateContentMigrationOptions<T>> extends BaseOptions {

    public enum MigrationType {
        course_copy_importer, zip_file_importer, common_cartridge_importer, canvas_cartridge_importer, qti_converter, moodle_converter;

        @Override
        public String toString() { return name().toLowerCase(); }

    }

    protected final MigrationType migrationType;

    /**
     * Constructs object to hold API options for the creating a content migration.
     * A course content migration has its own options class, see CreateCourseContentMigrationOptions.
     * @param migrationType Migration type, see enum options
     */
    public CreateContentMigrationOptions(MigrationType migrationType) {
        this.migrationType = migrationType;
        addSingleItem("migration_type", migrationType.toString().toLowerCase());
    }

    /**
     * Constructs object to hold API options for the creating a content migration.
     * A course content migration has its own options class, see CreateCourseContentMigrationOptions.
     * @param sourceCourseId The id of the source course 
     * @param migrationType Migration type, see enum options
     */
    public CreateContentMigrationOptions(String sourceCourseId, MigrationType migrationType) {
        this.migrationType = migrationType;
        addSingleItem("settings[source_course_id]", sourceCourseId);
        addSingleItem("migration_type", migrationType.toString().toLowerCase());
    }

    public MigrationType getMigrationType() {
        return migrationType;
    }

    /** The solution for the unchecked cast warning. */
    protected T getThis() {
        return (T) this;
    }

    public T preAttachment(String name) {
        addSingleItem("pre_attachment[name]", name);
        return getThis();
    }

    public T addFileUploadProperty(String name, String value) {
        addSingleItem("pre_attachment["+name+"]", value);
        return getThis();
    }

    public T fileUrl(String fileUrl) {
        addSingleItem("settings[file_url]", fileUrl);
        return getThis();
    }

    public T contentExport(String contentExportId) {
        addSingleItem("settings[content_export_id]", contentExportId);
        return getThis();
    }

    public T folder(String folderId) {
        addSingleItem("settings[folder_id]", folderId);
        return getThis();
    }

}
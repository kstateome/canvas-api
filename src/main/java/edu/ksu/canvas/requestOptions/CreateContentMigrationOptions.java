package edu.ksu.canvas.requestOptions;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreateContentMigrationOptions extends BaseOptions {

    public enum MigrationType {
        course_copy_importer, zip_file_importer, common_cartridge_importer, canvas_cartridge_importer, qti_converter, moodle_converter;

        @Override
        public String toString() { return name().toLowerCase(); }

    }

    protected final String sourceCourseId;

    protected final MigrationType migrationType;

    /**
     * Constructs object to hold API options for the creating a content migration.
     * A course content migration has its own options class, see CreateCourseContentMigrationOptions.
     * @param sourceCourseId The id of the source course 
     * @param migrationType Migration type, see enum options
     */
    public CreateContentMigrationOptions(String sourceCourseId, MigrationType migrationType) {
        this.sourceCourseId = sourceCourseId;
        this.migrationType = migrationType;
        addSingleItem("settings[source_course_id]", sourceCourseId);
        addSingleItem("migration_type", migrationType.toString().toLowerCase());
    }

    public String getSourceCourseId() {
        return sourceCourseId;
    }

    public MigrationType getMigrationType() {
        return migrationType;
    }

    public CreateContentMigrationOptions preAttachment(String name) {
        addSingleItem("pre_attachment[name]", name);
        return this;
    }

    public CreateContentMigrationOptions addFileUploadProperty(String name, String value) {
        addSingleItem("pre_attachment["+name+"]", value);
        return this;
    }

    public CreateContentMigrationOptions fileUrl(String fileUrl) {
        addSingleItem("settings[file_url]", fileUrl);
        return this;
    }

    public CreateContentMigrationOptions contentExport(String contentExportId) {
        addSingleItem("settings[content_export_id]", contentExportId);
        return this;
    }

    public CreateContentMigrationOptions folder(String folderId) {
        addSingleItem("settings[folder_id]", folderId);
        return this;
    }

}
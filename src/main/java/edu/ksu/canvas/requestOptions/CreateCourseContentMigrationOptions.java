package edu.ksu.canvas.requestOptions;

public class CreateCourseContentMigrationOptions extends BaseOptions {

    public enum MigrationType {
        course_copy_importer;
        // Only allow course copy for now.
        //CANVAS_CARTRIDGE_IMPORTER, COMMON_CARTRIDGE_IMPORTER,
        //ZIP_FILE_IMPORTER, QTI_CONVERTER, MOODLE_CONVERTER;

        @Override
        public String toString() { return name().toLowerCase(); }

    }

    private final String destinationCourseId;

    private final String sourceCourseId;

    private final MigrationType migrationType;
    
    public CreateCourseContentMigrationOptions(String destinationCourseId, String sourceCourseId, MigrationType migrationType) {
        this.destinationCourseId = destinationCourseId;
        this.sourceCourseId = sourceCourseId;
        this.migrationType = migrationType;
        addSingleItem("settings[source_course_id]", sourceCourseId);
        addSingleItem("migration_type", migrationType.toString().toLowerCase());
    }

    public String getDestinationCourseId() {
        return destinationCourseId;
    }

    public String getSourceCourseId() {
        return sourceCourseId;
    }

    public MigrationType getMigrationType() {
        return migrationType;
    }
}
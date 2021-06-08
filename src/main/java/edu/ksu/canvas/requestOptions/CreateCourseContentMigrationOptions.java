package edu.ksu.canvas.requestOptions;

import java.util.Date;

public class CreateCourseContentMigrationOptions extends CreateContentMigrationOptions {

    private final String destinationCourseId;

    private final boolean selectiveImport;

    /**
     * Constructs object to hold API options for the creating a course content migration.
     *
     * @param destinationCourseId      The id of the destination course
     * @param sourceCourseId           The id of the source course
     * @param migrationType            Course copy content
     * @param selectiveImport          Whether user wants to select specific items or to copy the whole course content
     * @param selectedData             Dynamic list of items that will be copied from the original course. See SelectiveData API for more info
     */
    public CreateCourseContentMigrationOptions(String destinationCourseId, String sourceCourseId, MigrationType migrationType, boolean selectiveImport, String... selectedData) {
        super(sourceCourseId, migrationType);
        this.destinationCourseId = destinationCourseId;
        this.selectiveImport = selectiveImport;
        addSingleItem("selective_import", Boolean.toString(selectiveImport));
        for(String item : selectedData) {
            addSingleItem(item, "1");
        }
    }

    public String getDestinationCourseId() {
        return destinationCourseId;
    }

    public boolean isSelectiveImport() {
        return selectiveImport;
    }

    public CreateContentMigrationOptions questionBank(Long questionBankId) {
        addSingleItem("settings[question_bank_id]", questionBankId.toString());
        return this;
    }

    public CreateContentMigrationOptions questionBankName(String questionBankName) {
        addSingleItem("settings[question_bank_name]", questionBankName);
        return this;
    }

    public CreateContentMigrationOptions overwriteQuizzes(Boolean overwriteQuizzes) {
        addSingleItem("settings[overwrite_quizzes]", overwriteQuizzes.toString());
        return this;
    }

    public CreateContentMigrationOptions insertIntoModule(Long insertIntoModuleId) {
        addSingleItem("settings[insert_into_module_id]", insertIntoModuleId.toString());
        return this;
    }

    public CreateContentMigrationOptions insertIntoModuleType(String insertIntoModuleType) {
        addSingleItem("settings[insert_into_module_type]", insertIntoModuleType);
        return this;
    }

    public CreateContentMigrationOptions insertIntoModulePosition(Long insertIntoModulePosition) {
        addSingleItem("settings[insert_into_module_position]", insertIntoModulePosition.toString());
        return this;
    }

    public CreateContentMigrationOptions moveToAssignmentGroup(Long moveToAssignmentGroup) {
        addSingleItem("settings[move_to_assignment_group_id]", moveToAssignmentGroup.toString());
        return this;
    }

    public CreateContentMigrationOptions shiftDates(Boolean shiftDates) {
        addSingleItem("date_shift_options[shift_dates]", shiftDates.toString());
        return this;
    }

    public CreateContentMigrationOptions removeDates(Boolean removeDates) {
        addSingleItem("date_shift_options[remove_dates]", removeDates.toString());
        return this;
    }

    public CreateContentMigrationOptions oldStartDate(Date oldStartDate) {
        addSingleItem("date_shift_options[old_start_date]", oldStartDate.toString());
        return this;
    }

    public CreateContentMigrationOptions oldEndDate(Date oldEndDate) {
        addSingleItem("date_shift_options[old_end_date]", oldEndDate.toString());
        return this;
    }

    public CreateContentMigrationOptions newStartDate(Date newStartDate) {
        addSingleItem("date_shift_options[new_start_date]", newStartDate.toString());
        return this;
    }

    public CreateContentMigrationOptions newEndDate(Date newEndDate) {
        addSingleItem("date_shift_options[new_end_date]", newEndDate.toString());
        return this;
    }

    public CreateContentMigrationOptions daySubstitutions(Integer dayX, Integer newDay) {
        addSingleItem("date_shift_options[day_substitutions]["+dayX+"]", newDay.toString());
        return this;
    }

}
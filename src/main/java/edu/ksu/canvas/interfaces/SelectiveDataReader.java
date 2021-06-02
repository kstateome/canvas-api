package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.SelectiveData;
import edu.ksu.canvas.requestOptions.GetSelectiveDataOptions;

import java.io.IOException;
import java.util.List;

public interface SelectiveDataReader extends CanvasReader<SelectiveData, SelectiveDataReader> {
    /**
     * Get selective data of a course migration.
     * @param options The destination item ID for the migration, the migration id and the type
     * @return List of selectable items for the migration
     * @throws IOException When there is an error communicating with Canvas
     */
    List<SelectiveData> getCourseSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException;

    /**
     * Get selective data of a user migration.
     * @param options The destination item ID for the migration, the migration id and the type
     * @return List of selectable items for the migration
     * @throws IOException When there is an error communicating with Canvas
     */
    List<SelectiveData> getUserSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException;

    /**
     * Get selective data of a group migration.
     * @param options The destination item ID for the migration, the migration id and the type
     * @return List of selectable items for the migration
     * @throws IOException When there is an error communicating with Canvas
     */
    List<SelectiveData> getGroupSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException;

    /**
     * Get selective data of an account migration.
     * @param options The destination item ID for the migration, the migration id and the type
     * @return List of selectable items for the migration
     * @throws IOException When there is an error communicating with Canvas
     */
    List<SelectiveData> getAccountSelectiveDataFromMigration(GetSelectiveDataOptions options) throws IOException;
}
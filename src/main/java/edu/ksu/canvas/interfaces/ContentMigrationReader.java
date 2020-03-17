package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;

import java.io.IOException;
import java.util.Optional;

public interface ContentMigrationReader extends CanvasReader<ContentMigration, ContentMigrationReader> {
    /**
     * Returns a content migration.
     * @param courseId The course ID for this API call
     * @param id The Canvas ID of the content migration to query
     * @return A ContentMigration object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<ContentMigration> getCourseContentMigration(String courseId, Integer id) throws IOException;
}
package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ContentMigrationReader extends CanvasReader<ContentMigration, ContentMigrationReader> {
    /**
     * Returns a course content migration.
     * @param courseId The course ID for this API call
     * @param id The Canvas ID of the content migration to query
     * @return A ContentMigration object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<ContentMigration> getCourseContentMigration(String courseId, Long id) throws IOException;

    /**
     * Returns a list of course content migrations.
     * @param courseId The course ID for this API call
     * @return A ContentMigration list
     * @throws IOException When there is an error communicating with Canvas
     */
    List<ContentMigration> getCourseContentMigrations(String courseId) throws IOException;

    /**
     * Returns a user content migration.
     * @param userId The user ID for this API call
     * @param id The Canvas ID of the content migration to query
     * @return A ContentMigration object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<ContentMigration> getUserContentMigration(String userId, Long id) throws IOException;

    /**
     * Returns a list of user content migrations.
     * @param userId The user ID for this API call
     * @return A ContentMigration list
     * @throws IOException When there is an error communicating with Canvas
     */
    List<ContentMigration> getUserContentMigrations(String userId) throws IOException;

    /**
     * Returns a group content migration.
     * @param groupId The group ID for this API call
     * @param id The Canvas ID of the content migration to query
     * @return A ContentMigration object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<ContentMigration> getGroupContentMigration(String groupId, Long id) throws IOException;

    /**
     * Returns a list of group content migrations.
     * @param groupId The group ID for this API call
     * @return A ContentMigration list
     * @throws IOException When there is an error communicating with Canvas
     */
    List<ContentMigration> getGroupContentMigrations(String groupId) throws IOException;

    /**
     * Returns a account content migration.
     * @param accountId The account ID for this API call
     * @param id The Canvas ID of the content migration to query
     * @return A ContentMigration object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<ContentMigration> getAccountContentMigration(String accountId, Long id) throws IOException;

    /**
     * Returns a list of account content migrations.
     * @param accountId The account ID for this API call
     * @return A ContentMigration list
     * @throws IOException When there is an error communicating with Canvas
     */
    List<ContentMigration> getAccountContentMigrations(String accountId) throws IOException;
}
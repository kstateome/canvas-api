package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.MigrationIssue;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MigrationIssueReader extends CanvasReader<MigrationIssue, MigrationIssueReader> {

    /**
     * Returns a list migration issues.
     * @param courseId The destination course ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @return List of migration issues
     * @throws IOException When there is an error communicating with Canvas
     */
    List<MigrationIssue> getCourseMigrationIssues(String courseId, Long migrationId) throws IOException;

    /**
     * Returns a migration issue.
     * @param courseId The destination course ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @param issueId The Canvas ID of the migration issue
     * @return A migration issue
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<MigrationIssue> getCourseMigrationIssue(String courseId, Long migrationId, Long issueId) throws IOException;

    /**
     * Returns a list migration issues.
     * @param userId The user ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @return List of migration issues
     * @throws IOException When there is an error communicating with Canvas
     */
    List<MigrationIssue> getUserMigrationIssues(String userId, Long migrationId) throws IOException;

    /**
     * Returns a migration issue.
     * @param userId The user ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @param issueId The Canvas ID of the migration issue
     * @return A migration issue
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<MigrationIssue> getUserMigrationIssue(String userId, Long migrationId, Long issueId) throws IOException;

    /**
     * Returns a list migration issues.
     * @param groupId The group ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @return List of migration issues
     * @throws IOException When there is an error communicating with Canvas
     */
    List<MigrationIssue> getGroupMigrationIssues(String groupId, Long migrationId) throws IOException;

    /**
     * Returns a migration issue.
     * @param groupId The group ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @param issueId The Canvas ID of the migration issue
     * @return A migration issue
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<MigrationIssue> getGroupMigrationIssue(String groupId, Long migrationId, Long issueId) throws IOException;

    /**
     * Returns a list migration issues.
     * @param accountId The account ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @return List of migration issues
     * @throws IOException When there is an error communicating with Canvas
     */
    List<MigrationIssue> getAccountMigrationIssues(String accountId, Long migrationId) throws IOException;

    /**
     * Returns a migration issue.
     * @param accountId The account ID for the migration
     * @param migrationId The Canvas ID of the content migration
     * @param issueId The Canvas ID of the migration issue
     * @return A migration issue
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<MigrationIssue> getAccountMigrationIssue(String accountId, Long migrationId, Long issueId) throws IOException;
}
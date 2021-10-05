package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.requestOptions.CreateContentMigrationOptions;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;

import java.io.IOException;

import java.util.Optional;

public interface ContentMigrationWriter extends CanvasWriter<ContentMigration, ContentMigrationWriter>{

    Optional<ContentMigration> createCourseContentMigration(CreateCourseContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateCourseContentMigration(Integer id, CreateCourseContentMigrationOptions options) throws IOException;

    Optional<ContentMigration> createUserContentMigration(String userId, CreateContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateUserContentMigration(String userId, Integer id, CreateContentMigrationOptions options) throws IOException;

    Optional<ContentMigration> createGroupContentMigration(String groupId, CreateContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateGroupContentMigration(String groupId, Integer id, CreateContentMigrationOptions options) throws IOException;

    Optional<ContentMigration> createAccountContentMigration(String accountId, CreateContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateAccountContentMigration(String accountId, Integer id, CreateContentMigrationOptions options) throws IOException;
}

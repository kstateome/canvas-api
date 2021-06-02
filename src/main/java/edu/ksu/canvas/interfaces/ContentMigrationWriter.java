package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.requestOptions.CreateContentMigrationOptions;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;

import java.io.IOException;

import java.util.Optional;

public interface ContentMigrationWriter extends CanvasWriter<ContentMigration, ContentMigrationWriter>{

    Optional<ContentMigration> createCourseContentMigration(CreateCourseContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateCourseContentMigration(Long id, CreateCourseContentMigrationOptions options) throws IOException;

    Optional<ContentMigration> createUserContentMigration(CreateContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateUserContentMigration(Long id, CreateContentMigrationOptions options) throws IOException;

    Optional<ContentMigration> createGroupContentMigration(CreateContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateGroupContentMigration(Long id, CreateContentMigrationOptions options) throws IOException;

    Optional<ContentMigration> createAccountContentMigration(CreateContentMigrationOptions options) throws IOException;
    Optional<ContentMigration> updateAccountContentMigration(Long id, CreateContentMigrationOptions options) throws IOException;
}

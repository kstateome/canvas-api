package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.requestOptions.CreateContentMigrationOptions;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.Optional;

public interface ContentMigrationWriter extends CanvasWriter<ContentMigration, ContentMigrationWriter>{

    Optional<ContentMigration> createCourseContentMigration(CreateCourseContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;
    Optional<ContentMigration> updateCourseContentMigration(Long id, CreateCourseContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;

    Optional<ContentMigration> createUserContentMigration(CreateContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;
    Optional<ContentMigration> updateUserContentMigration(Long id, CreateContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;

    Optional<ContentMigration> createGroupContentMigration(CreateContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;
    Optional<ContentMigration> updateGroupContentMigration(Long id, CreateContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;

    Optional<ContentMigration> createAccountContentMigration(CreateContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;
    Optional<ContentMigration> updateAccountContentMigration(Long id, CreateContentMigrationOptions options) throws IOException, URISyntaxException, ParseException;
}

package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ContentMigration;
import edu.ksu.canvas.requestOptions.CreateCourseContentMigrationOptions;

import java.io.IOException;

import java.util.Optional;

public interface ContentMigrationWriter extends CanvasWriter<ContentMigration, ContentMigrationWriter>{

    Optional<ContentMigration> createCourseContentMigration(CreateCourseContentMigrationOptions options) throws IOException;

}

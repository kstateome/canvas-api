package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Course;

public interface Courses {

    public Optional<Course> retrieveCourse(String courseId) throws IOException;

}

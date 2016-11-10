package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Enrollment;

import java.io.IOException;
import java.util.List;

public interface EnrollmentReader extends CanvasReader<Enrollment, EnrollmentReader> {
    /**
     * Retrieve a given user's enrollments
     * @param userId User ID to query for enrollments
     * @return List of the user's enrollments
     * @throws InvalidOauthTokenException  When the supplied OAuth token is invalid
     * @throws IOException When there is an error communicating with Canvas
     */
     List<Enrollment> getUserEnrollments(String userId) throws InvalidOauthTokenException, IOException;


     List<Enrollment> getSectionEnrollments(String sectionId, List<EnrollmentType> enrollmentTypes) throws InvalidOauthTokenException, IOException;

}

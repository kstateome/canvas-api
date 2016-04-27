package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.EnrollmentType;
import edu.ksu.canvas.exception.InvalidOauthTokenException;
import edu.ksu.canvas.model.Enrollment;

import java.io.IOException;
import java.util.List;

/**
 * Created by prv on 10/8/15.
 */
public interface EnrollmentsReader extends CanvasReader<Enrollment, EnrollmentsReader> {
    /**
     *
     * @param user_Id
     * @return
     * @throws InvalidOauthTokenException
     * @throws IOException
     */
     List<Enrollment> getUserEnrollments(Integer user_Id) throws InvalidOauthTokenException, IOException;


     List<Enrollment> getSectionEnrollments(Integer sectionId, List<EnrollmentType> enrollmentTypes) throws InvalidOauthTokenException, IOException;

}

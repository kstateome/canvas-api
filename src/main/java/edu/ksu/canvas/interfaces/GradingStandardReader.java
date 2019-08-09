package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.GradingStandard;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GradingStandardReader extends CanvasReader<GradingStandard, GradingStandardReader> {

    /**
     * List grading standards associated with a given course
     * @param courseId The Canvas course ID or sis_course_id:xxxx
     * @return A list of grading standards in the course
     * @throws IOException if there is an error communicating with Canvas
     */
    List<GradingStandard> listGradingStandardsInCourse(String courseId) throws IOException;

    /**
     * List grading standards associated with an account
     * @param accountId The Canvas account ID or sis_account_id:xxxx
     * @return A list of grading standards in the account
     * @throws IOException if there is an error communicating with Canvas
     */
    List<GradingStandard> listGradingStandardsInAccount(String accountId) throws IOException;

    /**
     * Get a specific grading standard from within a course
     * @param courseId Canvas course ID or sis_course_id:xxxx
     * @param gradingStandardId Canvas grading standard ID
     * @return The requested grading standard
     * @throws IOException if there is an error communicating with Canvas
     */
    Optional<GradingStandard> getGradingStandardInCourse(String courseId, Long gradingStandardId) throws IOException;

    /**
     * Get a specific grading standard from within an account
     * @param accountId Canvas account ID or sis_account_id:xxxx
     * @param gradingStandardId Canvas grading standard ID
     * @return The requested grading standard
     * @throws IOException if there is an error communicating with Canvas
     */
    Optional<GradingStandard> getGradingStandardInAccount(String accountId, Long gradingStandardId) throws IOException;
}

package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.EnrollmentTerm;
import edu.ksu.canvas.requestOptions.GetEnrollmentTermOptions;

import java.io.IOException;
import java.util.List;

public interface EnrollmentTermReader  extends CanvasReader<EnrollmentTerm, EnrollmentTermReader> {

    /**
     * Retrieve the enrollment terms for a single account.
     * @param options API options available to this call
     * @return the list of enrollment terms for that account
     * @throws IOException When there is an error communicating with Canvas
     */
    List<EnrollmentTerm> getEnrollmentTerms(GetEnrollmentTermOptions options) throws IOException;

}

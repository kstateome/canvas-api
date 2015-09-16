package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.model.Section;
/**
 * Methods to read information from and about sections
 */
public interface SectionReader {

    /**
     * Return a list of sections in a given course
     * @param courseId Canvas ID of course
     * @param includes Optional information to include in the returned sections
     * @return List of sections
     * @throws IOException
     */
    public List<Section> listCourseSections(Integer courseId, List<SectionIncludes> includes) throws IOException;
}

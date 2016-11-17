package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.enums.SectionIncludes;
import edu.ksu.canvas.model.Section;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
/**
 * Methods to read information from and about sections
 */
public interface SectionReader extends CanvasReader<Section, SectionReader> {

    /**
     * Return a list of sections in a given course
     * @param courseId Canvas ID of course
     * @param includes Optional information to include in the returned sections
     * @return List of sections
     * @throws IOException When there is an error communicating with Canvas
     */
     List<Section> listCourseSections(String courseId, List<SectionIncludes> includes) throws IOException;

    /**
     * Return a single section with a given section ID
     * @param sectionId Section ID of section
     * @return The section
     * @throws IOException When there is an error communicating with Canvas
     */
     Optional<Section> getSingleSection(String sectionId) throws IOException;

}

package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.model.Section;

/**
 * Created by japshvincent on 4/28/16.
 */
public interface SectionWriter extends CanvasWriter<Section, SectionWriter> {

    /**
     * Create a new section in Canvas.
     *
     * @param courseId identifies the parent course we are adding this section to
     * @param section a section object containing the information needed to create
     *         the section in Canvas
     * @param enableSisReactivation when true, will first try to re-activate a
     *         deleted section with matching sis_section_id if possible
     * @return the newly created section
     * @throws IOException
     */
    Optional<Section> createSection(String courseId, Section section, boolean enableSisReactivation) throws IOException;

    /**
     * Delete an existing section in Canvas.
     *
     * @param sectionId identifies the section to delete
     * @return true if the delete was successful
     * @return the section object that was deleted
     * @throws IOException
     */
    Optional<Section> deleteSection(String sectionId) throws IOException;
}

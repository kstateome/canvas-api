package edu.ksu.canvas.requestOptions;

import java.util.List;

public class GetRubricOptions extends BaseOptions{

    private String canvasId;
    private Long rubricId;

    public enum Include {
        ASSESSMENTS, GRADED_ASSESSMENTS, PEER_ASSESSMENTS,
        ASSOCIATIONS, ASSIGNMENT_ASSOCIATIONS, COURSE_ASSOCIATIONS, ACCOUNT_ASSOCIATIONS;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public enum Style {
        FULL, COMMENTS_ONLY;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public GetRubricOptions(String canvasId, Long rubricId) {
        this.canvasId = canvasId;
        this.rubricId = rubricId;
    }

    public String getCanvasId() {
        return canvasId;
    }

    public Long getRubricId() {
        return rubricId;
    }

    /**
     * Include optional values in the response
     * @param includes List of optional attributes to include in the response
     * @return This object to allow adding more options
     */
    public GetRubricOptions includes(final List<Include> includes){
        addEnumList("include[]", includes);
        return this;
    }

    /**
     * Applicable only if assessments are being returned. If included, returns either all criteria data associated
     * with the assessment, or just the comments. If not included, both data and comments are omitted.
     * @param style Specify if you want comment or full assessment data
     * @return This object to allow adding more options
     */
    public GetRubricOptions style(Style style) {
        addSingleItem("style", style.toString());
        return this;
    }
}

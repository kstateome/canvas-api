package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.List;

@CanvasObject(postKey = "")
public class GradingStandard extends BaseCanvasModel implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long contextId;
    private String ContextType;
    private List<GradingSchemeEntry> gradingScheme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getContextType() {
        return ContextType;
    }

    public void setContextType(String contextType) {
        ContextType = contextType;
    }

    public List<GradingSchemeEntry> getGradingScheme() {
        return gradingScheme;
    }

    public void setGradingScheme(List<GradingSchemeEntry> gradingScheme) {
        this.gradingScheme = gradingScheme;
    }


    public class GradingSchemeEntry implements Serializable {
        public static final long serialVersionUID = 1L;

        private String name;
        private Double value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }
    }
}

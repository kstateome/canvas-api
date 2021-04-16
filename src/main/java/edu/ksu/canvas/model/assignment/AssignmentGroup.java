package edu.ksu.canvas.model.assignment;

import java.io.Serializable;
import java.util.List;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.model.BaseCanvasModel;

public class AssignmentGroup extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    Integer id;
    String name;
    Integer position;
    Double groupWeight;
    String sisSourceId;
    List<Assignment> assignments;
    GradingRules rules;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "name", array = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "position", array = false)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @CanvasField(postKey = "group_weight", array = false)
    public Double getGroupWeight() {
        return groupWeight;
    }

    public void setGroupWeight(Double groupWeight) {
        this.groupWeight = groupWeight;
    }

    @CanvasField(postKey = "sis_source_id", array = false)
    public String getSisSourceId() {
        return sisSourceId;
    }

    public void setSisSourceId(String sisSourceId) {
        this.sisSourceId = sisSourceId;
    }

    /**
     * @return List of assignments in group. Only populated if explicitly requested via the include[] option
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * @return the grading rules that this Assignment Group has
     */
    public GradingRules getGradingRules() {
        return rules;
    }

    public void setGradingRules(GradingRules rules) {
        this.rules = rules;
    }

}

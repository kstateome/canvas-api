package edu.ksu.canvas.model.assignment;

import java.util.List;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.model.BaseCanvasModel;

public class AssignmentGroup extends BaseCanvasModel{

    Long id;
    String name;
    Long position;
    Float groupWeight;
    Long sisSourceId;
    List<Assignment> assignments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    @CanvasField(postKey = "group_weight", array = false)
    public Float getGroupWeight() {
        return groupWeight;
    }

    public void setGroupWeight(Float groupWeight) {
        this.groupWeight = groupWeight;
    }

    @CanvasField(postKey = "sis_source_id", array = false)
    public Long getSisSourceId() {
        return sisSourceId;
    }

    public void setSisSourceId(Long sisSourceId) {
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
}

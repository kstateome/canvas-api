package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.model.BaseCanvasModel;

public class AssignmentGroup extends BaseCanvasModel{

    Long id;
    String name;
    Long position;
    Long groupWeight;
    Long sisSourceId;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setPosition(long position) {
        this.position = position;
    }

    @CanvasField(postKey = "group_weight", array = false)
    public Long getGroupWeight() {
        return groupWeight;
    }

    public void setGroupWeight(long groupWeight) {
        this.groupWeight = groupWeight;
    }

    @CanvasField(postKey = "sis_source_id", array = false)
    public Long getSisSourceId() {
        return sisSourceId;
    }

    public void setSisSourceId(long sisSourceId) {
        this.sisSourceId = sisSourceId;
    }
}

package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

@CanvasObject(postKey = "")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public Long getGroupWeight() {
        return groupWeight;
    }

    public void setGroupWeight(long groupWeight) {
        this.groupWeight = groupWeight;
    }

    public Long getSisSourceId() {
        return sisSourceId;
    }

    public void setSisSourceId(long sisSourceId) {
        this.sisSourceId = sisSourceId;
    }
}

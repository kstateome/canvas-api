package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

@CanvasObject(postKey = "assignment_group")
public class AssignmentGroup extends BaseCanvasModel{

    long id;
    String name;
    long position;
    long group_weight;
    long sis_source_id;

    public long getId() {
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

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getGroup_weight() {
        return group_weight;
    }

    public void setGroup_weight(long group_weight) {
        this.group_weight = group_weight;
    }

    public long getSis_source_id() {
        return sis_source_id;
    }

    public void setSis_source_id(long sis_source_id) {
        this.sis_source_id = sis_source_id;
    }
}

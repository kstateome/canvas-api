package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

@CanvasObject(postKey = "")
public class AssignmentGroup extends BaseCanvasModel{

    Long id;
    String name;
    Long position;
    Long group_weight;
    Long sis_source_id;

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

    public Long getGroup_weight() {
        return group_weight;
    }

    public void setGroup_weight(long group_weight) {
        this.group_weight = group_weight;
    }

    public Long getSis_source_id() {
        return sis_source_id;
    }

    public void setSis_source_id(long sis_source_id) {
        this.sis_source_id = sis_source_id;
    }
}

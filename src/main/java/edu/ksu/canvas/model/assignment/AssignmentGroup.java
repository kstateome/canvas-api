package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.model.BaseCanvasModel;

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
    public Long getGroup_weight() {
        return group_weight;
    }

    public void setGroup_weight(long group_weight) {
        this.group_weight = group_weight;
    }

    @CanvasField(postKey = "sis_source_id", array = false)
    public Long getSis_source_id() {
        return sis_source_id;
    }

    public void setSis_source_id(long sis_source_id) {
        this.sis_source_id = sis_source_id;
    }
}

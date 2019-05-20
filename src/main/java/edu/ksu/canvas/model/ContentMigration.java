package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;

@CanvasObject(postKey = "content_migration")
public class ContentMigration extends BaseCanvasModel implements Serializable {
    private Integer id;
    private String type;
    private String migrationType;
    private String name;
    private String worksflowState;

    @CanvasField(postKey = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @CanvasField(postKey = "migration_type")
    public String getMigrationType() {
        return migrationType;
    }

    public void setMigrationType(String migrationType) {
        this.migrationType = migrationType;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "workflow_state")
    public String getWorksflowState() {
        return worksflowState;
    }

    public void setWorksflowState(String worksflowState) {
        this.worksflowState = worksflowState;
    }
}
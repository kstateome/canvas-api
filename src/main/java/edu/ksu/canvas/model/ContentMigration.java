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
    private String progressUrl;
    private String workflowState;
    private Integer migrationIssuesCount;

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

    @CanvasField(postKey = "progress_url")
    public String getProgressUrl() {
        return progressUrl;
    }

    public void setProgressUrl(String progressUrl) {
        this.progressUrl = progressUrl;
    }

    @CanvasField(postKey = "workflow_state")
    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }
    
    public Integer getMigrationIssuesCount() {
		return migrationIssuesCount;
	}

	public void setMigrationIssuesCount(Integer migrationIssuesCount) {
		this.migrationIssuesCount = migrationIssuesCount;
	}

}
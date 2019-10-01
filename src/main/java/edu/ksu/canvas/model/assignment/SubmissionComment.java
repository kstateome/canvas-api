package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;
import java.util.Date;

@CanvasObject(postKey = "submissioncomment")
public class SubmissionComment extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer authorId;
    private String authorName;
    private String comment;
    private Date createdAt;
    private Date editedAt;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "author_id", array = false)
    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @CanvasField(postKey = "author_name", array = false)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @CanvasField(postKey = "created_at", array = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @CanvasField(postKey = "edited_at", array = false)
    public Date getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Date editedAt) {
        this.editedAt = editedAt;
    }

}

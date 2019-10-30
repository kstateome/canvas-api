package edu.ksu.canvas.model.assignment;

import edu.ksu.canvas.model.BaseCanvasModel;

import java.io.Serializable;

public class SubmissionComment extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer submissionId;
    private Integer authorId;
    private String author_name;
    private String comment;
    private String createdAt;
    private String editedAt;
    private String mediaComment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(String editedAt) {
        this.editedAt = editedAt;
    }

    public String getMediaComment() {
        return mediaComment;
    }

    public void setMediaComment(String mediaComment) {
        this.mediaComment = mediaComment;
    }
}

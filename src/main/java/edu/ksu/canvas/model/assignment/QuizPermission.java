package edu.ksu.canvas.model.assignment;

import java.io.Serializable;

/**
 * Class to hold the permissions returned when querying quizzes.
 * Each one represents whether the user making the query has the permission described
 * See <a href="https://canvas.instructure.com/doc/api/quizzes.html#QuizPermissions">Canvas Quiz Permission</a> documentation.
 */

public class QuizPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean read;
    private Boolean submit;
    private Boolean create;
    private Boolean manage;
    private Boolean readStatistics;
    private Boolean reviewGrades;
    private Boolean update;

    public Boolean canRead() {
        return read;
    }

    public Boolean canSubmit() {
        return submit;
    }

    public Boolean canCreate() {
        return create;
    }

    public Boolean canManage() {
        return manage;
    }

    public Boolean canReadStatistics() {
        return readStatistics;
    }

    public Boolean canReviewGrades() {
        return reviewGrades;
    }

    public Boolean canUpdate() {
        return update;
    }
}

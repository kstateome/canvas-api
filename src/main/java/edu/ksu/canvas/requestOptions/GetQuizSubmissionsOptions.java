package edu.ksu.canvas.requestOptions;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class GetQuizSubmissionsOptions extends BaseOptions {
    private String courseId;
    private String quizId;

    public enum Include {
        USER;

        public String toString() {
            return name().toLowerCase();
        }
    }

    public GetQuizSubmissionsOptions includes(final List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    public GetQuizSubmissionsOptions(final String courseId, final String quizId, final Include... includes) {
        this.courseId = courseId;
        this.quizId = quizId;
        if (includes.length > 0) {
            includes(ImmutableList.copyOf(includes));
        }
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(final String courseId) {
        this.courseId = courseId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(final String quizId) {
        this.quizId = quizId;
    }
}

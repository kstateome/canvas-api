package edu.ksu.canvas.requestOptions;

import java.util.Map;

/**
 * Rubric_Assessment not supported yet.
 */
public class SubmissionOptions extends BaseOptions {

    public class StudentSubmissionOption {
        private String textComment;
        private String postedGrade;
        private Boolean excuse;
        private Boolean groupComment;
        private String mediaCommentId;
        private String mediaCommentType;

        public StudentSubmissionOption(String textComment, String postedGrade, Boolean excuse, Boolean groupComment, String mediaCommentId, String mediaCommentType) {
            this.textComment = textComment;
            this.postedGrade = postedGrade;
            this.excuse = excuse;
            this.groupComment = groupComment;
            this.mediaCommentId = mediaCommentId;
            this.mediaCommentType = mediaCommentType;
        }


        public String getTextComment() {
            return textComment;
        }

        public void setTextComment(String textComment) {
            this.textComment = textComment;
        }

        public String getPostedGrade() {
            return postedGrade;
        }

        public void setPostedGrade(String postedGrade) {
            this.postedGrade = postedGrade;
        }

        public Boolean getExcuse() {
            return excuse;
        }

        public void setExcuse(Boolean excuse) {
            this.excuse = excuse;
        }

        public Boolean getGroupComment() {
            return groupComment;
        }

        public void setGroupComment(Boolean groupComment) {
            this.groupComment = groupComment;
        }

        public String getMediaCommentId() {
            return mediaCommentId;
        }

        public void setMediaCommentId(String mediaCommentId) {
            this.mediaCommentId = mediaCommentId;
        }

        public String getMediaCommentType() {
            return mediaCommentType;
        }

        public void setMediaCommentType(String mediaCommentType) {
            this.mediaCommentType = mediaCommentType;
        }
    }

    private Integer courseId;
    private Integer sectionId;
    private Integer assignmentId;
    private Map<Integer, StudentSubmissionOption> studentSubmissionOptionMap;

    public SubmissionOptions(Integer courseId, Integer sectionId, Integer assignemnetId, Map<Integer, StudentSubmissionOption> studentSubmissionOptionMap) {
        this.courseId = courseId;
        this.sectionId = sectionId;
        this.assignmentId = assignemnetId;
        this.studentSubmissionOptionMap = studentSubmissionOptionMap;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Map<Integer, StudentSubmissionOption> getStudentSubmissionOptionMap() {
        return studentSubmissionOptionMap;
    }

    public void setStudentSubmissionOptionMap(Map<Integer, StudentSubmissionOption> studentSubmissionOptionMap) {
        this.studentSubmissionOptionMap = studentSubmissionOptionMap;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public StudentSubmissionOption createStudentSubmissionOption(String comments, String grade, Boolean excuse, Boolean groupComment, String mediaCommentId, String mediaCommentType) {
        return new StudentSubmissionOption(comments, grade, excuse, groupComment, mediaCommentId, mediaCommentType);
    }
}

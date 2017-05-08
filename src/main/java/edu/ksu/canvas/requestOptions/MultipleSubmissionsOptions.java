package edu.ksu.canvas.requestOptions;

import java.util.Map;

/**
 * Rubric_Assessment not supported yet.
 */
public class MultipleSubmissionsOptions extends BaseOptions {

    private String objectId;
    private Integer assignmentId;
    private Map<String, StudentSubmissionOption> studentSubmissionOptionMap;

    public MultipleSubmissionsOptions(String objectId, Integer assignemnetId, Map<String, StudentSubmissionOption> studentSubmissionOptionMap) {
        this.objectId = objectId;
        this.assignmentId = assignemnetId;
        this.studentSubmissionOptionMap = studentSubmissionOptionMap;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Map<String, StudentSubmissionOption> getStudentSubmissionOptionMap() {
        return studentSubmissionOptionMap;
    }

    public void setStudentSubmissionOptionMap(Map<String, StudentSubmissionOption> studentSubmissionOptionMap) {
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
}

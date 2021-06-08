package edu.ksu.canvas.model;

import com.google.gson.annotations.SerializedName;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Class to represent Canvas courses.
 * See <a href="https://canvas.instructure.com/doc/api/courses.html#Course">Canvas courses</a> documentation.
 */
@CanvasObject(postKey = "course")
public class Course extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long accountId;
    private String courseCode;
    private String defaultView;
    private Long id;
    private String name;
    private Date createdAt;
    private Date startAt;
    private Date endAt;
    private Boolean publicSyllabus;
    private Long storageQuotaMb;
    private Boolean hideFinalGrades;
    private Boolean applyAssignmentGroupWeights;
    private String sisCourseId;
    private String integrationId;
    private String workflowState;
    private Long totalStudents;
    private Long enrollmentTermId;
    private Boolean restrictEnrollmentsToCourseDates;
    private Long gradingStandardId;
    private String subaccountName;
    private String license;
    private Boolean isPublic;
    private Boolean isPublicToAuthUsers;
    private Boolean publicSyllabusToAuth;
    private Boolean allowStudentWikiEdits;
    private Boolean allowWikiComments;
    private Boolean allowStudentForumAttachments;
    private Boolean openEnrollment;
    private Boolean selfEnrollment;
    private Boolean blueprint;
    private Boolean concluded;
    private String termId;
    private String timeZone;
    private Boolean offer;
    private Boolean enrollMe;
    private String syllabusBody;
    private String courseFormat;
    private Boolean enableSisReactivation;
    private Account account;
    // Only used when asking for the course image
    private String imageDownloadUrl;
    // Only used when setting course image
    private Long imageId;
    private String imageUrl;
    private String publicDescription;
    // Only used when pushing a course status change to Canvas
    private CourseEvent event;

    private List<Section> sections;
    private List<Enrollment> enrollments;
    private List<UserDisplay> teachers;

    @SerializedName("term")
    private EnrollmentTerm enrollmentTerm;

    public enum CourseEvent {
        claim, offer, conclude, delete, undelete;
    }

    public long getEnrollmentTermId() {
        return enrollmentTermId;
    }

    public void setEnrollmentTermId(long enrollmentTermId) {
        this.enrollmentTermId = enrollmentTermId;
    }

    @CanvasField(postKey = "account_id")
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @CanvasField(postKey = "course_code")
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDefaultView() {
        return defaultView;
    }

    public void setDefaultView(String defaultView) {
        this.defaultView = defaultView;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @CanvasField(postKey = "start_at")
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @CanvasField(postKey = "end_at")
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @CanvasField(postKey = "public_syllabus")
    public Boolean getPublicSyllabus() {
        return publicSyllabus;
    }

    public void setPublicSyllabus(Boolean publicSyllabus) {
        this.publicSyllabus = publicSyllabus;
    }

    public Long getStorageQuotaMb() {
        return storageQuotaMb;
    }

    public void setStorageQuotaMb(Long storageQuotaMb) {
        this.storageQuotaMb = storageQuotaMb;
    }

    @CanvasField(postKey = "hide_final_grades")
    public Boolean getHideFinalGrades() {
        return hideFinalGrades;
    }

    public void setHideFinalGrades(Boolean hideFinalGrades) {
        this.hideFinalGrades = hideFinalGrades;
    }

    @CanvasField(postKey = "apply_assignment_group_weights")
    public Boolean getApplyAssignmentGroupWeights() {
        return applyAssignmentGroupWeights;
    }

    public void setApplyAssignmentGroupWeights(Boolean applyAssignmentGroupWeights) {
        this.applyAssignmentGroupWeights = applyAssignmentGroupWeights;
    }

    @CanvasField(postKey = "sis_course_id")
    public String getSisCourseId() {
        return sisCourseId;
    }

    public void setSisCourseId(String sisCourseId) {
        this.sisCourseId = sisCourseId;
    }

    @CanvasField(postKey = "integration_id")
    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    public Long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(Long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public EnrollmentTerm getEnrollmentTerm() {
        return enrollmentTerm;
    }

    public void setEnrollmentTerm(EnrollmentTerm enrollmentTerm) {
        this.enrollmentTerm = enrollmentTerm;
    }

    @CanvasField(postKey = "sections")
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @CanvasField(postKey = "enrollments")
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @CanvasField(postKey = "restrict_enrollments_to_course_dates")
    public Boolean getRestrictEnrollmentsToCourseDates() {
        return restrictEnrollmentsToCourseDates;
    }

    public void setRestrictEnrollmentsToCourseDates(Boolean restrictEnrollmentsToCourseDates) {
        this.restrictEnrollmentsToCourseDates = restrictEnrollmentsToCourseDates;
    }

    @CanvasField(postKey = "grading_standard_id")
    public Long getGradingStandardId() {
        return gradingStandardId;
    }

    public void setGradingStandardId(Long gradingStandardId) {
        this.gradingStandardId = gradingStandardId;
    }

    @CanvasField(postKey = "subaccount_name")
    public String getSubaccountName() {
        return subaccountName;
    }

    public void setSubaccountName(String subaccountName) {
        this.subaccountName = subaccountName;
    }

    @CanvasField(postKey = "teachers")
    public List<UserDisplay> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<UserDisplay> teachers) {
        this.teachers = teachers;
    }

    @CanvasField(postKey = "license")
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @CanvasField(postKey = "is_public")
    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    @CanvasField(postKey = "is_public_to_auth_users")
    public Boolean getIsPublicToAuthUsers() {
        return isPublicToAuthUsers;
    }

    public void setIsPublicToAuthUsers(Boolean isPublicToAuthUsers) {
        this.isPublicToAuthUsers = isPublicToAuthUsers;
    }

    @CanvasField(postKey = "public_syllabus_to_auth")
    public Boolean getPublicSyllabusToAuth() {
        return publicSyllabusToAuth;
    }

    public void setPublicSyllabusToAuth(Boolean publicSyllabusToAuth) {
        this.publicSyllabusToAuth = publicSyllabusToAuth;
    }

    @CanvasField(postKey = "allow_student_wiki_edits")
    public Boolean getAllowStudentWikiEdits() {
        return allowStudentWikiEdits;
    }

    public void setAllowStudentWikiEdits(Boolean allowStudentWikiEdits) {
        this.allowStudentWikiEdits = allowStudentWikiEdits;
    }

    @CanvasField(postKey = "allow_wiki_comments")
    public Boolean getAllowWikiComments() {
        return allowWikiComments;
    }

    public void setAllowWikiComments(Boolean allowWikiComments) {
        this.allowWikiComments = allowWikiComments;
    }

    @CanvasField(postKey = "allow_student_forum_attachments")
    public Boolean getAllowStudentForumAttachments() {
        return allowStudentForumAttachments;
    }

    public void setAllowStudentForumAttachments(Boolean allowStudentForumAttachments) {
        this.allowStudentForumAttachments = allowStudentForumAttachments;
    }

    @CanvasField(postKey = "open_enrollment")
    public Boolean getOpenEnrollment() {
        return openEnrollment;
    }

    public void setOpenEnrollment(Boolean openEnrollment) {
        this.openEnrollment = openEnrollment;
    }

    @CanvasField(postKey = "self_enrollment")
    public Boolean getSelfEnrollment() {
        return selfEnrollment;
    }

    public void setSelfEnrollment(Boolean selfEnrollment) {
        this.selfEnrollment = selfEnrollment;
    }

    @CanvasField(postKey = "term_id")
    public String getTermId() {
        return termId;
    }

    public void setTermId(String termId) {
        this.termId = termId;
    }

    @CanvasField(postKey = "time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

    public Boolean getEnrollMe() {
        return enrollMe;
    }

    public void setEnrollMe(Boolean enrollMe) {
        this.enrollMe = enrollMe;
    }

    @CanvasField(postKey = "syllabus_body")
    public String getSyllabusBody() {
        return syllabusBody;
    }

    public void setSyllabusBody(String syllabusBody) {
        this.syllabusBody = syllabusBody;
    }

    @CanvasField(postKey = "course_format")
    public String getCourseFormat() {
        return courseFormat;
    }

    public void setCourseFormat(String courseFormat) {
        this.courseFormat = courseFormat;
    }

    public Boolean getEnableSisReactivation() {
        return enableSisReactivation;
    }

    public void setEnableSisReactivation(Boolean enableSisReactivation) {
        this.enableSisReactivation = enableSisReactivation;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @CanvasField(postKey = "image_id")
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @CanvasField(postKey = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Get the URL to download the course image, this is optional data and you need to request that Canvas
     * include it.
     * @see edu.ksu.canvas.requestOptions.GetSingleCourseOptions.Include#COURSE_IMAGE
     * @return The public URL to download the course image.
     */
    public String getImageDownloadUrl() {
        return imageDownloadUrl;
    }

    public void setImageDownloadUrl(String imageDownloadUrl) {
        this.imageDownloadUrl = imageDownloadUrl;
    }

    @CanvasField(postKey = "blueprint")
    public Boolean getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Boolean blueprint) {
        this.blueprint = blueprint;
    }

    public Boolean getConcluded() {
        return concluded;
    }

    @CanvasField(postKey = "public_description")
    public String getPublicDescription() {
        return publicDescription;
    }

    public void setPublicDescription(String publicDescription) {
        this.publicDescription = publicDescription;
    }

    public CourseEvent getEvent() {
        return event;
    }

    /**
     * Cause a course status event when updating course in Canvas.
     *
     * See "event" parameter for details: https://canvas.instructure.com/doc/api/courses.html#method.courses.update
     * @param event The event to trigger (publish, conclude, etc)
     */
    public void setEvent(CourseEvent event) {
        this.event = event;
    }
}

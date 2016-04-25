package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.List;

/**
 * Class to represent Canvas assigmnents.
 * See <a href="https://canvas.instructure.com/doc/api/assignments.html#Assignment">Canvas assignment</a> documentation.
 */
@CanvasObject(postKey = "assignment")
public class Assignment extends BaseCanvasModel implements Serializable{

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String description;
    private String created_at;
    private String updated_at;
    private String due_at;
    private String lock_at;
    private String unlock_at;
    private String all_dates;
    private String course_id;
    private String html_url;
    private String assingment_group_id;
    private String[] allowed_extensions;
    private boolean turnitin_enabled;
    private String turnitin_settings;
    private boolean grade_group_students_individually;
    private String external_tool_tag_attributes;
    private boolean peer_reviews;
    private boolean automatic_peer_reviews;
    private String peer_review_count;
    private String peer_reviews_assign_at;
    private String group_category_id;
    private String needs_grading_count;
    private String needs_grading_count_by_section;
    private String position;
    private boolean post_to_sis;
    private String integration_id;
    private String integration_data;
    private String muted;
    private String points_possible;
    private List<String> submission_types;
    private String grading_type;
    private String grading_standard_id;
    private boolean published;
    private boolean unpublishable;
    private boolean only_visible_to_overrides;
    private boolean locked_for_user;
    private boolean notify_of_update;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @CanvasField(postKey = "due_at")
    public String getDue_at() {
        return due_at;
    }

    public void setDue_at(String due_at) {
        this.due_at = due_at;
    }

    @CanvasField(postKey = "lock_at")
    public String getLock_at() {
        return lock_at;
    }

    public void setLock_at(String lock_at) {
        this.lock_at = lock_at;
    }

    @CanvasField(postKey = "unlock_at")
    public String getUnlock_at() {
        return unlock_at;
    }

    public void setUnlock_at(String unlock_at) {
        this.unlock_at = unlock_at;
    }

    public String getAll_dates() {
        return all_dates;
    }

    public void setAll_dates(String all_dates) {
        this.all_dates = all_dates;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getAssingment_group_id() {
        return assingment_group_id;
    }

    public void setAssingment_group_id(String assingment_group_id) {
        this.assingment_group_id = assingment_group_id;
    }

    public String[] getAllowed_extensions() {
        return allowed_extensions;
    }

    public void setAllowed_extensions(String[] allowed_extensions) {
        this.allowed_extensions = allowed_extensions;
    }

    @CanvasField(postKey = "turnitin_enabled")
    public boolean isTurnitin_enabled() {
        return turnitin_enabled;
    }

    public void setTurnitin_enabled(boolean turnitin_enabled) {
        this.turnitin_enabled = turnitin_enabled;
    }

    public String getTurnitin_settings() {
        return turnitin_settings;
    }

    public void setTurnitin_settings(String turnitin_settings) {
        this.turnitin_settings = turnitin_settings;
    }

    @CanvasField(postKey = "grade_group_students_individually")
    public boolean isGrade_group_students_individually() {
        return grade_group_students_individually;
    }

    public void setGrade_group_students_individually(boolean grade_group_students_individually) {
        this.grade_group_students_individually = grade_group_students_individually;
    }

    public String getExternal_tool_tag_attributes() {
        return external_tool_tag_attributes;
    }

    public void setExternal_tool_tag_attributes(String external_tool_tag_attributes) {
        this.external_tool_tag_attributes = external_tool_tag_attributes;
    }

    @CanvasField(postKey = "peer_reviews")
    public boolean isPeer_reviews() {
        return peer_reviews;
    }

    public void setPeer_reviews(boolean peer_reviews) {
        this.peer_reviews = peer_reviews;
    }

    @CanvasField(postKey = "automatic_peer_reviews")
    public boolean isAutomatic_peer_reviews() {
        return automatic_peer_reviews;
    }

    public void setAutomatic_peer_reviews(boolean automatic_peer_reviews) {
        this.automatic_peer_reviews = automatic_peer_reviews;
    }

    public String getPeer_review_count() {
        return peer_review_count;
    }

    public void setPeer_review_count(String peer_review_count) {
        this.peer_review_count = peer_review_count;
    }

    public String getPeer_reviews_assign_at() {
        return peer_reviews_assign_at;
    }

    public void setPeer_reviews_assign_at(String peer_reviews_assign_at) {
        this.peer_reviews_assign_at = peer_reviews_assign_at;
    }

    @CanvasField(postKey = "group_category_id")
    public String getGroup_category_id() {
        return group_category_id;
    }

    public void setGroup_category_id(String group_category_id) {
        this.group_category_id = group_category_id;
    }

    public String getNeeds_grading_count() {
        return needs_grading_count;
    }

    public void setNeeds_grading_count(String needs_grading_count) {
        this.needs_grading_count = needs_grading_count;
    }

    public String getNeeds_grading_count_by_section() {
        return needs_grading_count_by_section;
    }

    public void setNeeds_grading_count_by_section(String needs_grading_count_by_section) {
        this.needs_grading_count_by_section = needs_grading_count_by_section;
    }

    @CanvasField(postKey = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public boolean isPost_to_sis() {
        return post_to_sis;
    }

    public void setPost_to_sis(boolean post_to_sis) {
        this.post_to_sis = post_to_sis;
    }

    @CanvasField(postKey = "integration_id")
    public String getIntegration_id() {
        return integration_id;
    }

    public void setIntegration_id(String integration_id) {
        this.integration_id = integration_id;
    }

    @CanvasField(postKey = "integration_data")
    public String getIntegration_data() {
        return integration_data;
    }

    public void setIntegration_data(String integration_data) {
        this.integration_data = integration_data;
    }

    @CanvasField(postKey = "muted")
    public String getMuted() {
        return muted;
    }

    public void setMuted(String muted) {
        this.muted = muted;
    }

    @CanvasField(postKey = "points_possible")
    public String getPoints_possible() {
        return points_possible;
    }

    public void setPoints_possible(String points_possible) {
        this.points_possible = points_possible;
    }

    public List<String> getSubmission_types() {
        return submission_types;
    }

    public void setSubmission_types(List<String> submission_types) {
        this.submission_types = submission_types;
    }

    @CanvasField(postKey = "grading_type")
    public String getGrading_type() {
        return grading_type;
    }

    public void setGrading_type(String grading_type) {
        this.grading_type = grading_type;
    }

    @CanvasField(postKey = "grading_standard_id")
    public String getGrading_standard_id() {
        return grading_standard_id;
    }

    public void setGrading_standard_id(String grading_standard_id) {
        this.grading_standard_id = grading_standard_id;
    }

    @CanvasField(postKey = "published")
    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isUnpublishable() {
        return unpublishable;
    }

    @CanvasField(postKey = "only_visible_to_overrides")
    public boolean isOnly_visible_to_overrides() {
        return only_visible_to_overrides;
    }

    public void setOnly_visible_to_overrides(boolean only_visible_to_overrides) {
        this.only_visible_to_overrides = only_visible_to_overrides;
    }

    public boolean isLocked_for_user() {
        return locked_for_user;
    }

    public void setLocked_for_user(boolean locked_for_user) {
        this.locked_for_user = locked_for_user;
    }

    @CanvasField(postKey = "notify_of_update")
    public boolean isNotify_of_update() {
        return notify_of_update;
    }

    public void setNotify_of_update(boolean notify_of_update) {
        this.notify_of_update = notify_of_update;
    }
}

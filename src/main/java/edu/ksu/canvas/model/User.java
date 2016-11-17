package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.List;

/**
 * Class to represent Canvas users.
 * See the <a href="https://canvas.instructure.com/doc/api/users.html#User">Canvas User</a> documentation.
 */
@CanvasObject(postKey = "user")
public class User extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String sortableName;
    private String shortName;
    private String sisUserId;
    private String sisImportId;
    private String loginId;
    private String avatarUrl;
    private List<Enrollment> enrollments;
    private String email;
    private String locale;
    private String lastLogin;
    private String timeZone;
    private String bio;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "sortable_name")
    public String getSortableName() {
        return sortableName;
    }

    public void setSortableName(String sortableName) {
        this.sortableName = sortableName;
    }
    @CanvasField(postKey = "short_name")
    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @CanvasField(overrideObjectKey = "pseudonym", postKey = "sis_user_id")
    public String getSisUserId() {
        return sisUserId;
    }

    public void setSisUserId(String sisUserId) {
        this.sisUserId = sisUserId;
    }

    public String getSisImportId() {
        return sisImportId;
    }

    public void setSisImportId(String sisImportId) {
        this.sisImportId = sisImportId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @CanvasField(postKey = "avatar][url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @CanvasField(postKey = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    @CanvasField(postKey = "time_zone")
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
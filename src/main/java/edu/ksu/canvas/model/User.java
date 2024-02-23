package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent Canvas users.
 * See the <a href="https://canvas.instructure.com/doc/api/users.html#User">Canvas User</a> documentation.
 */
@CanvasObject(postKey = "user")
public class User extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 2L;

    private long id;
    private String name;
    private String firstName;
    private String lastName;
    private String sortableName;
    private String shortName;
    private String sisUserId;
    private String sisImportId;
    private String loginId;
    private String integrationId;
    private String authenticationProviderId;
    private String avatarUrl;
    private List<Enrollment> enrollments;
    private String email;
    private String locale;
    private String lastLogin;
    private String timeZone;
    private String bio;
    private Instant createdAt;
    private String confirmationUrl;

    public User() {
    }

    public User(User other) {
        this.id = other.id;
        this.name = other.name;
        this.sortableName = other.sortableName;
        this.shortName = other.shortName;
        this.sisUserId = other.sisUserId;
        this.sisImportId = other.sisImportId;
        this.loginId = other.loginId;
        this.integrationId = other.integrationId;
        this.authenticationProviderId = other.authenticationProviderId;
        this.avatarUrl = other.avatarUrl;
        this.enrollments = new ArrayList<>();
        for (Enrollment enrollment: other.enrollments) {
            // TODO
            //this.enrollments.add( new Enrollment(enrollment));
        }
        this.email = other.email;
        this.locale = other.locale;
        this.lastLogin = other.lastLogin;
        this.timeZone = other.timeZone;
        this.bio = other.bio;
        this.createdAt = other.createdAt;
        this.confirmationUrl = other.confirmationUrl;
        this.firstName = other.firstName;
        this.lastName = other.lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @CanvasField(postKey = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @CanvasField(postKey = "last_name")
    public String getLastName() {
        return lastName;
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

    @CanvasField(overrideObjectKey = "pseudonym", postKey = "unique_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @CanvasField(overrideObjectKey = "pseudonym", postKey = "integration_id")
    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(final String integrationId) {
        this.integrationId = integrationId;
    }

    @CanvasField(overrideObjectKey = "pseudonym", postKey = "authentication_provider_id")
    public String getAuthenticationProviderId() {
        return authenticationProviderId;
    }

    public void setAuthenticationProviderId(final String authenticationProviderId) {
        this.authenticationProviderId = authenticationProviderId;
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

    @CanvasField(postKey = "locale")
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

    public String getConfirmationUrl() {
        return confirmationUrl;
    }

    public void setConfirmationUrl(String confirmationUrl) {
        this.confirmationUrl = confirmationUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(sortableName, user.sortableName) && Objects.equals(shortName, user.shortName) && Objects.equals(loginId, user.loginId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sortableName, shortName, loginId);
    }
}

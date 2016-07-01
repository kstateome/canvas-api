package edu.ksu.canvas.requestmodel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to represent parameters to be passed to the "List active courses in account" API call.
 * See <a href="https://canvas.instructure.com/doc/api/accounts.html#method.accounts.courses_api">
 *              https://canvas.instructure.com/doc/api/accounts.html#method.accounts.courses_api</a>
 */
public class AccountCourseListOptions extends BaseOptions {

    private String accountId;

    public enum EnrollmentType {
        student, teacher, ta, observer, designer;
    }

    public enum State {
        created, claimed, available, completed, deleted, all;
    }

    public enum Includes {
        syllabus_body, term, course_progress, storage_quota_used_mb, total_students, teachers;
    }

    public AccountCourseListOptions(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    /**
     * If true, include only courses with at least one enrollment.
     * If false, include only courses with no enrollments.
     * If not present, do not filter on course enrollment status.
     * @param enrollments Whether to filter course list on enrollment status
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions withEnrollments(Boolean enrollments) {
        optionsMap.put("with_enrollments", Arrays.asList(enrollments.toString()));
        return this;
    }

    /**
     * If set, only return courses that have at least one user enrolled in in
     * the course with one of the specified enrollment types.
     * @param enrollmentTypes List of enrollment types to filter on
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions withEnrollmentTypes(List<EnrollmentType> enrollmentTypes) {
        List<String> enrollmentStrings = enrollmentTypes.stream().map(e -> e.name()).collect(Collectors.toList());
        optionsMap.put("enrollment_type[]", enrollmentStrings);
        return this;
    }

    /**
     * If true, include only published courses.
     * If false, exclude published courses.
     * If not present, do not filter on published status.
     * @param published Published status to filter on
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions onlyPublished(Boolean published) {
        optionsMap.put("published", Arrays.asList(published.toString()));
        return this;
    }

    /**
     * If true, include only completed courses (these may be in state 'completed', or their enrollment term may have ended).
     * If false, exclude completed courses.
     * If not present, do not filter on completed status.
     * @param completed Completed status to filter on
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions onlyCompleted(Boolean completed) {
        optionsMap.put("published", Arrays.asList(completed.toString()));
        return this;
    }

    /**
     * Only include courses taught by the given list of teacher user IDs
     * @param teacherList List of teacher user IDs to filter on (can "be sis_user_id:xxxx")
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions taughtByTeachers(List<String> teacherList) {
        optionsMap.put("by_teachers[]", teacherList);
        return this;
    }

    /**
     * Only include courses in sub-accounts identified by the given list of account IDs
     * @param accountList List of sub-account IDs (can be "sis_account_id:xxxx")
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions inSubaccount(List<String> accountList) {
        optionsMap.put("by_subaccounts[]", accountList);
        return this;
    }

    /**
     * Only include courses that are in the given list of states.
     * By default all states but "deleted" are returned.
     * @param stateList List of states to filter by
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions inState(List<State> stateList) {
        List<String> stateStrings = stateList.stream().map(s -> s.name()).collect(Collectors.toList());
        optionsMap.put("state[]", stateStrings);
        return this;
    }

    /**
     * Only include courses in the specified enrollment term
     * @param enrollmentTermId Term ID to filter by (can be "sis_term_id:xxxxx")
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions addEnrollmentTermId(String enrollmentTermId) {
        optionsMap.put("enrollment_term_id", Arrays.asList(enrollmentTermId));
        return this;
    }

    /**
     * Filter on a search term. Can be course name, code or full ID. Must be at least 3 characters
     * @param searchTerm Search term to filter by
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions searchTerm(String searchTerm) {
        if(searchTerm == null || searchTerm.length() < 3) {
            throw new IllegalArgumentException("Search term must be at least 3 characters");
        }
        optionsMap.put("search_term", Arrays.asList(searchTerm));
        return this;
    }

    /**
     * Optional elements to include in the returned courses.
     * “sections”, “needs_grading_count” and “total_scores” are not valid options at the account level
     * @param includes List of optional elements
     * @return This object to allow adding more options
     */
    public AccountCourseListOptions includes(List<Includes> includes) {
        List<String> includeStrings = includes.stream().map(i -> i.name()).collect(Collectors.toList());
        optionsMap.put("include[]", includeStrings);
        return this;
    }
}

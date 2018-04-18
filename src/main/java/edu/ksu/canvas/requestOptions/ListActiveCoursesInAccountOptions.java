package edu.ksu.canvas.requestOptions;

import java.util.List;

/**
 * A class to represent parameters to be passed to the "List active courses in account" API call.
 * See <a href="https://canvas.instructure.com/doc/api/accounts.html#method.accounts.courses_api">
 *              https://canvas.instructure.com/doc/api/accounts.html#method.accounts.courses_api</a>
 */
public class ListActiveCoursesInAccountOptions extends BaseOptions {

    private String accountId;

    public enum EnrollmentType {
        STUDENT, TEACHER, TA, OBSERVER, DESIGNER;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum State {
        CREATED, CLAIMED, AVAILABLE, COMPLETED, DELETED, ALL;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    /* Note: The SUBACCOUNT include is undocumented. When this was pointed out to Canvas, they changed how it works.
    The documented way to get subaccount info now is to use the "account" include which will add the entire
    account object to the response JSON. However I don't know when this change will get rolled out to production
    and the old undocumented behavior will still work so I am leaving this as is for now (April 2018) */
    public enum Include {
        SYLLABUS_BODY, TERM, COURSE_PROGRESS, STORAGE_QUOTA_USED_MB, TOTAL_STUDENTS, TEACHERS, SUBACCOUNT;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum SearchBy {
        COURSE, TEACHER;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public ListActiveCoursesInAccountOptions(String accountId) {
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
    public ListActiveCoursesInAccountOptions withEnrollments(Boolean enrollments) {
        addSingleItem("with_enrollments", enrollments.toString());
        return this;
    }

    /**
     * If set, only return courses that have at least one user enrolled in in
     * the course with one of the specified enrollment types.
     * @param enrollmentTypes List of enrollment types to filter on
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions withEnrollmentTypes(List<EnrollmentType> enrollmentTypes) {
        addEnumList("enrollment_type[]", enrollmentTypes);
        return this;
    }

    /**
     * If true, include only published courses.
     * If false, exclude published courses.
     * If not present, do not filter on published status.
     * @param published Published status to filter on
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions onlyPublished(Boolean published) {
        addSingleItem("published", published.toString());
        return this;
    }

    /**
     * If true, include only completed courses (these may be in state 'completed', or their enrollment term may have ended).
     * If false, exclude completed courses.
     * If not present, do not filter on completed status.
     * @param completed Completed status to filter on
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions onlyCompleted(Boolean completed) {
        addSingleItem("published", completed.toString());
        return this;
    }

    /**
     * Only include courses taught by the given list of teacher user IDs
     * @param teacherList List of teacher user IDs to filter on (can "be sis_user_id:xxxx")
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions taughtByTeachers(List<String> teacherList) {
        optionsMap.put("by_teachers[]", teacherList);
        return this;
    }

    /**
     * Only include courses in sub-accounts identified by the given list of account IDs
     * @param accountList List of sub-account IDs (can be "sis_account_id:xxxx")
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions inSubaccount(List<String> accountList) {
        optionsMap.put("by_subaccounts[]", accountList);
        return this;
    }

    /**
     * Only include courses that are in the given list of states.
     * By default all states but "deleted" are returned.
     * @param stateList List of states to filter by
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions inState(List<State> stateList) {
        addEnumList("state[]", stateList);
        return this;
    }

    /**
     * Only include courses in the specified enrollment term
     * @param enrollmentTermId Term ID to filter by (can be "sis_term_id:xxxxx")
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions addEnrollmentTermId(String enrollmentTermId) {
        addSingleItem("enrollment_term_id", enrollmentTermId);
        return this;
    }

    /**
     * Filter on a search term. Can be course name, code or full ID. Must be at least 3 characters
     * @param searchTerm Search term to filter by
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions searchTerm(String searchTerm) {
        if(searchTerm == null || searchTerm.length() < 3) {
            throw new IllegalArgumentException("Search term must be at least 3 characters");
        }
        addSingleItem("search_term", searchTerm);
        return this;
    }

    /**
     * Optional elements to include in the returned courses.
     * “sections”, “needs_grading_count” and “total_scores” are not valid options at the account level
     * @param includes List of optional elements
     * @return This object to allow adding more options
     */
    public ListActiveCoursesInAccountOptions includes(List<Include> includes) {
        addEnumList("include[]", includes);
        return this;
    }

    public ListActiveCoursesInAccountOptions searchBy(SearchBy searchType) {
        addSingleItem("search_by", searchType.toString());
        return this;
    }
}

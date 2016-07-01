package edu.ksu.canvas.requestmodel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public AccountCourseListOptions withEnrollments(Boolean enrollments) {
        optionsMap.put("with_enrollments", Arrays.asList(enrollments.toString()));
        return this;
    }

    public AccountCourseListOptions withEnrollmentTypes(List<EnrollmentType> enrollmentTypes) {
        List<String> enrollmentStrings = enrollmentTypes.stream().map(e -> e.name()).collect(Collectors.toList());
        optionsMap.put("enrollment_type[]", enrollmentStrings);
        return this;
    }

    public AccountCourseListOptions onlyPublished(Boolean published) {
        optionsMap.put("published", Arrays.asList(published.toString()));
        return this;
    }

    public AccountCourseListOptions onlyCompleted(Boolean completed) {
        optionsMap.put("published", Arrays.asList(completed.toString()));
        return this;
    }

    public AccountCourseListOptions taughtByTeachers(List<String> teacherList) {
        optionsMap.put("by_teachers[]", teacherList);
        return this;
    }

    public AccountCourseListOptions inSubaccount(List<String> accountList) {
        optionsMap.put("by_subaccounts[]", accountList);
        return this;
    }

    public AccountCourseListOptions hideEnrollmentlessCourses(Boolean hide) {
        optionsMap.put("hide_enrollmentless_courses", Arrays.asList(hide.toString()));
        return this;
    }

    public AccountCourseListOptions inState(List<State> stateList) {
        List<String> stateStrings = stateList.stream().map(s -> s.name()).collect(Collectors.toList());
        optionsMap.put("state[]", stateStrings);
        return this;
    }

    public AccountCourseListOptions addEnrollmentTermId(String enrollmentTermId) {
        optionsMap.put("enrollment_term_id", Arrays.asList(enrollmentTermId));
        return this;
    }

    public AccountCourseListOptions searchTerm(String searchTerm) {
        optionsMap.put("search_term", Arrays.asList(searchTerm));
        return this;
    }

    public AccountCourseListOptions includes(List<Includes> includes) {
        List<String> includeStrings = includes.stream().map(i -> i.name()).collect(Collectors.toList());
        optionsMap.put("include[]", includeStrings);
        return this;
    }
}

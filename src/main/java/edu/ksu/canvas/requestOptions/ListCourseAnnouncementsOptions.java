package edu.ksu.canvas.requestOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListCourseAnnouncementsOptions extends BaseOptions {

	public enum Include {
		SECTIONS, SECTIONS_USER_COUNT;

		@Override
		public String toString() { return name().toLowerCase(); }
	}

	private String courseId;

	public ListCourseAnnouncementsOptions(String courseId) {
		this.courseId = courseId;
		List<String> contextCodesList = new ArrayList<>();
		contextCodesList.add("course_" + courseId);
		addStringList("context_codes[]", contextCodesList);
	}

	public String getCourseId() {
		return courseId;
	}

	public ListCourseAnnouncementsOptions includes(List<Include> includes) {
		addEnumList("include[]", includes);
		return this;
	}

	public ListCourseAnnouncementsOptions startDate(Date startDate) {
		addSingleItem("start_date", formatDate(startDate));
		return this;
	}

	public ListCourseAnnouncementsOptions endDate(Date endDate) {
		addSingleItem("end_date", formatDate(endDate));
		return this;
	}

	public ListCourseAnnouncementsOptions activeOnly(Boolean activeOnly) {
		addSingleItem("active_only", activeOnly.toString());
		return this;
	}

	public ListCourseAnnouncementsOptions latestOnly(Boolean latestOnly) {
		addSingleItem("latest_only", latestOnly.toString());
		return this;
	}

	private static String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
		return df.format(date);
	}

}

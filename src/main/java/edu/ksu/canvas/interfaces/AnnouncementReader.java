package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.announcement.Announcement;
import edu.ksu.canvas.requestOptions.ListCourseAnnouncementsOptions;

import java.io.IOException;
import java.util.List;

public interface AnnouncementReader extends CanvasReader<Announcement, AnnouncementReader> {

	List<Announcement> listCourseAnnouncements(ListCourseAnnouncementsOptions options) throws IOException;

}

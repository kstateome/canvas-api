package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.announcement.Announcement;
import edu.ksu.canvas.requestOptions.ListCourseAnnouncementsOptions;

import java.io.IOException;
import java.util.List;

/**
 * Handles calls to the Announcement API endpoint. Functions
 * almost identically to fetching lists of DiscussionTopics, since
 * Announcements ARE DiscussionTopics, just hidden behind a different
 * API call.
 */
public interface AnnouncementReader extends CanvasReader<Announcement, AnnouncementReader> {

    List<Announcement> listCourseAnnouncements(ListCourseAnnouncementsOptions options) throws IOException;

}

package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.announcement.Announcement;

/**
 * There are no write calls for announcements. That's all handled by
 * the DiscussionTopic API calls. This is only here to fit the structure of
 * the library.
 */
public interface AnnouncementWriter extends CanvasWriter<Announcement, AnnouncementWriter> {
}

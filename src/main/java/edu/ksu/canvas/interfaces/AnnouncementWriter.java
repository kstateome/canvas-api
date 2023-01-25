package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.announcement.Announcement;

public interface AnnouncementWriter extends CanvasWriter<Announcement, AnnouncementWriter> {

	/*
	 * There are no write calls for announcements. That's all handled by
	 * the DiscussionTopic calls. This is here to fit the structure of
	 * the library.
	 */

}

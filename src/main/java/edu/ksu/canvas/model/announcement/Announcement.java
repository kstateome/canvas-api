package edu.ksu.canvas.model.announcement;

import edu.ksu.canvas.model.discussion.DiscussionTopic;

import java.io.Serializable;

/**
 * The Announcement object type is just a DiscussionTopic object
 * that gets returned from a different API call.
 */
public class Announcement extends DiscussionTopic implements Serializable {

    private static final long serialVersionUID = 1L;

}

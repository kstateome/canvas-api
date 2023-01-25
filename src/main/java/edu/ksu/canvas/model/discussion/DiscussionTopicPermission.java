package edu.ksu.canvas.model.discussion;

import java.io.Serializable;

public class DiscussionTopicPermission implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean attach;

	public boolean canAttach() { return attach; }

}

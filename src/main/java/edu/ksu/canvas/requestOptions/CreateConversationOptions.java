package edu.ksu.canvas.requestOptions;

import java.util.List;

public class CreateConversationOptions extends BaseOptions {

    public enum MediaCommentType {AUDIO, VIDEO;
        @Override
        public String toString() { return name().toLowerCase(); }
    }
    public enum Mode {SYNC, ASYNC;
        @Override
        public String toString() { return name().toLowerCase(); }
    }

    /**
     * Recipient list and body text are required to create a conversation.
     * Recipients can be either a user ID or a course/group ID if prefixed with "course_" or "group_"
     * @param recipients List of IDs for users/courses/groups to start a conversation with
     * @param bodyText The contents of the message
     */
    public CreateConversationOptions(List<String> recipients, String bodyText) {
        addSingleItem("body", bodyText);
        optionsMap.put("recipients[]", recipients);
    }

    /**
     * Convenience constructor to send to a single recipient instead of a list.
     * Recipient can be a user ID or a course/group if prefixed with "course_" or "group_"
     * @param recipient The ID of the user/course/group to start a conversation with
     * @param bodyText The contents of the message
     */
    public CreateConversationOptions(String recipient, String bodyText) {
        addSingleItem("body", bodyText);
        addSingleItem("recipients[]", recipient);
    }

    /**
     * Set a conversation subject.
     * If this is a private message between users who already have a conversation then that
     * conversation and its subject will be re-used instead of creating a new one.
     * @param subject The desired subject of the conversation
     * @return This object to allow adding more options
     */
    public CreateConversationOptions subject(String subject) {
        addSingleItem("subject", subject);
        return this;
    }

    /**
     * Forces a new message to be created, even if there is an existing private conversation.
     * @param forceNew True if a new message should be created.
     * @return This object to allow adding more options
     */
    public CreateConversationOptions forceNew(Boolean forceNew) {
        addSingleItem("force_new", forceNew.toString());
        return this;
    }

    /**
     * If specifying multiple recipients, setting this to true will turn this into a group
     * message instead of multiple individual conversations. Defaults to false.
     * @param groupConversation Whether or not this is a group conversation.
     * @return This object to allow adding more options
     */
    public CreateConversationOptions groupConversation(Boolean groupConversation) {
        addSingleItem("group_conversation", groupConversation.toString());
        return this;
    }

    /**
     * IDs of files to attach to this conversation. The files must have been previously uploaded
     * to the sender's "conversation attachments" folder.
     * @param attachmentIds IDs of files to attach to conversation
     * @return This object to allow adding more options
     */
    public CreateConversationOptions attachmentIds(List<Integer> attachmentIds) {
        addNumberList("attachment_ids[]", attachmentIds);
        return this;
    }

    /**
     * Associate an audio or video file with this conversation
     * @param mediaCommentId Media comment ID of media to associate
     * @return This object to allow adding more options
     */
    public CreateConversationOptions mediaCommentId(String mediaCommentId) {
        addSingleItem("media_comment_id", mediaCommentId);
        return this;
    }

    /**
     * Set the type of media associated with this conversation
     * @param type Type of media
     * @return This object to allow adding more options
     */
    public CreateConversationOptions mediaCommentType(MediaCommentType type) {
        addSingleItem("media_comment_type", type.toString());
        return this;
    }

    /**
     * Adds a faculty journal entry for each recipient if:
     *  - User making call has permission
     *  - Recipient is a student
     *  - Faculty journals are enabled on the account
     * @param userNote Whether to add faculty journals to conversations
     * @return This object to allow adding more options
     */
    public CreateConversationOptions userNote(Boolean userNote) {
        addSingleItem("user_note", userNote.toString());
        return this;
    }

    /**
     * Send messages synchronously or asynchronously. Only applies if this is a bulk private message.
     * If specified, the response is an empty array. Message sending status must be queried via the batches API
     * @param mode Sync or async mode to use when sending message
     * @return This object to allow adding more options
     */
    public CreateConversationOptions mode(Mode mode) {
        addSingleItem("mode", mode.toString());
        return this;
    }
}

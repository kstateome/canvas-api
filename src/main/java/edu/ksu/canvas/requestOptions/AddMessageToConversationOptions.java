package edu.ksu.canvas.requestOptions;

import java.util.List;

import edu.ksu.canvas.requestOptions.CreateConversationOptions.MediaCommentType;

public class AddMessageToConversationOptions extends BaseOptions {

    private Integer conversationId;

    /**
     * Create parameters to add a message. Conversation ID and message body are required.
     * @param conversationId The ID of the Conversation to add a message to
     * @param body The contents of the message
     */
    public AddMessageToConversationOptions(Integer conversationId, String body) {
        this.conversationId = conversationId;
        addSingleItem("body", body);
    }

    public Integer getConversationId() {
        return conversationId;
    }

    /**
     * IDs of files to attach to this message. The files must have been previously uploaded
     * to the sender's "conversation attachments" folder.
     * @param attachmentIds IDs of files to attach to conversation
     * @return This object to allow adding more options
     */
    public AddMessageToConversationOptions attachmentIds(List<Integer> attachmentIds) {
        addNumberList("attachment_ids[]", attachmentIds);
        return this;
    }

    /**
     * Associate an audio or video file with this message
     * @param mediaCommentId Media comment ID of media to associate
     * @return This object to allow adding more options
     */
    public AddMessageToConversationOptions mediaCommentId(Integer mediaCommentId) {
        addSingleItem("media_comment_id", mediaCommentId.toString());
        return this;
    }

    /**
     * Set the type of media associated with this message
     * @param type Type of media
     * @return This object to allow adding more options
     */
    public AddMessageToConversationOptions mediaCommentType(MediaCommentType type) {
        addSingleItem("media_comment_type", type.toString());
        return this;
    }


    /**
     * Recipients to add to the conversation. Defaults to all users currently participating
     * @param recipients User IDs of users to include in this message
     * @return This object to allow adding more options
     */
    public AddMessageToConversationOptions recipients(List<String> recipients) {
        optionsMap.put("recipients[]", recipients);
        return this;
    }

    /**
     * List of message IDs from this conversation to send to recipients of the new message.
     * Only has an effect on new users being added to the conversation via the "recipients" method.
     * @param messages List of message IDs
     * @return This object to allow adding more options
     */
    public AddMessageToConversationOptions includedMessages(List<Integer> messages) {
        addNumberList("included_messages[]", messages);
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
    public AddMessageToConversationOptions userNote(Boolean userNote) {
        addSingleItem("user_note", userNote.toString());
        return this;
    }
}

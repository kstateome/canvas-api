package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;

import edu.ksu.canvas.model.Conversation;
import edu.ksu.canvas.requestOptions.CreateConversationOptions;

public interface ConversationWriter extends CanvasWriter<Conversation, ConversationWriter> {

    /**
     * Create a new conversation with one or more recipients. Note that if there is an existing
     * private conversation between the sender and a recipient, that conversation will be reused
     * and this message appended to it. A new conversation will NOT be created and the subject
     * will not be modified.
     *
     * Recipients can be users, courses or groups. If courses or groups, the IDs need to be
     * prepended with "course_" or "group_" respectively. Returns a list of conversations created.
     * @param options Creation options for the conversation
     * @return List of conversations created
     * @throws IOException if an error happens while communicating with Canvas
     */
    public List<Conversation> createConversation(CreateConversationOptions options) throws IOException;

}

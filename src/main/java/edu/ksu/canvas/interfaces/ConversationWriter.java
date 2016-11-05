package edu.ksu.canvas.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import edu.ksu.canvas.model.Conversation;
import edu.ksu.canvas.requestOptions.AddMessageToConversationOptions;
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

    /**
     * Marks all conversations for the current user as read.
     * There is no return from Canvas. If there is no error, we must assume it succeeded.
     * @throws IOException if there is an error communicating with Canvas
     */
    public void markAllConversationsRead() throws IOException;

    /**
     * Modify a conversation in Canvas. Only some fields are mutable:
     * workflow state, subscribed and starred.
     * According to the API docs you should be able to modify the subject but it doesn't seem to work.
     * @param conversation Conversation with modifications to send to Canvas
     * @return The modified conversation object
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<Conversation> editConversation(Conversation conversation) throws IOException;

    /**
     * Add a message to an existing conversation. The returned Conversation object will only
     * have a single message in it (the one we just added)
     * @param options Parameters for the message adding method
     * @return Conversation object with only the added message in it
     * @throws IOException if there is an error communicating with Canvas
     */
    public Optional<Conversation> addMessage(AddMessageToConversationOptions options) throws IOException;
}

package edu.ksu.canvas.requestOptions;

import edu.ksu.canvas.model.CommunicationChannel;

public class CreateCommunicationChannelOptions extends BaseOptions {

    protected final String userId;

    /**
     * Constructs object to hold API options for creating a communication channel.
     * @param userId The id of the related user
     */
    public CreateCommunicationChannelOptions(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public CreateCommunicationChannelOptions skipConfirmation(Boolean skipConfirmation) {
        addSingleItem("skip_confirmation", skipConfirmation.toString());
        return this;
    }

    public CreateCommunicationChannelOptions address(String address) {
        addSingleItem("communication_channel[address]", address);
        return this;
    }

    public CreateCommunicationChannelOptions type(CommunicationChannel.Type type) {
        addSingleItem("communication_channel[type]", type.toString());
        return this;
    }

    public CreateCommunicationChannelOptions token(String token) {
        addSingleItem("communication_channel[token]", token);
        return this;
    }

}
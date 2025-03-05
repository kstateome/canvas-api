package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CommunicationChannel;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface CommunicationChannelReader extends CanvasReader<CommunicationChannel, CommunicationChannelReader> {
    /**
     * Retrieve a user's communication channels.
     * @param userId the id of the user to retrieve communication channels for
     * @return List of the user's communication channels
     * @throws IOException When there is an error communicating with Canvas
     */
    public List<CommunicationChannel> getCommunicationChannelsForUser(String userId) throws IOException, URISyntaxException, ParseException;
}

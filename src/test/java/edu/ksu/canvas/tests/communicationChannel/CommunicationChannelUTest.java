package edu.ksu.canvas.tests.communicationChannel;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CommunicationChannelImpl;
import edu.ksu.canvas.interfaces.CommunicationChannelReader;
import edu.ksu.canvas.interfaces.CommunicationChannelWriter;
import edu.ksu.canvas.model.CommunicationChannel;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.CreateCommunicationChannelOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommunicationChannelUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CommunicationChannelReader ccReader;
    private CommunicationChannelWriter ccWriter;

    private static final String USER_ID = "123";
    private static final String CC_ID = "1111";
    private static final String ADDRESS_EXAMPLE = "address@example.com";
    
    @BeforeEach
    void setupReader() {
         ccReader = new CommunicationChannelImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                 SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
         ccWriter = new CommunicationChannelImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                 SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testListCommunicationChannels() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/CommunicationChannelsList.json");
        List<CommunicationChannel> ccList = ccReader.getCommunicationChannelsForUser(USER_ID);
        assertNotNull(ccList);
        assertEquals(2, ccList.size());
    }

    @Test
    void testCreateCommunicationChannel() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/CreateCommunicationChannelResponse.json");
        CreateCommunicationChannelOptions ccco = new CreateCommunicationChannelOptions(USER_ID);
        ccco.address(ADDRESS_EXAMPLE);
        ccco.type(CommunicationChannel.Type.EMAIL);
        ccco.skipConfirmation(false);
        Optional<CommunicationChannel> ccResponse = ccWriter.createCommunicationChannel(ccco);
        assertNotNull(ccResponse.get().getId());
        assertEquals(ccResponse.get().getUserId(), USER_ID);
        assertEquals(ccResponse.get().getAddress(), ADDRESS_EXAMPLE);
        assertEquals(ccResponse.get().getWorkflowState(), CommunicationChannel.WorkflowState.UNCONFIRMED);
        assertEquals(ccResponse.get().getType(), CommunicationChannel.Type.EMAIL);
    }

    @Test
    void testCreateCommunicationChannelSkip() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/CreateCommunicationChannelSkipConfirmationResponse.json");
        CreateCommunicationChannelOptions ccco = new CreateCommunicationChannelOptions(USER_ID);
        ccco.address(ADDRESS_EXAMPLE);
        ccco.type(CommunicationChannel.Type.EMAIL);
        ccco.skipConfirmation(true);
        Optional<CommunicationChannel> ccResponse = ccWriter.createCommunicationChannel(ccco);
        assertNotNull(ccResponse.get().getId());
        assertEquals(ccResponse.get().getUserId(), USER_ID);
        assertEquals(ccResponse.get().getAddress(), ADDRESS_EXAMPLE);
        assertEquals(ccResponse.get().getWorkflowState(), CommunicationChannel.WorkflowState.ACTIVE);
        assertEquals(ccResponse.get().getType(), CommunicationChannel.Type.EMAIL);
    }

    @Test
    void testDeleteCommunicationChannel() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels/" + CC_ID, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/DeleteCommunicationChannelResponse.json");
        CommunicationChannel cc = new CommunicationChannel();
        cc.setId(CC_ID);
        cc.setUserId(USER_ID);
        cc.setAddress(ADDRESS_EXAMPLE);
        cc.setType(CommunicationChannel.Type.EMAIL);
        Optional<CommunicationChannel> ccResponse = ccWriter.deleteCommunicationChannel(cc);
        assertEquals(CC_ID, ccResponse.get().getId());
        assertNull(ccResponse.get().getWorkflowState());
    }
}

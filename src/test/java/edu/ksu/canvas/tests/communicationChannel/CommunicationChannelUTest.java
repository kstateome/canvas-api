package edu.ksu.canvas.tests.communicationChannel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CommunicationChannelImpl;
import edu.ksu.canvas.interfaces.CommunicationChannelReader;
import edu.ksu.canvas.interfaces.CommunicationChannelWriter;
import edu.ksu.canvas.model.CommunicationChannel;
import edu.ksu.canvas.requestOptions.CreateCommunicationChannelOptions;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListRolesOptions;
import edu.ksu.canvas.util.CanvasURLBuilder;

public class CommunicationChannelUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CommunicationChannelReader ccReader;
    private CommunicationChannelWriter ccWriter;

    private static final String USER_ID = "123";
    private static final String CC_ID = "1111";
    private static final String ADDRESS_EXAMPLE = "address@example.com";
    
    @Before
    public void setupReader() {
         ccReader = new CommunicationChannelImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                 SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
         ccWriter = new CommunicationChannelImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                 SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testListCommunicationChannels() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/CommunicationChannelsList.json");
        List<CommunicationChannel> ccList = ccReader.getCommunicationChannelsForUser(USER_ID);
        Assert.assertNotNull(ccList);
        Assert.assertEquals(2, ccList.size());
    }

    @Test
    public void testCreateCommunicationChannel() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/CreateCommunicationChannelResponse.json");
        CreateCommunicationChannelOptions ccco = new CreateCommunicationChannelOptions(USER_ID);
        ccco.address(ADDRESS_EXAMPLE);
        ccco.type(CommunicationChannel.Type.EMAIL);
        ccco.skipConfirmation(false);
        Optional<CommunicationChannel> ccResponse = ccWriter.createCommunicationChannel(ccco);
        Assert.assertNotNull(ccResponse.get().getId());
        Assert.assertEquals(ccResponse.get().getUserId(), USER_ID);
        Assert.assertEquals(ccResponse.get().getAddress(), ADDRESS_EXAMPLE);
        Assert.assertEquals(ccResponse.get().getWorkflowState(), CommunicationChannel.WorkflowState.UNCONFIRMED);
        Assert.assertEquals(ccResponse.get().getType(), CommunicationChannel.Type.EMAIL);
    }

    @Test
    public void testCreateCommunicationChannelSkip() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels", Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/CreateCommunicationChannelSkipConfirmationResponse.json");
        CreateCommunicationChannelOptions ccco = new CreateCommunicationChannelOptions(USER_ID);
        ccco.address(ADDRESS_EXAMPLE);
        ccco.type(CommunicationChannel.Type.EMAIL);
        ccco.skipConfirmation(true);
        Optional<CommunicationChannel> ccResponse = ccWriter.createCommunicationChannel(ccco);
        Assert.assertNotNull(ccResponse.get().getId());
        Assert.assertEquals(ccResponse.get().getUserId(), USER_ID);
        Assert.assertEquals(ccResponse.get().getAddress(), ADDRESS_EXAMPLE);
        Assert.assertEquals(ccResponse.get().getWorkflowState(), CommunicationChannel.WorkflowState.ACTIVE);
        Assert.assertEquals(ccResponse.get().getType(), CommunicationChannel.Type.EMAIL);
    }

    @Test
    public void testDeleteCommunicationChannel() throws Exception {
        String url = CanvasURLBuilder.buildCanvasUrl(baseUrl, apiVersion,"users/" + USER_ID + "/communication_channels/" + CC_ID, Collections.emptyMap());
        fakeRestClient.addSuccessResponse(url, "SampleJson/communicationChannel/DeleteCommunicationChannelResponse.json");
        CommunicationChannel cc = new CommunicationChannel();
        cc.setId(CC_ID);
        cc.setUserId(USER_ID);
        cc.setAddress(ADDRESS_EXAMPLE);
        cc.setType(CommunicationChannel.Type.EMAIL);
        Optional<CommunicationChannel> ccResponse = ccWriter.deleteCommunicationChannel(cc);
        Assert.assertEquals(CC_ID, ccResponse.get().getId());
        Assert.assertNull(ccResponse.get().getWorkflowState());
    }
}

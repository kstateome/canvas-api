package edu.ksu.canvas.tests.sisImport;

import java.io.IOException;
import java.util.Optional;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.SisImportImpl;
import edu.ksu.canvas.interfaces.SisImportReader;
import edu.ksu.canvas.model.SisImport;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.net.Response;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SisImportUTest extends CanvasTestBase {

    private SisImportReader sisImportReader;

    @Autowired
    private FakeRestClient fakeRestClient;

    private static final String ARBITRARY_ACCOUNT_ID = "9";
    private static final Long ARBITRARY_SIS_IMPORT_ID = 65632L;
    private static final Integer ARBITRARY_SIS_IMPORT_PROGRESS = 100;

    @Before
    public void setupData() {
        sisImportReader = new SisImportImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testGetSisImport() throws IOException {
        String url = baseUrl + "/api/v1/accounts/" + ARBITRARY_ACCOUNT_ID + "/sis_imports/" + ARBITRARY_SIS_IMPORT_ID;
        fakeRestClient.addSuccessResponse(url, "SampleJson/sisImport/SisImportCompleted.json");

        Optional<SisImport> response = sisImportReader.getSisImport(ARBITRARY_ACCOUNT_ID, ARBITRARY_SIS_IMPORT_ID);
        SisImport ss = response.get();
        assertEquals(ARBITRARY_SIS_IMPORT_ID, ss.getId());

        assertEquals("2020-07-29T06:41:44Z", ss.getCreatedAt().toString());
        assertEquals("2020-07-29T06:41:44Z", ss.getStartedAt().toString());
        assertEquals("2020-07-29T06:41:53Z", ss.getEndedAt().toString());
        assertEquals("2020-07-29T06:41:53Z", ss.getUpdatedAt().toString());

        assertEquals(ARBITRARY_SIS_IMPORT_PROGRESS, ss.getProgress());
        assertEquals("imported", ss.getWorkflowState());
        assertEquals("difff", ss.getDiffingDataSetIdentifier());
    }

}

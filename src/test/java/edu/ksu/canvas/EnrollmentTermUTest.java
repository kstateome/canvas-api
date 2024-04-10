package edu.ksu.canvas;

import edu.ksu.canvas.impl.EnrollmentTermImpl;
import edu.ksu.canvas.interfaces.EnrollmentTermReader;
import edu.ksu.canvas.model.EnrollmentTerm;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.GetEnrollmentTermOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EnrollmentTermUTest extends CanvasTestBase {
    private static final String ACCOUNT_ID = "1";

    @Autowired
    private FakeRestClient fakeRestClient;

    @Autowired
    private String baseUrl;
    private EnrollmentTermReader enrollmentTermReader;

    @BeforeEach
    void setup() {
        String url =  baseUrl  + "/api/v1/accounts/" + ACCOUNT_ID + "/terms";
        fakeRestClient.addSuccessResponse(url, "SampleJson/EnrollmentTerm.json");
        enrollmentTermReader = new EnrollmentTermImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void getEnrollmentTerms() throws Exception {
        List<EnrollmentTerm> enrollmentTerms = enrollmentTermReader.getEnrollmentTerms(new GetEnrollmentTermOptions(ACCOUNT_ID));

        EnrollmentTerm firstEnrollmentTerm = enrollmentTerms.get(0);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssX");
        Date startDate = sdf.parse("2016-08-31T20:00:00Z");
        Date endDate = sdf.parse("2016-12-20T20:00:00Z");
        Date createdDate = sdf.parse("2016-07-16T20:00:00Z");

        assertEquals(1, firstEnrollmentTerm.getId(), "Expected id in object to match id in json");
        assertEquals("Fall 2016", firstEnrollmentTerm.getName(), "Expected name in object to match name in json");
        assertEquals(startDate, firstEnrollmentTerm.getStartAt(), "Expected start date in object to match start date in json");
        assertEquals(endDate, firstEnrollmentTerm.getEndAt(), "Expected end date in object to match end date in json");
        assertEquals(createdDate, firstEnrollmentTerm.getCreatedAt(), "Expected created date in object to match created date in json");
        assertEquals("active", firstEnrollmentTerm.getWorkflowState(), "Expected workflow state 'active' not returned");
        assertEquals("81", firstEnrollmentTerm.getSisTermId(), "Expected sis id in object to match name in json");

    }
}

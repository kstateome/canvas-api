package edu.ksu.canvas.tests.file;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.FileImpl;
import edu.ksu.canvas.interfaces.FileReader;
import edu.ksu.canvas.model.File;
import edu.ksu.canvas.net.FakeRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseGetUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private FileReader fileReader;

    @BeforeEach
    public void setupData() {
        fileReader  = new FileImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT,
                SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testGettingCourses() throws IOException {
        String url = baseUrl + "/api/v1/files/123";
        fakeRestClient.addSuccessResponse(url, "SampleJson/file/GetFileSuccess.json");
        Optional<File> optionalFile = fileReader.getFile(url);
        assertTrue(optionalFile.isPresent());
        File file = optionalFile.get();
        assertEquals(569, file.getId().intValue());
        assertEquals("SUj23659sdfASF35h265kf352YTdnC4", file.getUuid());
        assertEquals(4207, file.getFolderId().intValue());
        assertEquals("file.txt", file.getDisplayName());
        assertEquals("file.txt", file.getFilename());
        assertTrue(file.getUrl().startsWith("http:"));
        assertEquals(43451, file.getSize().intValue());
        assertEquals("2012-07-06T14:58:50Z", file.getCreatedAt().toString());
        assertEquals("2012-07-06T14:58:50Z", file.getUpdatedAt().toString());
        assertEquals("2012-07-07T14:58:50Z", file.getUnlockAt().toString());
        assertFalse(file.getLocked());
        assertFalse(file.getHidden());
        assertEquals("2012-07-20T14:58:50Z", file.getLockAt().toString());
        assertNull(file.getThumbnailUrl());
        assertEquals("2012-07-06T14:58:50Z", file.getModifiedAt().toString());
        assertEquals("html", file.getMimeClass());
        assertEquals("m-3z31gfpPf129dD3sSDF85SwSDFnwe", file.getMediaEntryId());
        assertFalse(file.getLockedForUser());
    }

}

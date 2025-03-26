package edu.ksu.canvas.impl;

import com.google.common.reflect.TypeToken;
import edu.ksu.canvas.interfaces.AnnouncementReader;
import edu.ksu.canvas.interfaces.AnnouncementWriter;
import edu.ksu.canvas.model.announcement.Announcement;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListCourseAnnouncementsOptions;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * An Announcement is really just a DiscussionTopic, but Canvas has
 * a separate API call for fetching the ones posted under the
 * Announcements tab rather than the Discussions tab. That's handled here.
 */
public class AnnouncementImpl extends BaseImpl<Announcement, AnnouncementReader, AnnouncementWriter> implements AnnouncementReader, AnnouncementWriter {

    public AnnouncementImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public List<Announcement> listCourseAnnouncements(ListCourseAnnouncementsOptions options) throws IOException {
        String url = buildCanvasUrl("/announcements", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Announcement>>(){}.getType();
    }

    @Override
    protected Class<Announcement> objectType() {
        return Announcement.class;
    }
}

package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.ModuleReader;
import edu.ksu.canvas.model.Module;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListModulesOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ModuleImpl extends BaseImpl<Module, ModuleReader, CanvasWriter> implements ModuleReader {
    private static final Logger LOG = LoggerFactory.getLogger(ModuleImpl.class);

    public ModuleImpl(final String canvasBaseUrl, final Integer apiVersion, final OauthToken oauthToken, final RestClient restClient, final int connectTimeout, final int readTimeout, final Integer paginationPageSize, final Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    public List<Module> getModulesInCourse(final ListModulesOptions options) throws IOException {
        LOG.debug("Retrieving modules for course {}", options.getCourseId());
        String url = buildCanvasUrl(String.format("courses/%d/modules", options.getCourseId()), options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    protected Class<Module> objectType() {
        return Module.class;
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<Module>>() {
        }.getType();
    }
}

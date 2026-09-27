package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CanvasWriter;
import edu.ksu.canvas.interfaces.ModuleItemReader;
import edu.ksu.canvas.model.ModuleItem;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.ListModuleItemsOptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ModuleItemImpl extends BaseImpl<ModuleItem, ModuleItemReader, CanvasWriter> implements ModuleItemReader {
	private static final Logger LOG = LoggerFactory.getLogger(ModuleItemImpl.class);
	public ModuleItemImpl(final String canvasBaseUrl, final Integer apiVersion, final OauthToken oauthToken, final RestClient restClient, final int connectTimeout, final int readTimeout, final Integer paginationPageSize, final Boolean serializeNulls) {
		super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
	}

	@Override
	public List<ModuleItem> getModuleItemsInModule(final ListModuleItemsOptions options) throws IOException {
		LOG.debug("Retrieving items for module {}", options.getModuleId());
		String url = buildCanvasUrl(String.format("courses/%d/modules/%d/items", options.getCourseId(), options.getModuleId()), options.getOptionsMap());
		return getListFromCanvas(url);
	}

	@Override
	protected Type listType() {
		return new TypeToken<List<ModuleItem>>() {
		}.getType();
	}

	@Override
	protected Class<ModuleItem> objectType() {
		return ModuleItem.class;
	}
}
package edu.ksu.canvas.tests.module;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.ModuleItemImpl;
import edu.ksu.canvas.interfaces.ModuleItemReader;
import edu.ksu.canvas.model.ModuleItem;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListModuleItemsOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class ModuleItemListingTest extends CanvasTestBase {
	@Autowired
	private FakeRestClient fakeRestClient;
	private ModuleItemReader moduleItemReader;

	@Before
	public void setupData() {
		moduleItemReader = new ModuleItemImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
	}

	@Test
	public void testListModuleItemsInModule() throws IOException {
		Long courseId = 1092L;
		Long moduleId = 1059720L;
		ListModuleItemsOptions options = new ListModuleItemsOptions(courseId, moduleId);
		String url = baseUrl + "/api/v1/courses/1092/modules/1059720/items";

		fakeRestClient.addSuccessResponse(url, "SampleJson/ModuleItems.json");
		List<ModuleItem> moduleItems = moduleItemReader.getModuleItemsInModule(options);
		Assert.assertNotNull(moduleItems);
		Assert.assertEquals(4, moduleItems.size());
		for (ModuleItem moduleItem: moduleItems) { /* Copied from https://github.com/instructure/CanvasAPI/blob/develop/src/test/java/ModuleItemUnitTest.java */
			Assert.assertTrue(moduleItem.getId() > 0);
			Assert.assertNotNull(moduleItem.getType());
			Assert.assertNotNull(moduleItem.getTitle());
			Assert.assertNotNull(moduleItem.getHtmlUrl());
			Assert.assertNotNull(moduleItem.getUrl());
			if (moduleItem.getCompletionRequirement() != null) {
				Assert.assertNotNull(moduleItem.getCompletionRequirement().getType());
			}
		}
	}
}
package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.ModuleItem;
import edu.ksu.canvas.requestOptions.ListModuleItemsOptions;

import java.io.IOException;
import java.util.List;

public interface ModuleItemReader extends CanvasReader<ModuleItem, ModuleItemReader> {
	/**
	 * Retrieve the list of items in a module.
	 *
	 * @param options The object holding options for this API call
	 * @return List of the items in a module
	 * @throws IOException When there is an error communicating with Canvas
	 */
	public List<ModuleItem> getModuleItemsInModule(ListModuleItemsOptions options) throws IOException;
}
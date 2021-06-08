package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.SisImport;

import java.io.IOException;
import java.util.Optional;

public interface SisImportReader extends CanvasReader<SisImport, SisImportReader> {
    /**
     * Returns a sis import.
     * @param accountId The account ID for this API call
     * @param id The Canvas ID of the sis import to query
     * @return A SisImport object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<SisImport> getSisImport(String accountId, Long id) throws IOException;
}

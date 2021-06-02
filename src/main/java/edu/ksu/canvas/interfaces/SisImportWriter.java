package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.SisImport;
import edu.ksu.canvas.requestOptions.CreateSisImportOptions;

import java.io.IOException;

import java.util.Optional;

public interface SisImportWriter extends CanvasWriter<SisImport, SisImportWriter>{

    Optional<SisImport> createSisImport(CreateSisImportOptions options) throws IOException;

}

package edu.ksu.canvas.interfaces;

public interface CanvasWriter<T, WRITERTYPE extends CanvasWriter>  {

    WRITERTYPE writeAsCanvasUser(String masqueradeAs);

    WRITERTYPE writeAsSisUser(String masqueradeAs);

}

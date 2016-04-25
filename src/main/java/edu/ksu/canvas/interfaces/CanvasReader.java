package edu.ksu.canvas.interfaces;


import java.util.List;
import java.util.function.Consumer;

public interface CanvasReader<T> {
    /*
     * Perform an operation with a callback. This is used to
     * to perform operations on paginated calls before the final
     * response is complete.
     */
    CanvasReader withCallback(Consumer<List<T>> responseConsumer);

}

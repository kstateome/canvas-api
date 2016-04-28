package edu.ksu.canvas.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CanvasField {

    String postKey();

    /* Controls whether the key should be wrapped in the CanvasObject's postName.
     * For example if the class's CanvasObject annotation contains the postKey value of 'course',
     * and the CanvasField has a postKey value of 'id' then:
     * Setting array to true will result in the postMap key be 'course[id]'
     * Setting array to false will result in the postMap key to be 'id'
     */
    boolean array() default true;

    /*
        Used to override the key on the main Canvas Object. Leave empty unless necessary
     */
    String overrideObjectKey() default "";
}

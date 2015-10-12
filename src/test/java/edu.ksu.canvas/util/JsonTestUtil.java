package edu.ksu.canvas.util;


import org.apache.log4j.Logger;

import java.io.InputStream;

public class JsonTestUtil {
    private static final Logger LOG = Logger.getLogger(JsonTestUtil.class);
    public static String loadJson(String fileName, Class clazz) {
        try {
            InputStream stream = clazz.getResourceAsStream(fileName);
            int c = (char) stream.read();
            String json = "";
            while (c != -1) {
                json += (char) c;
                c = stream.read();
            }
            return json;
        } catch (Exception e) {
            LOG.error("Could not load json file " + fileName,e);
            return null;
        }
    }
}
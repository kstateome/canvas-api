package edu.ksu.canvas.util;

import java.util.Comparator;

/**
 * Needed to put the key as the first parameter in the map which is needed for AWS S3 uploads
 */
public class KeyFirstComparator implements Comparator<String> {

    public static final String KEY = "key";

    @Override
    public int compare(String o1, String o2) {
        if (KEY.equals(o1)) {
            return -1;
        }
        if (KEY.equals(o2)) {
            return 1;
        }
        return o1.compareTo(o2);
    }
}

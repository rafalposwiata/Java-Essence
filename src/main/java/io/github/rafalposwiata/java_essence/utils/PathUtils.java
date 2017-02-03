package io.github.rafalposwiata.java_essence.utils;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Rafał Poświata.
 */
public class PathUtils {

    private PathUtils() {}

    public static Path getPath(Object caller, String resourceName){
        try {
            return Paths.get(caller
                    .getClass()
                    .getClassLoader()
                    .getResource(resourceName)
                    .toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

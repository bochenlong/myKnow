package org.bochenlong.watch;

import java.nio.file.Paths;

/**
 * Created by bochenlong on 16-10-22.
 */
public class FileWatcherTest {
    public static void main(String[] args) {
        FileWatcher.register(Paths.get("/home/bochenlong/temp"));
    }
}

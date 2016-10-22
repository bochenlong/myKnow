package org.bochenlong.watch;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

/**
 * Created by bochenlong on 16-10-22.
 * // volatile 不能保证对象的可见性
 */
public class FileWatcher {
    private static class FileWatcherHolder {
        private static WatchService watchService = init();

        private static WatchService init() {
            WatchService watchService = null;
            try {
                watchService = FileSystems.getDefault().newWatchService();

                new Thread(new WatherLoopThread(watchService)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return watchService;
        }


    }

    private FileWatcher() {
    }

    public static boolean register(Path path) {
        assert path != null;
        File file = new File(path.toUri());
        assert file.isDirectory();
        try {
            path.register(FileWatcherHolder.watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY
            );
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static class WatherLoopThread implements Runnable {
        private WatchService watchService;

        public WatherLoopThread(WatchService watchService) {
            this.watchService = watchService;
        }

        @Override
        public void run() {
            while (true) {
                WatchKey key;
                try {
                    key = watchService.take();
                    for (WatchEvent event : key.pollEvents()) {
                        WatchEvent.Kind kind = event.kind();
                        System.out.println("Event on " + event.context().toString()
                                + " is " + kind);
                    }
                    key.reset();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

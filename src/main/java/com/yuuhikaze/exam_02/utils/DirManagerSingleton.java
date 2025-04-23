package com.yuuhikaze.exam_02.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirManagerSingleton {

    private static final Path dataDir =
        Paths.get(System.getProperty("user.home")).resolve("cmp_exam");

    public static Path getDataDir() {
        return dataDir;
    }

    public static void createDataDir() throws IOException {
        if (Files.notExists(dataDir)) {
            try {
                Files.createDirectories(dataDir);
                Files.createDirectories(dataDir.resolve("logs"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

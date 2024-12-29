package io.file;

import java.io.File;
import java.io.IOException;

/**
 * 경로 표시
 */

public class OldFilePath {

    public static void main(String[] args) throws IOException {
        File file = new File("temp/..");
        System.out.println("file.getPath() = " + file.getPath());

        // 절대경로
        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());

        // 정규 경로
        System.out.println("file.getCanonicalPath() = " + file.getCanonicalPath());

        File[] files = file.listFiles();
        for (File f : files) {
            System.out.println((f.isFile() ? "F" : "D") + " | " + f.getName());
        }

    }

}

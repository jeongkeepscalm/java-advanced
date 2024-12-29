package io.file.copy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * 예제를 위한 200mb 임시 파일 생성
 */
public class CreateCopyFile {

    // 200mb
    private static final int FILE_SIZE = 200 * 1024 * 1024;

    public static void main(String[] args) throws IOException {

        String filePath = "temp/copy.dat";

        long startTime = System.currentTimeMillis();

        FileOutputStream fos = new FileOutputStream(filePath);
        byte[] buffer = new byte[FILE_SIZE];
        fos.write(buffer);
        fos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + filePath);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        /*
            File created: temp/copy.dat
            File size: 200MB
            Time taken: 608ms
         */

    }
}

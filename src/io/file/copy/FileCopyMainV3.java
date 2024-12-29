package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 파일 복사 예제 3
 *      Files.copy() 사용
 *
 * 정리
 *      예제 1,2: 파일(copy.dat)  자바(byte)  파일(copy_new.dat)
 *      예제 3: 파일(copy.dat)  파일(copy_new.dat)
 */
public class FileCopyMainV3 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        Path source = Path.of("temp/copy.dat");
        Path target = Path.of("temp/copy_new.dat");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");    }
        // Time taken: 125ms
}

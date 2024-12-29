package io.file.copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 복사 예제 1
 *      파일의 생성해서 기존의 파일 내용을 새로 쓴다.
 */
public class FileCopyMainV1 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("temp/copy.dat");
        FileOutputStream fos = new FileOutputStream("temp/copy_new.dat");
        byte[] bytes = fis.readAllBytes();
        fos.write(bytes);
        fis.close();
        fos.close();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        // Time taken: 452ms
    }

}

package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.FILE_NAME;
import static io.buffered.BufferedConst.FILE_SIZE;

/**
 * 파일 입출력과 성능 최적화1 - 하나씩 쓰기
 */
public class CreateFileV1 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < FILE_SIZE; i++) {
            // 한 번 호출 시 1byte 생성
            // 약 1000만번 호출하면 10mb 의 파일이 만들어진다.
            fos.write(1);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        /*
            File created: temp/buffered.dat
            File size: 10MB
            Time taken: 17724ms
         */

    }

}

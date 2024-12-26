package io.buffered;

import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

/**
 * 파일 입출력과 성능 최적화2 - 버퍼 활용
 */
public class CreateFileV2 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();
        int bufferIndex = 0;

        byte[] buffer = new byte[BUFFER_SIZE];
        for (int i = 0; i < FILE_SIZE; i++) {
            buffer[bufferIndex++] = 1;

            // 버퍼가 가득 차면 쓰고, 버퍼를 비운다.
            if (bufferIndex == BUFFER_SIZE) {
                fos.write(buffer);
                bufferIndex = 0;
            }

        }

        // 버퍼에 남은 부분을 쓴다.
        if (bufferIndex > 0) {
            fos.write(buffer, 0, bufferIndex);
        }

        fos.close();

        long endTime = System.currentTimeMillis();

        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        /*
            File created: temp/buffered.dat
            File size: 10MB
            Time taken: 40ms
         */

        /**
            디스크나 파일 시스템에서 데이터를 읽고 쓰는 기본 단위가 보통 4KB 또는 8KB
                4KB (4096 byte)
                8KB (8192 byte)
                => 버퍼의 크기는 보통 4KB, 8KB 정도로 잡는 것이 효율적
         */

    }

}

package io.buffered;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.*;

/**
 * 파일 입출력과 성능 최적화3 - Buffered 스트림 쓰기
 */
public class CreateFileV3 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < FILE_SIZE; i++) {
            bos.write(1);
        }

        /** 
         * 마지막에 연결한 스트림을 닫아야한다. 
         * 마지막에 연결한 스트림만 닫아주면 연쇄적으로 기본 스트림, 보조 스트림 FileOutputStream close() 가 호출된다.
         */
        bos.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + FILE_SIZE / 1024 / 1024 + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        /*
            File created: temp/buffered.dat
            File size: 10MB
            Time taken: 144ms
         */

        /**
            BufferedOutputStream
                OutputStream 필요
                버퍼 크기 전달
                내부에 byte[] buffer 존재하여 크기를 지정해주면 알아서 처리
                flush(): 버퍼에 남은 데이터를 전달
                close(): flush() 후 자원 정리


         */
    }

}

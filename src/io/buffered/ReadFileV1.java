package io.buffered;

import java.io.FileInputStream;
import java.io.IOException;

import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV1 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream(FILE_NAME);

        long startTime = System.currentTimeMillis();

        int fileSize = 0;
        int data;
        while ((data = fis.read()) != -1) {
            fileSize++;
        }
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File name: " + FILE_NAME);
        System.out.println("File size: " + (fileSize / 1024 / 1024) + "MB");
        System.out.println("Time taken: " + (endTime - startTime) + "ms");

        /*
            File name: temp/buffered.dat
            File size: 10MB
            Time taken: 12181ms
         */

        /**
            CreateFileV1, ReadFileV1
                실행시간이 오래 걸린 이유는 자바에서 1byte 씩 디스크에 데이터를 전달하기 때문이다.
                write() 나 read() 를 호출할 때마다 OS의 시스템 콜을 통해 파일을 읽거나 쓰는 명령어를 전달한다.
                이러한 시스템 콜은 상대적으로 무거운 작업이다.

         */
    }

}

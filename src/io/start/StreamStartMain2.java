package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {

    public static void main(String[] args) throws IOException {

        /**
         * new FileOutputStream("temp/hello.dat", true);
         *      true: 기존 파일의 끝에 이어서 쓴다.
         *      false: 기존 파일의 데이터를 지우고 처음부터 다시 쓴다. (기본값)
         *
         * -1: EOF(End Of File)를 나타낸다.
         */
        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        fos.write(65);
        fos.write(66);
        fos.write(67);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        int data;
        while ((data = fis.read()) != -1) {
            System.out.println("data = " + data);
        }
        fis.close();

    }
}

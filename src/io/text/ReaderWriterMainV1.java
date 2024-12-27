package io.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;

public class ReaderWriterMainV1 {

    public static void main(String[] args) throws IOException {

        String writeString = "ABC";
        byte[] writeBytes = writeString.getBytes(StandardCharsets.UTF_8);
        System.out.println("write String: " + writeString); // ABC
        System.out.println("write bytes: " + Arrays.toString(writeBytes)); // [65, 66, 67]

        // 파일에 쓰기
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        fos.write(writeBytes);
        fos.close();

        // 파일에서 읽기
        FileInputStream fis = new FileInputStream(FILE_NAME);
        byte[] readAllBytes = fis.readAllBytes();
        fis.close();

        // byte -> String UTF-8 디코딩
        String readString = new String(readAllBytes, StandardCharsets.UTF_8);
        System.out.println("read bytes: " + Arrays.toString(writeBytes)); // [65, 66, 67]
        System.out.println("read String: " + readString); // ABC

    }

}

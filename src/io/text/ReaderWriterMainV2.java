package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static io.text.TextConst.FILE_NAME;

/**
 * OutputStreamWriter, InputStreamReader
 *      편리하게 문자를 byte[] 변경 및 반대도 가능
 *      개발자는 쉽게 String 문자를 파일에 저장 가능하다.
 *
 *  I/O
 *      byte 를 다루는 IO 클래스(OutputStream, InputStream)
 *      문자를 다루는 IO 클래스(Writer, Reader)
 *  => 그래도 모든 데이터는 byte 단위(숫자)로 저장된다.
 *
 */

public class ReaderWriterMainV2 {

    public static void main(String[] args) throws IOException {

        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        // 파일에 쓰기
        /*
            OutputStreamWriter
                스트림에 byte 대신에 문자를 저장할 수 있게 지원
                "ABC" -> [65,66,67] 문자 인코딩
                write() 는 byte 가 아니라 String 이나 char 를 사용한다.
                    이유는?
                        OutputStreamWriter 는 Writer 의 자식이기 때문
        */
        FileOutputStream fos = new FileOutputStream(FILE_NAME);
        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
        osw.write(writeString);
        osw.close();

        // 파일에서 읽기
        // InputStreamWriter: 스트림에 byte 대신에 문자를 읽을 수 있게 지원
        FileInputStream fis = new FileInputStream(FILE_NAME);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }
        isr.close();

        System.out.println("sb = " + sb);

    }

}

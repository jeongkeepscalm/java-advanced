package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.text.TextConst.FILE_NAME;

/**
 * FileWriter
 *      FileOutputStream 을 하나 생성해서 사용해서 파일에 저장한다.
 * FileReader
 *      FileInputStream 을 사용해서 데이터를 byte 단위로 읽어들인다.
 *      그리고 문자 집합을 사용해서 byte[] 을 char 로 디코딩한다.
 *
 * 정리
 *      Writer, Reader 클래스를 사용하면 바이트 변환없이 문자를 직접 다룰 수 있어 편리하다
 *      하지만 실제로는 내부에서 byte 로 변환해서 저장한다!
 */

public class ReaderWriterMainV3 {

    public static void main(String[] args) throws IOException {

        String writeString = "ABC";
        System.out.println("writeString = " + writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, StandardCharsets.UTF_8);
        fw.write(writeString);
        fw.close();

        // 파일에서 읽기
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, StandardCharsets.UTF_8);
        int ch;
        while ((ch = fr.read()) != -1) {
            sb.append((char) ch);
        }
        fr.close();

        System.out.println("sb = " + sb);
    }

}

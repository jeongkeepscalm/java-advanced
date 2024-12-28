package io.text;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.text.TextConst.FILE_NAME;

/**
 *
 */

public class ReaderWriterMainV4 {

    public static void main(String[] args) throws IOException {

        String writeString = "ABC\n가나다";
        System.out.println("== Write String ==");
        System.out.println(writeString);

        // 파일에 쓰기
        FileWriter fw = new FileWriter(FILE_NAME, StandardCharsets.UTF_8);
        BufferedWriter bw = new BufferedWriter(fw, BUFFER_SIZE);
        bw.write(writeString);
        bw.close();

        // 파일에서 읽기
        StringBuilder sb = new StringBuilder();
        FileReader fr = new FileReader(FILE_NAME, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        /*
            파일의 끝(EOF)에 도달하면 반환타입이 String 이기 때문에 null 반환
         */
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();

        System.out.println("== Read String ==");
        System.out.println(sb);

    }

}

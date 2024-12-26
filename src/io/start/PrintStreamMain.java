package io.start;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class PrintStreamMain {

    public static void main(String[] args) throws IOException {
        PrintStream printStream = System.out;
        byte[] bytes = "HELLO!\n".getBytes(StandardCharsets.UTF_8);
        printStream.write(bytes);
        printStream.println("Print!");
    }

    /**
        PrintStream
            자주 사용했던 System.out 은 사실, PrintStream 이다.
            OutputStream 을 상속 받는다.
            자바가 시작될 때 자동으로 만들어진다.
     */

}

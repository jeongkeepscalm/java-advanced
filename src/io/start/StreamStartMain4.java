package io.start;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StreamStartMain4 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("temp/hello.dat");
        byte[] input = {65, 66, 67};
        fos.write(input);
        fos.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");
        byte[] readAllBytes = fis.readAllBytes();
        System.out.println("Arrays.toString(readAllBytes) = " + Arrays.toString(readAllBytes));
        fis.close();
    }

    /**
        read(byte[], offset, lentgh)
            스트림의 내용을 부분적으로 읽거나, 읽은 내용을 처리하면서 스트림을 계속해서 읽어야 할 경우에 적합하다.
            메모리 사용량을 제어할 수 있다.
            예시) 파일이나 스트림에서 일정한 크기의 데이터를 반복적으로 읽어야 할 때 유용하다. 예를 들어, 대용량
            파일을 처리할 때, 한 번에 메모리에 로드하기보다는 이 메서드를 사용하여 파일을 조각조각 읽어들일 수 있다.
            100M의 파일을 1M 단위로 나누어 읽고 처리하는 방식을 사용하면 한 번에 최대 1M의 메모리만 사용한다.
        readAllBytes()
            한 번의 호출로 모든 데이터를 읽을 수 있어 편리하다.
            작은 파일이나 메모리에 모든 내용을 올려서 처리해야 하는 경우에 적합하다.
            메모리 사용량을 제어할 수 없다.
            큰 파일의 경우 OutOfMemoryError가 발생할 수 있다.
     */
}

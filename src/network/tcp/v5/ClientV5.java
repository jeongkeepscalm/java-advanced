package network.tcp.v5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

/**
 * try-with-resources 적용
 * output, input, socket 순으로 close()
 *      try-with-resources 에 선언되는 순서의 반대대로 자원 정리 
 * OutputStream, InputStream, Socket 모두 AutoCloseable 구현
 */
public class ClientV5 {

    public static final int PORT = 12345;

    public static void main(String[] args) {
        log("클라이언트 시작");

        try (
                Socket socket = new Socket("localhost", PORT);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            log("소캣 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("전송 문자: ");
                String toSend = scanner.nextLine();

                // 서버에게 문자 보내기
                output.writeUTF(toSend);
                log("client -> server: " + toSend);

                if ("exit".equals(toSend)) break;

                // 서버로부터 문자 받기
                String received = input.readUTF();
                log("client <- server: " + received);
            }

        } catch (IOException e) {
            log(e);
        }

    }

}

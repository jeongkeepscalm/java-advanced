package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

/**
 * SessionV3
 *      소켓이 연결된 클라이언트와 메시지를 반복해서 주고 받는다.
 *      생성자를 통해 Socket 객체를 입력 받는다.
 *      Runnable 을 구현해서 별도의 스레드에서 실행한다.
 */
public class SessionV3 implements Runnable {

    private final Socket socket;

    public SessionV3(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF(); // 블로킹
                log("client -> server: " + received);

                if (received.equals("exit")) break;

                // 클라이언트에게 문자 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }

            // 자원 정리
            log("연결 종료: " + socket);
            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

/*
    1. 서버:      종료 socket.close() -> FIN 패킷 클라이언트로 전달
    2. 클라이언트: FIN 에 대한 ACK 패킷 서버에 전달
    3. 클라이언트: output.write(1): 서버에 메시지 전달
    4. 서버:      서버가 기대하는 값은 FIN 패킷이지만 메시지가 왔으므로 TCP 문제라 판단하여 즉각 연결 종료하라는 RST 패킷 클라이언트에 전송
    5. 클라이언트: RST 패킷이 도착하면 자바는 read(), write() 로 메시지를 읽을 때/전송할 때 예외를 던진다.

    RST(Reset)
         TCP 에서 RST 패킷은 연결 상태를 초기화(리셋)해서 더 이상 현재의 연결을 유지하지 않겠다는 의미를 전달한다.

    RST 패킷은 TCP 연결에 문제가 있는 다양한 상황에 발생한다.
        TCP 스펙에 맞지 않는 순서로 메시지가 전달될 때
        TCP 버퍼에 있는 데이터를 아직 다 읽지 않았는데, 연결을 종료할 때
        방화벽 같은 곳에서 연결을 강제로 종료할 때도 발생한다.
 */
public class ResetCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("localhost", 12345);
        log("소캣 연결: " + socket);

        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();

        // client <- server: FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때 까지 잠시 대기

        // client -> server: PUSH[1]
        output.write(1);

        // client <- server: RST
        Thread.sleep(1000); // RST 메시지 전송 대기

        try {
            // java.net.SocketException: Connection reset 발생!
            int read = input.read();
            System.out.println("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            output.write(1);
        }
        catch (SocketException e) {
            //java.net.SocketException: Broken pipe
            e.printStackTrace();
        }

    }


}

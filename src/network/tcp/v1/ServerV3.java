package network.tcp.v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

/**
 * main 스레드는 서버 소켓을 생성하고 serverSocket.accept() 를 호출해서 연결을 대기한다.
 * 새로운 연결이 추가될 때마다 SessionV3 객체를 생성하고
 * 별도의 스레드에서 Session 객체를 실행한다.
 */
public class ServerV3 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();// 블로킹
            log("소켓 연결: " + socket);

            SessionV3 session = new SessionV3(socket);
            Thread thread = new Thread(session);
            thread.start();
        }
    }

}

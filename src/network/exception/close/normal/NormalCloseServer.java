package network.exception.close.normal;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

/**
    서버는 소켓이 연결되면 1초 뒤에 연결을 종료한다.
    서버에서 socket.close() 를 호출하면 클라이언트에 FIN 패킷을 보낸다.
 */
public class NormalCloseServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Socket socket = serverSocket.accept();
        log("소캣 연결: " + socket);
        Thread.sleep(1000);
        socket.close();
        log("소캣 종료");
    }

}

package network.tcp.v1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

/**
 * V1: 메시지를 하나만 주고 받으면 클라이언트와 서버가 모두 종료
 */
public class ClientV1 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        /*
            127.0.0.1:12345 tcp 접속 시도
            Socket 객체를 통해 서버와 통신 가능
         */
        Socket socket = new Socket("localhost", PORT); 
        
        /*
            InputStream: 서버에서 전달한 데이터를 클라이언트가 받을 때 사용
            OutputStream: 클라이언트에서 서버에 데이터를 전달할 때 사용
         */
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        log("소캣 연결: " + socket);

        // 서버에게 문자 보내기
        String toSend = "Hello";
        dos.writeUTF(toSend);
        log("client -> server: " + toSend);

        // 서버로부터 문자 받기
        String received = dis.readUTF();
        log("client <- server: " + received);

        // 자원 정리
        log("연결 종료: " + socket);
        dis.close();
        dos.close();
        socket.close();
    }
}

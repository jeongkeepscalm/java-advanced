package network.tcp.v2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

/**
 * V2: 서버가 하나의 클라이언트의 연결만 처리
 *      ServerV2, ClientV2, ClientV2-2 차례대로 실행시킬 때,
 *      ClientV2-2 와 ServerV2 tcp 연결은 성공되지만
 *      OS backlog queue 에 먼저 연결된 ClientV2 정보가 존재하므로
 *      ClientV2-2 는 대기하게 된다.
 */
public class ClientV2 {

    public static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Socket socket = new Socket("localhost", PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        log("소캣 연결: " + socket);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("전송 문자: ");
            String toSend = scanner.nextLine();
            
            // 서버로 문자 보내기
            output.writeUTF(toSend);
            log("client -> server: " + toSend);

            if (toSend.equals("exit")) break;

            // 서버로부터 문자 받기
            String received = input.readUTF();
            log("client <- server: " + received);

        }

        // 자원 정리
        log("연결 종료: " + socket);
        input.close();
        output.close();
        socket.close();
    }
}

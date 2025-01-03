package network.exception.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
    실무에서 자주 발생하는 장애 원인 중 하나가 바로 연결 타임아웃,
    소켓 타임아웃(read 타임 아웃)을 누락하기 때문에 발생한다.

    고객 -> 주문 서버 -> 신용카드 회사 서버(문제)

    만약 주문 서버에 연결, 소켓 타임아웃을 적절히 설정했다면, 신용카드 C 회사 서버가 연결이 오래 걸리거나 응답을 주
    지 않을 때 타임아웃으로 처리할 수 있다. 이렇게 되면 요청이 쌓이지 않기 때문에, 주문 서버에 장애가 발생하지 않는
    다. 타임아웃이 발생하는 신용카드 C 사용자에게는 현재 문제가 있다는 안내를 하면 된다. 나머지 신용카드A, 신용카드
    B는 정상적으로 작동한다.
 */
public class SoTimeoutClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        InputStream input = socket.getInputStream();

        try {
            socket.setSoTimeout(3000);  // 타임아웃 시간 설정
            int read = input.read();    // 타임아웃 시간을 설정하지 않으면 무한 대기
            System.out.println("read = " + read);
        } catch (Exception e) {
            e.printStackTrace();
        }

        socket.close();

    }

}

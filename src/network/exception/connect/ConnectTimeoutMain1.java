package network.exception.connect;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

/**
    192.168.1.250: 사설 IP 대역(주로 공유기에서 사용하는 IP 대역)
    해당 IP로 연결 패킷을 보내지만 IP를 사용하는 서버가 없으므로 TCP 응답이 오지 않는다.

    OS 기본 대기 시간
        TCP 연결을 시도했는데 연결 응답이 없다면 OS 에는 연결 대기 타임아웃이 설정되어 있다.
        Windows: 약 21초
        Linux: 약 75초 ~ 180초
        mac test: 75초
    해당 시간이 지나면 java.net.ConnectException: Operation timed out 발생

    TCP 연결을 클라이언트가 이렇게 오래 대기하는 것은 좋은 방법이 아니다.
    연결이 안되면 고객에게 빠르게 현재 연결에 문제가 있다고 알려주는 것이 더 나은 방법이다.
 */
public class ConnectTimeoutMain1 {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        try {
            Socket socket = new Socket("192.168.1.250", 45678);
        } catch (ConnectException e) {
            // ConnectException: Operation timed out
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("end = " + (end - start));
    }

}

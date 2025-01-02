package network.tcp.v1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress
 *      호스트명으로 IP 찾기
 *      시스템의 호스트 파일 확인
 *          리눅스, mac: /etc/hosts
 *          윈도우: C:\Windows\System32\drivers\etc\hosts
 *      호스트 파일에 미정의되어 있을 경우, dns 서버에 요청하여 IP 주소를 얻는다.
 */

public class InetAddressMain {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println("localhost = " + localhost);

        InetAddress google = InetAddress.getByName("google.com");
        System.out.println("google = " + google);
    }

}

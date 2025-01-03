package network.tcp.v6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

/**
    서버 종료시, 서버 소켓과 연결된 모든 소켓 자원을 다 반납하고
    서버를 종료하기 위해 셧다운 훅을 사용한다.

    셧다운 훅
    프로세스가 종료될 때, 자원 정리나 로그 기록과 같은 종료 작업을 마무리할 수 있게 한다.

    프로세스 종료
        정상 종료(셧다운 훅 작동)
        모든 non 데몬 스레드의 실행 완료로 자바 프로세스 정상 종료
        사용자가 Ctrl+C를 눌러서 프로그램을 중단
        kill 명령 전달
        IntelliJ stop 버튼
    강제 종료(셧다운 훅 작동 x)
        운영체제에서 프로세스를 더 이상 유지할 수 없다고 판단할 때 사용
        리눅스/유닉스의 kill -9 혹은 윈도우의 taskkill /F
 */
public class ServerV6 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {

        log("서버 시작");
        SessionManagerV6 sessionManager = new SessionManagerV6();
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        // ShutdownHook 등록
        // Runtime.getRuntime().addShutdownHook(): 셧다운이 발생했을 때 처리할 작업과 스레드를 등록
        ShutdownHook shutdownHook = new ShutdownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(shutdownHook, "shutdown"));

        try {
            while (true) {
                Socket socket = serverSocket.accept();// 블로킹
                log("소켓 연결: " + socket);
                SessionV6 session = new SessionV6(socket, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소캣 종료: " + e);
        }

    }

    static class ShutdownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManagerV6 sessionManager;
        public ShutdownHook(ServerSocket serverSocket, SessionManagerV6 sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");
            try {
                // 연결된 스레드(클라이언트-서버)들 및 서버 소켓을 닫는다.
                sessionManager.closeAll();
                serverSocket.close();

                /*
                    자원 정리 대기
                    다른 스레드가 자원을 정리하거나 필요한 로그를 남길 수 있도록 셧다운 훅의 실행을 잠시 대기
                 */
                Thread.sleep(1000);
                
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }

}



package network.tcp.v6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

/**
 *  try-with-resources 는 사용과 해제를 함게 묶어서 처리할 때 사용한다.
 *
 *  try-with-resources 는 try 선언부에서 사용한 자원을 try 가 끝나는 시점에 정리한다.
 *  따라서 try 에서 자원의 선언과 자원 정리를 묶어서 처리할 때 사용할 수 있다.
 *  하지만 지금은 서버를 종료하는 시점에 Session 이 사용하는 자원을 정리해야 한다.
 *  서버를 종료하는 시점에 자원을 정리하는 것은 Session 안에 있는 try-with-resources 를 통해 처리할 수 없다.
 */
public class SessionV6 implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final SessionManagerV6 sessionManager;
    private boolean closed = false;

    public SessionV6(Socket socket, SessionManagerV6 sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.sessionManager = sessionManager;
        this.sessionManager.add(this);
    }

    @Override
    public void run() {

        try {
            while (true) {
                // 클라이언트로부터 문자 받기
                String received = input.readUTF();
                log("client -> server: " + received);
                if (received.equals("exit")) {
                    break;
                }
                // 클라이언트에게 문자 보내기
                String toSend = received + " World!";
                output.writeUTF(toSend);
                log("client <- server: " + toSend);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            close();
        }

    }
    
    /**
        close() 는 클라이언트와 연결이 종료되었을 때, 서버를 종료할 때 2곳에서 호출될 수 있다.
        따라서 close() 가 다른 스레드에서 동시에 중복 호출될 가능성이 존재한다.
        이런 문제를 막기위해 synchronized 키워드를 사용
     */
    public synchronized void close() {
        if (closed) return;
        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }

}

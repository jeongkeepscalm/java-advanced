package chat.client;

import java.io.DataInputStream;
import java.io.IOException;

import static util.MyLogger.log;

/**
 * 서버의 메시지를 반복해서 받고 콘솔에 출력한다.
 */
public class ReadHandler implements Runnable {

    private final DataInputStream input;
    private final Client client;
    public boolean closed = false;

    public ReadHandler(DataInputStream input, Client client) {
        this.input = input;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String received = input.readUTF();
                System.out.println("received = " + received);
            }
        } catch (IOException e) {
            log(e);
        } finally {
            client.close();
        }
    }

    public synchronized void close() {
        if (closed) return;

        // 종료 로직 필요시 이곳에 작성한다.
        closed = true;
        log("readHandler 종료");
    }


}

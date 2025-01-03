package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

/**
    서버로 부터 메시지를 읽는 3가지 방법
        read():     1 byte 단위로 읽음
        readLine(): 라인 단위로 String 으로 일음
        readUTF():  DataInputStream 을 통해 String 단위로 읽음
 */

public class NormalCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("localhost", 12345);
        log("소캣 연결: " + socket);
        InputStream input = socket.getInputStream();
        readByInputStream(input, socket);
        readByBufferedReader(input, socket);
        readByDataInputStream(input, socket);
        log("연결 종료: " + socket.isClosed());

        /*
            서버의 종료로 FIN 패킷을 받은 클라이언트는 각각의 상황에 따라 EOF 를 해석하는 방법이 다르다.
                -1, null, EOFException

            19:19:19.582 [     main] 소캣 연결: Socket[addr=localhost/127.0.0.1,port=12345,localport=6918]
            19:19:20.593 [     main] read = -1
            19:19:20.593 [     main] readString = null
            19:19:20.593 [     main] java.io.EOFException
            19:19:20.593 [     main] 연결 종료: true
         */

    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        log("read = " + read);
        if (read == -1) {
            input.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("readString = " + readString);
        if (readString == null) {
            br.close();
            socket.close();
        }
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);
        try {
            dis.readUTF();
        } catch (EOFException e) {
            log(e);
        } finally {
            dis.close();
            socket.close();
        }
    }

}

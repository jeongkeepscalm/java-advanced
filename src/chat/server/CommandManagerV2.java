package chat.server;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*
    입장 /join|{name}
    처음 채팅 서버에 접속할 때 사용자의 이름을 입력해야 한다.

    메시지 /message|{내용}
    모든 사용자에게 메시지를 전달한다.

    이름 변경 /change|{name}
    사용자의 이름을 변경한다.

    전체 사용자 /users
    채팅 서버에 접속한 전체 사용자 목록을 출력한다.

    종료 /exit
    채팅 서버의 접속을 종료한다
 */
public class CommandManagerV2 implements CommandManager {

    public static final String DELIMITER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {

        System.out.println("totalMessage: " + totalMessage);

        if (totalMessage.startsWith("/join")) {
            String[] split = totalMessage.split(DELIMITER);

            System.out.println(Arrays.toString(split));

            String username = split[1];
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장했습니다.");
        } else if (totalMessage.startsWith("/message")) {
            // 클라이언트 전체에게 문자 보내기
            String[] split = totalMessage.split(DELIMITER);
            String message = split[1];
            sessionManager.sendAll("[" + session.getUsername() + "] " + message);
        } else if (totalMessage.startsWith("/change")) {
            String[] split = totalMessage.split(DELIMITER);
            String changeName = split[1];
            sessionManager.sendAll(session.getUsername() + "님이 " + changeName + "로 이름을 변경했습니다.");
            session.setUsername(changeName);
        } else if (totalMessage.startsWith("/users")) {
            List<String> usernames = sessionManager.getAllUsername();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자 : ").append(usernames.size()).append("\n");
            for (String username : usernames) {
                sb.append(" - ").append(username).append("\n");
            }
            session.send(sb.toString());
        } else if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        } else {
            session.send("처리할 수 없는 명령어 입니다: " + totalMessage);
        }
    }

}

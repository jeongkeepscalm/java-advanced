package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class CommandManagerV3 implements CommandManager {

    public static final String DELIMITER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand =  new DefaultCommand();

    public CommandManagerV3(SessionManager sessionManager) {
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {

        String[] args = totalMessage.split(DELIMITER);
        String key = args[0];

        Command command = commands.getOrDefault(key, defaultCommand);
        command.execute(args, session);

        /**
         * Null Object Pattern
         *      null 대신 특별한 객체를 만들어, null 로 인해 발생할 수 있는 예외 상황을 방지하는 디자인 패턴
         *
         * Command Pattern
         *      Command 인터페이스와 그 구현체들이 바로 커맨드 패턴을 사용한 것이다.
         *
         * 커맨드 패턴의 장점
         *      이 패턴의 장점은 새로운 커맨드를 쉽게 추가할 수 있다는 점이다. 예를 들어, 새로운 커맨드를 추가하고 싶다면,
         *      새로운 Command 의 구현체만 만들면 된다. 그리고 기존 코드를 대부분 변경할 필요가 없다.
         *      작업을 호출하는 객체와 작업을 수행하는 객체가 분리되어 있다. 이전 코드에서는 작업을 호출하는 if 문이 각 작
         *      업마다 등장했는데, 커맨드 패턴에서는 이런 부분을 하나로 모아서 처리할 수 있다.
         *      각각의 기능이 명확하게 분리된다. 개발자가 어떤 기능을 수정해야 할 때, 수정해야 하는 클래스가 아주 명확해진
         *      다.
         *
         * 커맨드 패턴의 단점
         *      복잡성 증가: 간단한 작업을 수행하는 경우에도 Command 인터페이스와 구현체들, Command 객체를 호출하고
         *      관리하는 클래스등 여러 클래스를 생성해야 하기 때문에 코드의 복잡성이 증가할 수 있다.
         *      모든 설계에는 트레이드 오프가 있다. 예를 들어 단순한 if문 몇게로 해결할 수 있는 문제에 복잡한 커맨드 패턴을
         *      도입하는 것은 좋은 설계가 아닐 수 있다.
         *      기능이 어느정도 있고, 각각의 기능이 명확하게 나누어질 수 있고, 향후 기능의 확장까지 고려해야 한다면 커맨드
         *      패턴은 좋은 대안이 될 수 있다.
         */
    }

}

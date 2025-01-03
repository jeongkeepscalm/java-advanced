package network.tcp.v6;

import java.util.ArrayList;
import java.util.List;

/**
 * 모든 세션들을 찾아서 종료하려면 생성한 세션을 보관하고 관리할 객체
 */
public class SessionManagerV6 {

    private List<SessionV6> sessions = new ArrayList<>();

    public synchronized void add(SessionV6 session) {
        sessions.add(session);
    }

    public synchronized void remove(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }

}

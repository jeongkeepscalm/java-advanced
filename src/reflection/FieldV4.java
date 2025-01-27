package reflection;

import reflection.data.Team;
import reflection.data.User;

/**
 * FieldUtil.nullFieldToDefault(): 리플렉션을 활용한 필드 기본 값 도입
 */

public class FieldV4 {

    public static void main(String[] args) throws Exception {
        User user = new User("id1", null, null);
        Team team = new Team("team1", null);
        System.out.println("===== before =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
        FieldUtil.nullFieldToDefault(user);
        FieldUtil.nullFieldToDefault(team);
        System.out.println("===== after =====");
        System.out.println("user = " + user);
        System.out.println("team = " + team);
    }

}

package reflection;

import reflection.data.User;

import java.lang.reflect.Field;

/**
 * 리플렉션은 주로 테스트나 라이브러리 개발 같은 특별한 상황에서 유용하게 사용되지만,
 * 일반적인 애플리케이션 코드에서는 권장되지 않는다.
 * 이를 무분별하게 사용하면 코드의 가독성과 안전성을 크게 저하시킬 수 있다.
 */

public class FieldV2 {

    public static void main(String[] args) throws Exception {
        User user = new User("id1", "userA", 20);
        System.out.println("기존 이름 = " + user.getName());
        Class<? extends User> aClass = user.getClass();
        Field nameField = aClass.getDeclaredField("name");

        // private 필드에 접근 허용, private 메서드도 이렇게 호출 가능
        nameField.setAccessible(true);
        nameField.set(user, "userB");
        System.out.println("변경된 이름 = " + user.getName());
    }

}

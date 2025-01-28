package annotation.test;

public class UserMaskingTest {

    /**
     * Spring AOP 를 사용하여 어노테이션을 감지하고 마스킹 처리를 할 수 있다.
     * 아래는 리플렉션을 통해 마스킹 처리.
     */

    public static void main(String[] args) throws IllegalAccessException {
        // Given
        User user = new User("John Doe", "1234567890", 30);
        // When
        MaskingProcessor.maskSensitiveFields(user);
        // Then
        System.out.println("user = " + user);
    }
}
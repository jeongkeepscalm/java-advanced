package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Field;

/**
 * fields()
 *      해당 클래스와 상위 클래스에서 상속된 모든 public 필드를 반환
 * declaredFields()
 *      해당 클래스에서 선언된 모든 필드를 반환하며, 접근 제어자에 관계없이 반환.
 *      상속된 필드는 포함하지 않음
 */

public class FieldV1 {

    public static void main(String[] args) {

        Class<BasicData> helloClass = BasicData.class;

        System.out.println("====== fields() =====");
        Field[] fields = helloClass.getFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        System.out.println("====== declaredFields() =====");
        Field[] declaredFields = helloClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("declaredField = " + field);
        }
    }

}

package reflection;

import reflection.data.BasicData;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodV2 {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // 정적 메소드 호출 - 일반적인 메소드 호출
        BasicData helloInstance = new BasicData();
        helloInstance.call();

        // 동적 메소드 호출 - 리플렉션 사용
        Class<? extends BasicData> helloClass = helloInstance.getClass();
        String methodName = "hello";

        // 메소드 이름을 변수로 변경할 수 있다.
        Method method1 = helloClass.getDeclaredMethod(methodName, String.class);
        Object returnValue = method1.invoke(helloInstance, "hi");
        System.out.println("returnValue = " + returnValue);

        /*
            helloClass.getMethod(methodName, String.class)
                메소드명이 methodName, 매개변수가 String 인 메소드를 찾는다.

            method1.invoke(helloInstance, "hi")
                해당 인스턴스에 있는 메소드 실행
         */

    }

}

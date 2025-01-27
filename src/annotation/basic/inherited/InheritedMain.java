package annotation.basic.inherited;

import java.lang.annotation.Annotation;

/**
 * @Inherited 는 클래스 상속에만 적용된다.
 */
public class InheritedMain {

    public static void main(String[] args) {
        print(Parent.class);
        print(Child.class);
        print(TestInterface.class);
        print(TestInterfaceImpl.class);
    }

    private static void print(Class<?> clazz) {
        System.out.println("clazz = " + clazz);
        for (Annotation annotation : clazz.getAnnotations()) {
            String simpleName = annotation.annotationType().getSimpleName();
            System.out.println(" - " + simpleName);
        }
        System.out.println();
    }

}

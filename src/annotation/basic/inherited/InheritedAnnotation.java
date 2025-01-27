package annotation.basic.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited // 클래스 상속시 자식도 어노테이션이 적용된다.
@Retention(RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {}

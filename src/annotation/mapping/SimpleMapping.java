package annotation.mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * SimpleMapping 어노테이션 생성
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleMapping {
    String value();
}

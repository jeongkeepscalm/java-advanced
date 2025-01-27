package annotation.basic;

import java.lang.annotation.*;

/**
 * RetentionPolicy.RUNTIME
 *      자바 실행 중에도 어노테이션 정보가 남아있게 한다.
 * @Target({ElementType.METHOD, ElementType.TYPE})
 *      메소드와 타입(클래스)에 @AnnoMeta 어노테이션을 적용할 수 있다.
 *      다른 곳에 적용 시 컴파일 오류 발생
 * @Documented
 *      자바 API 문서를 만들 때 해당 어노테이션이 포함된다.
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AnnoMeta {
}

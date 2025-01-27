package annotation.basic;

import util.MyLogger;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 애노테이션 정의 규칙
 *
 * 데이터 타입
 *  기본 타입 (int, float, boolean 등)
 *  String
 *  Class (메타데이터) 또는 인터페이스
 *  enum
 *  다른 애노테이션 타입
 *  위의 타입들의 배열
 *  앞서 설명한 타입 외에는 정의할 수 없다. 쉽게 이야기해서 일반적인 클래스를 사용할 수 없다.
 *      예) Member , User , MyLogger
 *
 * default 값
 *  요소에 default 값을 지정할 수 있다.
 *  예: String value() default "기본 값을 적용합니다.";
 *
 * 요소 이름
 *  메서드 형태로 정의된다.
 *  괄호()를 포함하되 매개변수는 없어야 한다.
 *
 * 반환 값
 *  void 를 반환 타입으로 사용할 수 없다.
 *
 * 예외
 *  예외를 선언할 수 없다.
 *
 * 특별한 요소 이름
 *  value 라는 이름의 요소를 하나만 가질 경우, 애노테이션 사용 시 요소 이름을 생략할 수 있다
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnoElement {

    String value();
    int count() default 0;
    String[] tags() default {};

    // 다른 타입은 적용이 안된다.
    // MyLogger data();

    // 클래스 정보는 가능하다.
    Class<? extends MyLogger> annoData() default MyLogger.class;

}

package network.tcp.autocloseable;

/**
 * V2: 예외가 발생해도 자원을 정리한다.
 * 문제점
 *      close() 시점에 실수로 예외를 던지면, 이후 다른 자원을 닫을 수 없는 문제 발생
 *      finally 블럭 안에서 자원을 닫을 때 예외가 발생하면,
 *      핵심 예외가 finally 에ㅓ 발생한 부가 예외로 바뀌어 버린다.
 */
public class ResourceCloseMainV2 {

    public static void main(String[] args) {

        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            e.printStackTrace();
        } catch (CloseException e) {
            System.out.println("CloseException 예외 처리");
            e.printStackTrace();
        }

    }

    private static void logic() throws CallException, CloseException {

        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx(); // CallException
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e; // CallException 을 다시 던진다.
        } finally {

            /*
                null 체크
                    resource2 객체를 생성하기 전에 예외가 발생하면 resource2 는 null 이 된다.
                    따라서 null 체크를 한다.
             */
            if (resource2 != null) {
                resource2.closeEx(); // CloseException 발생
            }
            if (resource1 != null) {
                resource1.closeEx(); // 해당 코드 호출 안됨
            }
        }


    }
}

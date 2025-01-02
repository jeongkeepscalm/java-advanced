package network.tcp.autocloseable;

/**
 * finally 블럭에서도 예외를 잡아서 처리. 즉 자원을 닫을 수 있게 되었다.
 * 자원 정리 시점에 발생한 예외를 잡아서 처리했기에 자원정리 시점에 발생한 부가 예외가 핵심 예외를 가리지 않는다.
 * 자언 정리 시점에 발생한 예외는 당장 더 처리할 수 있는 부분이 없기에 로그를 남겨서 개발자가 인지할 수 있게하는 정도면 충분하다.
 */
public class ResourceCloseMainV3 {

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
                try {
                    resource2.closeEx(); // CloseException 발생
                } catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
            if (resource1 != null) {
                try {
                    resource1.closeEx();
                }
                catch (CloseException e) {
                    System.out.println("close ex: " + e);
                }
            }
        }


    }
}

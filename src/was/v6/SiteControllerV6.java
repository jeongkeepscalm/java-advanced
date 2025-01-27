package was.v6;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

/**
 * 리플렉션을 처리하는 서블릿
 */
public class SiteControllerV6 {

    public void site1(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site1</h1>");
    }
    public void site2(HttpRequest request, HttpResponse response) {
        response.writeBody("<h1>site2</h1>");
    }

}

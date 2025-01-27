package was.httpserver.servlet;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.HttpServlet;

import java.io.IOException;

public class DiscardServlet implements HttpServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        // empty: /favicon.ico 의 경우 아무 일도 하지 않고 요청을 무시하는 용도로 사용한다.
    }
}

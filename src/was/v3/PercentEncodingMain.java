package was.v3;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * % 인코딩
 */

public class PercentEncodingMain {

    public static void main(String[] args) {
        String encode = URLEncoder.encode("가", StandardCharsets.UTF_8);
        System.out.println("encode = " + encode);

        String decode = URLDecoder.decode(encode, StandardCharsets.UTF_8);
        System.out.println("decode = " + decode);
    }

}

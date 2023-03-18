package cn.caoh2.app.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author caoh2
 * @Date 2023/3/18 14:43
 * @Description: web工具类
 * @Version 1.0
 */
@Component
public class WebUtils {

    /**
     * 返回json字符串
     *
     * @param response
     * @param string
     * @return
     */
    @SuppressWarnings("all")
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}

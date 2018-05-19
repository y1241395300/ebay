package cn.sdcit.Interceptor;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CorsInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(CorsInterceptor.class);
    public static final String CALLBACK_FUNCTION_NAME = "callback";
    public static final String JSONP_FLAG = "jsonpFlag";
    public static final String TIME_ATTRIBUTE = "timeAttribute";
    public static final String CHARACTER_UTF8 = "UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf8";
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
        String callback = request.getParameter(CALLBACK_FUNCTION_NAME);
        if (StringUtils.isNotBlank(callback)) {
            response.setCharacterEncoding(CHARACTER_UTF8);
            response.setContentType(CONTENT_TYPE_JSON);
            write(response, callback + "(");
            request.setAttribute(JSONP_FLAG, true);
        } else {
            request.setAttribute(JSONP_FLAG, false);
        }
        request.setAttribute(TIME_ATTRIBUTE, System.currentTimeMillis());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView) throws Exception {
        boolean jsonpFlag = (Boolean)request.getAttribute(JSONP_FLAG);
        if (jsonpFlag) {
            write(response, ")");
        }
        long startTime = (Long)request.getAttribute(TIME_ATTRIBUTE);
        logger.info("------本次调用" + request.getRequestURI() + "耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // do nothing
    }

    /**
     * 写入信息
     * @param response
     * @param value
     * @throws IOException
     */
    private void write(HttpServletResponse response, String value) throws IOException{
        OutputStream os = response.getOutputStream();
        os.write(value.getBytes());
        os.flush();
    }

}

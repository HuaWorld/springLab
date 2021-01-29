package com.evan.xh.interceptor;

import com.evan.xh.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 旅行option请求，否则无法让前端带上自定义的header信息，导致sessionID该表，shiro验证失败
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return false;
        }
        return true;


//        HttpSession session = request.getSession();
//        String contextPath = session.getServletContext().getContextPath();
//        String[] requireAuthPages = {"index"};
//        String uri = request.getRequestURI();
//        uri = StringUtils.remove(uri, contextPath + "/");
//        String page = uri;
//        if(begingWith(page,requireAuthPages)){
//            User user = (User) session.getAttribute("user");
//            if(user == null){
//                response.sendRedirect("login");
//                return false;
//            }
//        }
//        return true;
    }
    /**
     * 筛选需要认证界面
    **/
    public boolean begingWith(String page,String[] requiredAuthPages){
        boolean result = false;
        for (String requiredAuthPage:requiredAuthPages) {
            if(StringUtils.startsWith(page,requiredAuthPage)){
                result = true;
                break;
            }
        }
        return result;
    }
}

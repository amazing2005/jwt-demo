package com.example.tokenjwtdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.example.tokenjwtdemo.entity.CheckAuthResponse;
import com.example.tokenjwtdemo.service.UserService;
import com.example.tokenjwtdemo.util.Const;
import com.example.tokenjwtdemo.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    private UserService userService = new UserService();

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle");

        String accessToken = CookieUtil.getCookieValue(request, Const.ACCESS_TOKEN);
        logger.info("access_token=" + accessToken);
        if (StringUtils.isEmpty(accessToken)) {
            JSONObject object = new JSONObject();
            object.put("code", -1);
            object.put("msg", "请登录");
            response.getWriter().write(object.toJSONString());
            response.sendRedirect("/login");
            return false;
        }

        CheckAuthResponse checkAuthResponse = userService.checkAuth(accessToken);
        if (Const.SUCCESS.equals(checkAuthResponse.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        System.out.println("afterCompletion");

    }
}

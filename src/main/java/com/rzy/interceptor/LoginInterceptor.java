package com.rzy.interceptor;
import com.rzy.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if(user != null){
            //已经是登录状态，放行
            return true;
        }else{
            //访问者没有登录，就不能访问main页面
            request.setAttribute("loginError","您还没有登录，请登录后访问！");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }
    }
}

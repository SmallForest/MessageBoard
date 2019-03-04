package com.yangsen.servlet;

import com.google.code.kaptcha.Constants;
import com.yangsen.bean.User;
import com.yangsen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = UserService.getInstance();
    }

    @Override
    public void destroy() {
        super.destroy();
        userService = null;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String yzm = request.getParameter("yzm");
        String s_code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (yzm != null && s_code != null) {
            if (!yzm.equalsIgnoreCase(s_code)) {
                System.out.println("验证码错误");
                request.getRequestDispatcher("/login.do").forward(request, response);
                return;
            }
        } else {
            System.out.println("验证码错误");
            request.getRequestDispatcher("/login.do").forward(request, response);
            return;
        }
        User user = userService.login(username, password);
        if (user != null) {
            //用户信息存储在session中
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/message/list.do").forward(request, response);
            return;
        } else {
            request.getRequestDispatcher("/login.do").forward(request, response);
            return;
        }


    }
}

package com.yangsen.servlet;

import com.google.code.kaptcha.Constants;
import com.yangsen.bean.User;
import com.yangsen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (Objects.equals("/regPrompt.do", path)) {
            //展示注册页面
            request.getRequestDispatcher("/WEB-INF/views/biz/reg.jsp").forward(request, response);
        } else if (Objects.equals("/reg.do", path)) {
            //执行注册流程
            String username = request.getParameter("username");
            String password=request.getParameter("password");
            String password2=request.getParameter("password2");
            String yzm = request.getParameter("yzm");
            //判断验证码
            String s_code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (yzm != null && s_code != null) {
                if (!yzm.equalsIgnoreCase(s_code)) {
                    System.out.println("验证码错误");
                    request.getRequestDispatcher("/regPrompt.do").forward(request, response);
                    return;
                }
            } else {
                System.out.println("验证码错误");
                request.getRequestDispatcher("/regPrompt.do").forward(request, response);
                return;
            }
            //判断两次密码是否一致
            if (!Objects.equals(password,password2)){
                System.out.println("两次密码不一致");
                request.getRequestDispatcher("/regPrompt.do").forward(request, response);
                return;
            }
            //判断username是否存在
            User user = userService.getUserByUsername(username);
            if(null != user){
                //username重复 不允许注册
                System.out.println("用户名已存在");
                request.getRequestDispatcher("/regPrompt.do").forward(request, response);
                return;
            }
            //
            boolean r = userService.insertUser(new User(username,password));
            if (r){
                request.getRequestDispatcher("/login.do").forward(request, response);
                return;
            }else{
                request.getRequestDispatcher("/regPrompt.do").forward(request, response);
                return;
            }


        } else {
            //to 404
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);

        }
    }

    @Override
    public void destroy() {
        super.destroy();
        userService = null;
    }
}

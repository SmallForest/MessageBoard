package com.yangsen.servlet;

import com.yangsen.bean.User;
import com.yangsen.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void destroy() {
        super.destroy();
        userService = null;
    }

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (Objects.equals("/userInfo.do", path)) {
            request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
        } else if (Objects.equals("/editUserPrompt.do", path)) {
            //通过ID获取用信息
            int id = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUserById(id);
            if (null != user) {
                request.getRequestDispatcher("/WEB-INF/views/biz/edit_user.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
            }

        } else if (Objects.equals("/editUser.do", path)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String realName = request.getParameter("realName");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            User user = new User();
            user.setId(id);
            user.setUsername(name);
            user.setPassword(password);
            user.setRealname(realName);
            user.setPhone(phone);
            user.setAddress(address);
            user.setBirthday(birthday);

            Boolean r = userService.updateUser(user);
            if (r) {
                request.getRequestDispatcher("/WEB-INF/views/biz/user.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
        }
    }
}

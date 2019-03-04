package com.yangsen.servlet;

import com.yangsen.bean.Message;
import com.yangsen.bean.User;
import com.yangsen.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 留言处理
 */
public class MessageServlet extends HttpServlet {
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = MessageService.getInstance();
    }

    @Override
    public void destroy() {
        super.destroy();
        messageService = null;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (Objects.equals("/addMessagePrompt.do", path)) {
            //展示页面
            request.getRequestDispatcher("/WEB-INF/views/biz/add_message.jsp").forward(request, response);
        } else if (Objects.equals("/addMessage.do", path)) {
            //执行保存操作
            //获取user信息
            User user = (User) request.getSession().getAttribute("user");
            if (null == user) {
                request.getRequestDispatcher("/message/list.do").forward(request, response);
            } else {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Message message = new Message(user.getId(), user.getUsername(), title, content);
                boolean result = messageService.addMessage(message);
                if (result){
                    request.getRequestDispatcher("/message/list.do").forward(request, response);
                }else {
                    request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                }
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
        }

    }
}

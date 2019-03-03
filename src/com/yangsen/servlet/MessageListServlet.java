package com.yangsen.servlet;

import com.yangsen.bean.Message;
import com.yangsen.bean.User;
import com.yangsen.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 消息列表的servlet
 */
public class MessageListServlet extends HttpServlet {
    //声明service
    private MessageService messageService;

    @Override
    public void init() throws ServletException {
        super.init();
        messageService = new MessageService();
    }

    @Override
    public void destroy() {
        super.destroy();
        messageService = null;
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (Objects.equals("/message/list.do", path)) {
            String pageStr = request.getParameter("page");
            int page = 1;//页码默认值1
            if (pageStr != null && !"".equals(pageStr)) {
                //pageStr不等于空 而且不等于空字符串
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    System.out.println("页码类型错误！无法转为int");
                    e.printStackTrace();
                }
            }
            int pageSize = 5;
            List<Message> messages = messageService.getMessages(page, pageSize);//查询全部留言
            int count = messageService.countMessages();

            //计算最后一页页码
            int last = count % pageSize == 0 ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("last", last);
            request.setAttribute("messages", messages);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/WEB-INF/views/biz/message_list.jsp").forward(request, response);
            return;
        } else if (Objects.equals("/my/message/list.do", path)) {
            //我的留言
            String pageStr = request.getParameter("page");
            int page = 1;//页码默认值1
            if (pageStr != null && !"".equals(pageStr)) {
                //pageStr不等于空 而且不等于空字符串
                try {
                    page = Integer.parseInt(pageStr);
                } catch (NumberFormatException e) {
                    System.out.println("页码类型错误！无法转为int");
                    e.printStackTrace();
                }
            }
            int pageSize = 5;
            User user = (User) request.getSession().getAttribute("user");
            List<Message> messages = messageService.getMessagesById(page, pageSize, user.getId());//查询全部留言
            int count = messageService.countMessagesById(user.getId());

            //计算最后一页页码
            int last = count % pageSize == 0 ? (count / pageSize) : (count / pageSize) + 1;
            request.setAttribute("last", last);
            request.setAttribute("messages", messages);
            request.setAttribute("page", page);
            request.getRequestDispatcher("/WEB-INF/views/biz/mymessage_list.jsp").forward(request, response);
            return;
        } else if (Objects.equals("/edit/message.do", path)) {
            //编辑留言页面
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            Message message = messageService.getMessageById(id);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/views/biz/edit_message.jsp").forward(request, response);
            return;
        } else if (Objects.equals("/doedit/message.do", path)) {
            //执行编辑留言
            String idStr = request.getParameter("id");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            Message message = new Message(Integer.parseInt(idStr), title, content);
            boolean r = messageService.updateMessage(message);
            if (r) {
                request.getRequestDispatcher("/my/message/list.do").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
                return;
            }


        } else if (Objects.equals("/del/message.do", path)) {
            //删除ajax方式
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            Message message = messageService.getMessageById(id);
            User user = (User) request.getSession().getAttribute("user");
            // 比较用户权限
            if (message.getUserId() != user.getId()) {
                System.out.println("没有删除权限");
                request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
            }
            //尝试删除
            boolean r = messageService.delMessageById(id);
            if (r) {
                response.getWriter().print(1);
            } else {
                response.getWriter().print(0);
            }

        } else {
            // to 404
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
        }


    }
}

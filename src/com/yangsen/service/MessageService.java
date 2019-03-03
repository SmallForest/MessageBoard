package com.yangsen.service;

import com.yangsen.bean.Message;
import com.yangsen.dao.MessageDAO;


import java.util.Date;
import java.util.List;

public class MessageService {
    private MessageDAO messageDao;

    /**
     * 通过狗仔构造方法生成dao
     */
    public MessageService() {
        messageDao = new MessageDAO();
    }

    /**
     * 获取所有类型的分页数据
     *
     * @param page
     * @param pageSize
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        return messageDao.getMessages(page, pageSize);
    }

    /**
     * 通过Id获取分页
     *
     * @param page
     * @param pageSize
     * @param id
     * @return
     */
    public List<Message> getMessagesById(int page, int pageSize, int id) {
        return messageDao.getMessagesById(page, pageSize, id);
    }

    /**
     * 计算所有留言总数
     *
     * @return
     */
    public int countMessages() {
        return messageDao.countMessages();
    }

    /**
     * 通过id计算所有留言总数
     *
     * @return
     */
    public int countMessagesById(int id) {
        return messageDao.countMessagesById(id);
    }

    /**
     * 添加留言
     *
     * @param message
     * @return
     */
    public boolean addMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDao.addMessage(message);
    }
    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean delMessageById(int id) {
        return messageDao.delMessageById(id);
    }
    /**
     * 通过留言的索引Id 获取留言
     * @param id
     * @return
     */
    public Message getMessageById(int id){
        return messageDao.getMessageById(id);
    }

    public boolean updateMessage(Message message){
        return messageDao.updateMessage(message);
    }
}

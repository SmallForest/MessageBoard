package com.yangsen.dao;

import com.yangsen.bean.Message;
import com.yangsen.utils.JDBCC3P0utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    /**
     * 分页查询 message
     *
     * @param page     页码
     * @param pageSize 每页数量
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集
        List<Message> message = new ArrayList<>();
        try {
            connection = JDBCC3P0utils.getConnection();

            String sql = "select * from message order by id desc limit ?,?";
            //预处理
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, (page - 1) * pageSize);//偏移量
            preparedStatement.setInt(2, pageSize);//查询长度 一般固定

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message.add(new Message(resultSet.getInt("id"), resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("create_time")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return message;
    }

    /**
     * 计算所有留言总数
     *
     * @return
     */
    public int countMessages() {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集

        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "select count(id) as total from message";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return 0;
    }

    /**
     * 保存留言
     *
     * @return
     */
    public boolean addMessage(Message message) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat

        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "insert into message(user_id,username,title,content,create_time) values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.getUserId());
            preparedStatement.setString(2, message.getUsername());
            preparedStatement.setString(3, message.getTitle());
            preparedStatement.setString(4, message.getContent());
            preparedStatement.setTimestamp(5, new Timestamp(message.getCreateTime().getTime()));
            int i = preparedStatement.executeUpdate();
            System.out.println("插入影响条数" + i);
            return i > 0;

        } catch (Exception e) {
            System.out.println("保存留言信息失败！");
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(preparedStatement, connection);
        }
        return false;
    }

    /**
     * 分页查询 message
     *
     * @param page     页码
     * @param pageSize 每页数量
     * @return
     */
    public List<Message> getMessagesById(int page, int pageSize, int id) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集
        List<Message> message = new ArrayList<>();
        try {
            connection = JDBCC3P0utils.getConnection();

            String sql = "select * from message where user_id=? order by id desc limit ?,?";
            //预处理
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, id);//偏移量
            preparedStatement.setInt(2, (page - 1) * pageSize);//偏移量
            preparedStatement.setInt(3, pageSize);//查询长度 一般固定

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                message.add(new Message(resultSet.getInt("id"), resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("create_time")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return message;
    }

    /**
     * 通过id计算所有留言总数
     *
     * @return
     */
    public int countMessagesById(int id) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集

        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "select count(id) as total from message where user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return 0;
    }

    /**
     * 删除用户
     *
     * @param id 留言的Id
     * @return
     */
    public boolean delMessageById(int id) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat

        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "delete from message where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            return i > 0;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(preparedStatement, connection);
        }
        return false;
    }

    /**
     * 通过留言的索引Id 获取留言
     *
     * @param id
     * @return
     */
    public Message getMessageById(int id) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集

        try {
            connection = JDBCC3P0utils.getConnection();

            String sql = "select * from message where id=?";
            //预处理
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Message(resultSet.getInt("id"), resultSet.getInt("user_id"), resultSet.getString("username"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getTimestamp("create_time"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return null;
    }

    public boolean updateMessage(Message message) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat

        try {
            connection = JDBCC3P0utils.getConnection();

            String sql = "update message set title=?,content=? where id=?";
            //预处理
            preparedStatement = connection.prepareStatement(sql);
            //设置参数
            preparedStatement.setString(1, message.getTitle());
            preparedStatement.setString(2, message.getContent());
            preparedStatement.setInt(3, message.getId());

            int i = preparedStatement.executeUpdate();
            return i > 0;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(preparedStatement, connection);
        }
        return false;
    }
}

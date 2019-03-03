package com.yangsen.dao;

import com.yangsen.bean.User;
import com.yangsen.utils.JDBCC3P0utils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 成功返回用户 失败返回空
     */
    public User login(String username, String password) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集
        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "select * from user where username=? and password=?";
            //预处理SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            //执行
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("real_name"), resultSet.getString("birthday"), resultSet.getString("phone"), resultSet.getString("address"));
            }
        } catch (Exception e) {
            System.out.println("登录失败！");
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return null;
    }

    /**
     * 通过Id获取user
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集
        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "select * from user where id=?";
            //预处理SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            //执行
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("real_name"), resultSet.getString("birthday"), resultSet.getString("phone"), resultSet.getString("address"));
            }
        } catch (Exception e) {
            System.out.println("通过ID获取User信息失败！");
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return null;
    }

    /**
     * 通过username获取用户信息
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat
        ResultSet resultSet = null;//结果集
        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "select * from user where username=?";
            //预处理SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            //执行
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("real_name"), resultSet.getString("birthday"), resultSet.getString("phone"), resultSet.getString("address"));
            }
        } catch (Exception e) {
            System.out.println("通过username获取User信息失败！");
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(resultSet, preparedStatement, connection);
        }
        return null;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    public boolean updateUser(User user) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat

        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "update user set username=?,password=?,real_name=?,birthday=?,phone=?,address=? where id=?";
            //预处理SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRealname());
            // 这里比较特殊一点
            preparedStatement.setString(4, user.getBirthday());
            preparedStatement.setString(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setInt(7, user.getId());
            //执行
            int i = preparedStatement.executeUpdate();
            return i > 0;


        } catch (Exception e) {
            System.out.println("更新User信息获取失败！");
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(preparedStatement, connection);
        }
        return false;
    }

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user) {
        Connection connection = null;//连接
        PreparedStatement preparedStatement = null;//预处理stat

        try {
            connection = JDBCC3P0utils.getConnection();
            String sql = "insert into user(username,password) values (?,?)";
            //预处理SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            //执行
            int i = preparedStatement.executeUpdate();
            return i > 0;


        } catch (Exception e) {
            System.out.println("插入User信息获取失败！");
            e.printStackTrace();
        } finally {
            JDBCC3P0utils.destory(preparedStatement, connection);
        }
        return false;
    }
}

package com.yangsen.service;

import com.yangsen.bean.User;
import com.yangsen.dao.UserDAO;

public class UserService {
    private UserDAO userDAO;

    // 私有构造方法
    private UserService() {
        userDAO = new UserDAO();
    }
    // 声明属性
    private static UserService single;

    public static UserService getInstance(){
        if (null == single){
            synchronized (UserService.class){
                if (null == single){
                    return new UserService();
                }
            }
        }
        return single;
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    /**
     * 通过ID获取用户信息
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    /**
     * 通过username获取user
     *
     * @param username
     * @return
     */
    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    public boolean insertUser(User user) {
        return userDAO.insertUser(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
}

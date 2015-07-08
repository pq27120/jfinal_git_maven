package com.xuan.producer.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.xuan.model.User;
import com.xuan.service.UserService;

import java.util.Date;

/**
 * 类简述
 * <p>
 * 类说明、详细描述
 * </p>
 *
 * @author pengqiang@asiainfo.com
 * @version 1.0.0
 * @Company 亚信科技
 * @Copyright 亚信科技
 * @CreateDate 2015-07-07 16:24
 */

public class UserServiceImpl implements UserService {

    private User userDao;

    public Page<User> paginate(int pageNumber, int pageSize) {
        Page<User> user = userDao.paginate(pageNumber, pageSize, "select *", "from user order by id asc");
        return user;
    }

    public void update(User user) {
        if (user == null) {
            return;
        }
        user.set("time",new Date()).update();
    }

    public User save(User user) {
        if (user == null) {
            return null;
        }
        user.set("time",new Date()).save();
        return user;
    }

    public User findById(String id) {
        User user = userDao.findById(id);
        return user;
    }

    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * @param userDao
     */
    public void setUserDao(User userDao) {
        this.userDao = userDao;
    }
}

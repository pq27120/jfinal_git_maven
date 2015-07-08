package com.xuan.model;

import com.jfinal.plugin.activerecord.Model;

import java.util.List;

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
 * @CreateDate 2015-07-07 16:29
 */

public class User extends Model<User> {
    public static final User dao = new User();

    public List<Pay> getPays() {
        return Pay.dao.find("select * from pay where id=?",
                get("id"));
    }
}

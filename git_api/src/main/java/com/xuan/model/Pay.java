package com.xuan.model;

import com.jfinal.plugin.activerecord.Model;

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

public class Pay extends Model<Pay> {

    public static final Pay dao = new Pay();

    public User getUser() {
        return User.dao.findById(get("id"));
    }
}

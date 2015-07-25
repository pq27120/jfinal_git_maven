package com.xuan.common;

import com.jfinal.config.Routes;
import com.xuan.action.IDCExtendController;
import com.xuan.action.PayController;
import com.xuan.action.UserApiController;
import com.xuan.action.UserController;
import com.xuan.weixin.action.HelloController;

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
 * @CreateDate 2015-07-06 14:36
 */

public class AdminRoute extends Routes {

    @Override
    public void config() {
        add("/bootstrap", HelloController.class);
        add("/user", UserController.class);
        add("/dormitory", UserApiController.class);
        add("/pay", PayController.class);
        add("/idc", IDCExtendController.class);
    }
}

package com.xuan.common;

import com.jfinal.config.Routes;
import com.xuan.weixin.action.WeixinApiController;
import com.xuan.weixin.action.WeixinMsgController;

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
 * @CreateDate 2015-07-06 14:34
 */

public class WeiXinRoute extends Routes {
    @Override
    public void config() {
        add("/msg", WeixinMsgController.class);
        add("/api", WeixinApiController.class, "/api");
    }
}

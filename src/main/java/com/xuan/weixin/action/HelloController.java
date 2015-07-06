package com.xuan.weixin.action;

import com.jfinal.core.Controller;

/**
 * 后台管理控制类
 * <p>
 * 类说明、详细描述
 * </p>
 *
 * @author pengqiang@asiainfo.com
 * @version 1.0.0
 * @Company 亚信科技
 * @Copyright 亚信科技
 * @CreateDate 2015-07-06 14:39
 */

public class HelloController extends Controller{

    public void index(){
        render("/app/hello/index.html");
    }

    public void logon(){
        render("/app/hello/logon.html");
    }
}

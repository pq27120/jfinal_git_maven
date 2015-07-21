package com.xuan.action;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.xuan.model.User;
import com.xuan.plugin.spring.Inject;
import com.xuan.plugin.spring.IocInterceptor;
import com.xuan.service.UserService;

import java.util.List;

/**
 * 类简述
 * <p>
 * GET     /user           ---> index
 * GET     /user/id        ---> show
 * GET     /user/add       ---> add
 * POST    /user           ---> save
 * GET     /user/edit/id   ---> edit
 * PUT     /user/id        ---> update
 * DELETE  /user/id        ---> delete
 * </p>
 *
 * @author pengqiang@asiainfo.com
 * @version 1.0.0
 * @Company 亚信科技
 * @Copyright 亚信科技
 * @CreateDate 2015-07-20 21:13
 */
@Before(IocInterceptor.class)
public class UserApiController extends Controller{
    @Inject.BY_NAME
    private UserService userService;

    /**
     * 响应/dormitory/
     */
    public void index(){
        List<User> userList = userService.findList();
        //允许哪些url可以跨域请求到本域
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        getResponse().setHeader("Access-Control-Allow-Methods", "GET");
        //允许哪些请求头可以跨域
        getResponse().setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");

        renderJson(userList);
    }

    /**
     * 对应/dormitory/{id}
     */
    public void show(){
        //允许哪些url可以跨域请求到本域
        getResponse().setHeader("Access-Control-Allow-Origin", "*");
        //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
        getResponse().setHeader("Access-Control-Allow-Methods", "GET");
        //允许哪些请求头可以跨域
        getResponse().setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");

        String id = getPara(0);
        User user = userService.findById(id);
        renderJson(user);
    }
}

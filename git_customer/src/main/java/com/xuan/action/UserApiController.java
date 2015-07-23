package com.xuan.action;

import com.alibaba.druid.util.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.Restful;
import com.xuan.model.User;
import com.xuan.plugin.spring.Inject;
import com.xuan.plugin.spring.IocInterceptor;
import com.xuan.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Before({Restful.class, IocInterceptor.class})
public class UserApiController extends Controller {

    @Inject.BY_NAME
    private UserService userService;

    /**
     * 响应/dormitory/
     */
    public void index() {
        List<User> userList = userService.findList();
        renderJson(userList);
    }

    /**
     * 对应/dormitory/{id}
     */
    public void show() {
        String id = getPara(0);
        User user = userService.findById(id);
        renderJson(user);
    }

    /**
     * 对应/dormitory/
     */
    public void save() {
        Map map = convertParam();
        String name = map.get("name").toString();
        User user = new User().set("time", new Date()).set("name", name);
        userService.save(user);
        renderJson(true);
    }

    /**
     * 对应/dormitory/edit/{id}
     */
    public void edit() {
        String id = getPara(0);
        User user = userService.findById(id);
        renderJson(user);
    }

    /**
     * 对应/dormitory/{id}
     */
    public void update() throws IOException {
        String id = getPara(0);
        Map map = convertParam();
        String name = map.get("name").toString();
        User user = new User().set("time", new Date()).set("name", name).set("id", id);
        userService.update(user);
        renderJson(true);
    }

    /**
     * 获取http body中的参数
     * PUT请求参数存放在HTTP BODY中
     * @return
     */
    private Map convertParam() {
        Map map = new HashMap();
        try {
            String queryString = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(getRequest().getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                queryString += line;
            }
            for (String s1 : queryString.split("&")) {
                String[] keyValue = s1.split("=");
                map.put(keyValue[0], keyValue[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 对应/dormitory/{id}
     */
    public void delete() {
        String id = getPara(0);
        userService.deleteById(id);
        renderJson(true);
    }
}

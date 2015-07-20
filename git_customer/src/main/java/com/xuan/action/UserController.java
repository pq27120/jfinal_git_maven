package com.xuan.action;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.xuan.model.User;
import com.xuan.plugin.spring.Inject;
import com.xuan.plugin.spring.IocInterceptor;
import com.xuan.service.PayService;
import com.xuan.service.UserService;
import com.xuan.validator.UserValidator;

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
 * @CreateDate 2015-07-07 16:12
 */
@Before(IocInterceptor.class)
public class UserController extends Controller {

    @Inject.BY_NAME
    private UserService userService;

    @Inject.BY_NAME
    private PayService payService;

    public void index() {
        Page<User> userPage = userService.paginate(getParaToInt(0, 1), 10);
        setAttr("userPage", userPage);
        render("/app/hello/dormitory/user.ftl");
    }

    public void add() {
        render("/app/hello/dormitory/user_edit.ftl");
    }

    @Before(UserValidator.class)
    public void saveOrUpdate() {
        User user = getModel(User.class);
        if (null != user.get("id")) {
            userService.update(user);
        } else {
            userService.save(user);
        }
        redirect("/user");
    }

    public void edit() {
        String id = getPara(0);
        User user = userService.findById(id);
        setAttr("user", user);
        render("/app/hello/dormitory/user_edit.ftl");
    }

    public void del() {
        String id = getPara(0);
        if (payService.findCountByUserId(id) > 0) {

        } else {
            userService.deleteById(id);
            redirect("/user");
        }
    }

    public void userList(){
        List<User> userList = userService.findList();
        setAttr("userList", userList);
        renderJson();
    }
}

package com.xuan.action;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.xuan.model.Pay;
import com.xuan.model.User;
import com.xuan.service.PayService;
import com.xuan.service.UserService;
import com.xuan.validator.PayValidator;

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
 * @CreateDate 2015-07-07 16:44
 */
@Before(IocInterceptor.class)
public class PayController extends Controller {
    @Inject.BY_NAME
    private PayService payService;

    @Inject.BY_NAME
    private UserService userService;

    public void index() {
        Page<Pay> payPage = payService.paginate(getParaToInt(0, 1), 10);
        setAttr("payPage", payPage);
        render("/app/hello/dormitory/pay.ftl");
    }

    public void add() {
        Page<User> userPage = userService.paginate(getParaToInt(0, 1), 10);
        setAttr("userPage",userPage);
        render("/app/hello/dormitory/pay_edit.ftl");
    }

    @Before(PayValidator.class)
    public void saveOrUpdate() {
        Pay pay = getModel(Pay.class);
        if (null != pay.get("id")) {
            payService.update(pay);
        } else {
            payService.save(pay);
        }
        redirect("/pay");
    }

    public void edit() {
        Page<User> userPage = userService.paginate(getParaToInt(0, 1), 10);
        setAttr("userPage",userPage);

        String id = getPara(0);
        Pay pay = payService.findById(id);
        setAttr("pay", pay);
        render("/app/hello/dormitory/pay_edit.ftl");
    }

    public void del() {
        String id = getPara(0);
        payService.deleteById(id);
        redirect("/pay");
    }
}

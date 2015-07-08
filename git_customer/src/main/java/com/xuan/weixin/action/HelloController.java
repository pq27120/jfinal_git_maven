package com.xuan.weixin.action;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.spring.Inject;
import com.jfinal.plugin.spring.IocInterceptor;
import com.xuan.service.DeferLogService;

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
@Before(IocInterceptor.class)
public class HelloController extends Controller {

    @Inject.BY_NAME
    private DeferLogService deferLogService;

    public void index() {
        setAttr("deferPage", deferLogService.paginate(getParaToInt(0, 1), 10));
        render("/app/hello/index.ftl");
    }

    public void logon() {
        render("/app/hello/logon.ftl");
    }
}

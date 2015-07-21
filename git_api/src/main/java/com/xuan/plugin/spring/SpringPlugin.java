package com.xuan.plugin.spring;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.IPlugin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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
 * @CreateDate 2015-07-20 14:13
 */

public class SpringPlugin implements IPlugin {
    private String[] configurations;
    private ApplicationContext ctx;

    /**
     * Use configuration under the path of WebRoot/WEB-INF.
     */
    public SpringPlugin() {
    }

    public SpringPlugin(String... configurations) {
        this.configurations = configurations;
    }

    public SpringPlugin(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    public boolean start() {
        if (ctx != null) {
            IocInterceptor.ctx = ctx;
        } else if (configurations != null) {
            IocInterceptor.ctx = new FileSystemXmlApplicationContext(configurations);
        } else {
            IocInterceptor.ctx = new FileSystemXmlApplicationContext(PathKit.getWebRootPath() + "/WEB-INF/applicationContext.xml");
        }
        return true;
    }

    public boolean stop() {
        return true;
    }
}

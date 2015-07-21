/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.xuan.common;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.xuan.model.DeferLog;
import com.xuan.model.Pay;
import com.xuan.model.User;
import com.xuan.plugin.spring.SpringPlugin;
import com.xuan.sdk.api.ApiConfigKit;

import java.util.HashMap;
import java.util.Map;

public class CommonConfig extends JFinalConfig {
	
	/**
	 * 如果生产环境配置文件存在，则优先加载该配置，否则加载开发环境配置文件
	 * @param pro 生产环境配置文件
	 * @param dev 开发环境配置文件
	 */
	public void loadProp(String pro, String dev) {
		try {
			PropKit.use(pro);
		}
		catch (Exception e) {
			PropKit.use(dev);
		}
	}
	
	public void configConstant(Constants me) {
		loadProp("a_little_config_pro.txt", "a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		
		// ApiConfigKit 设为开发模式可以在开发阶段输出请求交互的 xml 与 json 数据
		ApiConfigKit.setDevMode(me.getDevMode());
	}
	
	public void configRoute(Routes me) {
		me.add(new WeiXinRoute()); //微信路由
		me.add(new AdminRoute()); //管理平台路由
	}
	
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"),
                PropKit.get("password").trim(),"com.mysql.jdbc.Driver");
        //集成druid的插件
        druidPlugin.addFilter(new StatFilter());
        druidPlugin.setInitialSize(3).setMinIdle(3).setMaxActive(5);
        WallFilter wall = new WallFilter();
        wall.setDbType("mysql");
        druidPlugin.addFilter(wall);
        me.add(druidPlugin);

//		EhCachePlugin ecp = new EhCachePlugin();
//		me.add(ecp);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setDialect(new MysqlDialect());
        arp.setShowSql(true);

        arp.addMapping("defer_log", DeferLog.class);
        arp.addMapping("user", User.class);
        arp.addMapping("pay", Pay.class);

        me.add(arp);

        me.add(new SpringPlugin());
    }

    public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
        DruidStatViewHandler dvh =  new DruidStatViewHandler("/druid");
        me.add(dvh);
	}
	
    @Override
    public void afterJFinalStart() {
        Map<String, Class<?>> blogColumnMap = new HashMap<String, Class<?>>();
        blogColumnMap.put("id", Integer.class);
        blogColumnMap.put("title", String.class);
        blogColumnMap.put("content", String.class);

//        TableInitKit.init("blog", Blog.class, blogColumnMap);

//        System.out.println("Blog表字段模拟完成。");

        System.out.println("jfinal consumer for Dubbo启动完成。");
    }
}

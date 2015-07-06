/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.xuan.common;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.Dialect;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.xuan.model.DeferLog;
import com.xuan.sdk.api.ApiConfigKit;
import com.xuan.weixin.WeixinApiController;
import com.xuan.weixin.WeixinMsgController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.xuan.weixin.controller.CommonController;

public class WeixinConfig extends JFinalConfig {
	
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
		me.add("/", CommonController.class);
		me.add("/msg", WeixinMsgController.class);
		me.add("/api", WeixinApiController.class, "/api");
	}
	
	public void configPlugin(Plugins me) {
//		DruidPlugin druidPlugin = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim(),"com.mysql.jdbc.Driver");
//        //集成druid的插件
//        druidPlugin.addFilter(new StatFilter());
////        druidPlugin.setInitialSize(3).setMaxActive(5);
//        WallFilter wall = new WallFilter();
//        wall.setDbType("mysql");
//        druidPlugin.addFilter(wall);
//        me.add(druidPlugin);

        C3p0Plugin cp = new C3p0Plugin(PropKit.get("jdbcUrl"),
                PropKit.get("user"), PropKit.get("password").trim());
        me.add(cp);

//		EhCachePlugin ecp = new EhCachePlugin();
//		me.add(ecp);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
        arp.setDialect(new MysqlDialect());
        arp.setShowSql(true);
        arp.addMapping("defer_log", DeferLog.class);
        me.add(arp);

        QuartzPlugin quartzPlugin = new QuartzPlugin("job.properties", "quartz.properties");
        quartzPlugin.version(QuartzPlugin.VERSION_1);
        me.add(quartzPlugin);
    }

    public void configInterceptor(Interceptors me) {
		
	}
	
	public void configHandler(Handlers me) {
		
	}
	
	public static void main(String[] args) {
		JFinal.start("webapp", 80, "/", 5);
	}
}

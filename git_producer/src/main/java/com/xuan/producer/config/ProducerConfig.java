package com.xuan.producer.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.plugin.spring.SpringPlugin;
import com.xuan.model.DeferLog;

public class ProducerConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("producer_config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
	}

	@Override
	public void configHandler(Handlers me) {
		// 声明Druid监控页面URL
		me.add(new DruidStatViewHandler("/druid"));
	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置Druid数据库连接池插件
		DruidPlugin dp = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password").trim());
        dp.setInitialSize(3).setMinIdle(3).setMaxActive(5);

        StatFilter stat = new StatFilter();
		stat.setMergeSql(true);
		dp.addFilter(stat);

		WallFilter wall = new WallFilter();
		wall.setDbType(JdbcConstants.MYSQL);

		dp.addFilter(wall);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);

		arp.setShowSql(getPropertyToBoolean("devMode", false));
		arp.setDevMode(getPropertyToBoolean("devMode", false));
		arp.addMapping("defer_log", DeferLog.class); // 映射blog 表到 Blog模型
		arp.setDialect(new MysqlDialect());

		// 配置Spring插件
		SpringPlugin sp = new SpringPlugin();

		// 加入各插件到Config
		me.add(dp);
		me.add(arp);
		me.add(sp);
	}

	@Override
	public void configRoute(Routes me) {
	}

	@Override
	public void afterJFinalStart() {
		System.out.println("producer for Dubbo启动完成");
	}
}

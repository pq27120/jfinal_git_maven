package com.xuan.producer.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.xuan.model.DeferLog;
import com.xuan.model.Pay;
import com.xuan.model.User;
import com.xuan.plugin.spring.SpringPlugin;

public class ProducerConfig extends JFinalConfig {

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

	@Override
	public void configConstant(Constants me) {
        loadProp("producer_config_pro.txt", "producer_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode", false));
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
		arp.addMapping("defer_log", DeferLog.class);
		arp.addMapping("user", User.class);
		arp.addMapping("pay", Pay.class);
		arp.setDialect(new MysqlDialect());

        QuartzPlugin quartzPlugin = new QuartzPlugin("job.properties", "quartz.properties");
        quartzPlugin.version(QuartzPlugin.VERSION_1);
        me.add(quartzPlugin);

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

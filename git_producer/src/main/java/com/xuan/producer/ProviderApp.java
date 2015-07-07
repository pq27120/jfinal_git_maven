package com.xuan.producer;

import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.spring.SpringPlugin;
import com.xuan.model.DeferLog;

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
 * @CreateDate 2015-07-07 10:55
 */

public class ProviderApp {
    public static void main(String[] args) throws InterruptedException {
        // 读取配置文件
        Prop p = PropKit.use("producer_config.txt", "utf-8");

        // 配置Druid数据库连接池插件
        DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p
                .get("password").trim());

        WallFilter wall = new WallFilter();
        wall.setDbType(JdbcConstants.MYSQL);
        dp.addFilter(wall);

        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);

        arp.addMapping("defer_log", DeferLog.class); // 映射blog 表到 Blog模型
        arp.setDialect(new MysqlDialect());
        arp.setShowSql(p.getBoolean("devMode", false));
        arp.setDevMode(p.getBoolean("devMode", false));

        // 配置Spring插件
        SpringPlugin sp = new SpringPlugin(
                "git_producer/src/main/webapp/WEB-INF/applicationContext.xml");

        // 手动启动各插件
        dp.start();
        arp.start();
        sp.start();

        System.out.println("jfinal provider for Dubbo启动完成。");

        // 没有这一句，启动到这服务就退出了
        Thread.currentThread().join();
    }
}

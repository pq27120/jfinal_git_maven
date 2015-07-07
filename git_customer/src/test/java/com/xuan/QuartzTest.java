package com.xuan;

import com.jfinal.ext.plugin.quartz.QuartzPlugin;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

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
 * @CreateDate 2015-07-04 20:40
 */

public class QuartzTest {

    @Test
    public void test() throws InterruptedException {
        QuartzPlugin quartzPlugin = new QuartzPlugin("job.properties", "quartz.properties");
//        quartzPlugin.add("*/5 * * * * ?", new JobA());
        quartzPlugin.version(QuartzPlugin.VERSION_1);
        quartzPlugin.start();
        TimeUnit.SECONDS.sleep(20);
    }
}

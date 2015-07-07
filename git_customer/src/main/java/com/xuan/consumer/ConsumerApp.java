package com.xuan.consumer;

import com.jfinal.core.JFinal;

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
 * @CreateDate 2015-07-07 11:33
 */

public class ConsumerApp {

    public static void main(String[] args) {
        JFinal.start("E:\\project\\common\\jfinal_git_maven1\\git_customer\\target\\customer", 80, "/", 5);
    }
}

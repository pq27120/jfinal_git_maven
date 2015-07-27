package com.xuan.service;

import org.junit.Test;

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
 * @CreateDate 2015-07-27 16:57
 */

public class SSQServiceTest {

    @Test
    public void getLastTenList() {
        SSQService service = new SSQService();
        System.out.println(service.getLastTenList());
    }
}

package com.xuan.action;

import com.xuan.bean.IDCBean;
import com.xuan.service.IDCExtendService;
import com.xuan.utils.DateStyle;
import com.xuan.utils.DateUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

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
 * @CreateDate 2015-07-24 15:20
 */

public class IDCExtendControllerTest {

    static CloseableHttpClient httpClient;

    @Before
    public void setUp(){
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void login() {
        IDCExtendService service = new IDCExtendService();
        service.login(httpClient);
    }

    @Test
    public void fetchIDCServerInfo() throws ParseException {
        login();
        IDCExtendService service = new IDCExtendService();
        IDCBean idcBean = service.fetchIDCServerInfo(httpClient);
    }

    @Test
    public void extendIDCServer(){
        login();
        IDCExtendService service = new IDCExtendService();
        service.extendIDCServer(httpClient);
    }

}

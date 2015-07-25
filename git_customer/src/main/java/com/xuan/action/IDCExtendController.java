package com.xuan.action;

import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.xuan.service.IDCExtendService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.HashMap;
import java.util.Map;

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
 * @CreateDate 2015-07-24 15:16
 */

public class IDCExtendController extends Controller {
    //静态化是为了下次再交互的时候共用同一个httpClient
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    public void index() {
        Map resultMap = new HashMap();
        // 创建 HttpClient 实例
        IDCExtendService service = Enhancer.enhance(IDCExtendService.class);
        if (service.login(httpClient)) {
            resultMap.put("FLAG", true);
            resultMap.put("BEAN", service.fetchIDCServerInfo(httpClient));
        } else {
            resultMap.put("FLAG", false);
            resultMap.put("MESS", "登录失败，请稍候再试！");
        }
        renderJson(resultMap);
    }

    public void extend(){
        Map resultMap = new HashMap();
        IDCExtendService service = Enhancer.enhance(IDCExtendService.class);
        if (service.extendIDCServer(httpClient)) {
            resultMap.put("FLAG", true);
        }else {
            resultMap.put("FLAG", false);
        }
        renderJson(resultMap);
    }
}

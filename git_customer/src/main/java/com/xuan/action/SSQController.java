package com.xuan.action;

import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.xuan.service.SSQService;

import java.util.HashMap;
import java.util.List;
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
 * @CreateDate 2015-07-27 16:52
 */

public class SSQController extends Controller{

    public void index(){
        Map resultMap = new HashMap();
        SSQService service = Enhancer.enhance(SSQService.class);
        List list = service.getLastTenList();
        if(list != null){
            resultMap.put("FLAG", true);
            resultMap.put("LIST", list);
        } else {
            resultMap.put("FLAG", false);
            resultMap.put("MESS", "获取彩票信息失败，请稍候再试！");
        }
        renderJson(resultMap);
    }
}

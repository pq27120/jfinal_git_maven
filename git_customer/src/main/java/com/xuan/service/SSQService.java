package com.xuan.service;

import com.alibaba.druid.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.nutz.json.Json;
import org.nutz.lang.Lang;

import java.io.IOException;
import java.util.ArrayList;
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
 * @CreateDate 2015-07-27 16:53
 */

public class SSQService {
    /**
     * 获取最近10期双色球数据
     * @return
     */
    public List getLastTenList() {
        List list = new ArrayList();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "http://open.caipiaoapi.com/api/ssq/10/json";
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, "GBK");
            if (!StringUtils.isEmpty(html)) {
                Map map = Json.fromJson(HashMap.class, Lang.inr(html));
                list = (List)map.get("list");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        httpget.releaseConnection();
        return list;
    }
}

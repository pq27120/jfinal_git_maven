package com.xuan.service;

import com.alibaba.druid.util.StringUtils;
import com.xuan.bean.IDCBean;
import com.xuan.utils.DateStyle;
import com.xuan.utils.DateUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
 * @CreateDate 2015-07-24 15:25
 */

public class IDCExtendService {
    /**
     * 登录东方数据网站
     *
     * @param httpClient
     * @return
     */
    public boolean login(CloseableHttpClient httpClient) {
        // 创建一个 HTTP POST 请求的实例
        HttpPost httpPost = new HttpPost("http://www.idc789.com/user/userlogin.asp");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "pq27120"));
        params.add(new BasicNameValuePair("password", "df8867179"));
        CloseableHttpResponse response;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            // 发送请求并获取 HTTP 响应
            response = httpClient.execute(httpPost);
            if (response == null || response.getHeaders("Location") == null
                    || !"logininfo.asp".equals(response.getHeaders("Location")[0].getValue())) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        httpPost.releaseConnection();
        return true;
    }

    /**
     * 获取服务器信息并封装为Bean
     *
     * @param httpClient
     * @return
     */
    public IDCBean fetchIDCServerInfo(CloseableHttpClient httpClient) {
        IDCBean idcBean = new IDCBean();
        String memberpage = "http://www.idc789.com/user/vpsadm.asp";
        HttpGet httpget = new HttpGet(memberpage);
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, "GBK");
            if (!StringUtils.isEmpty(html)) {
                Document doc = Jsoup.parse(html);
                Elements elements = doc.select("html>body>div>table>tbody>tr>td>table");
                Elements tdElements = elements.get(1).select("tbody>tr").get(1).select("td");
                idcBean.setProductType(tdElements.get(0).text());
                idcBean.setServerName(tdElements.get(1).text());
                idcBean.setIp(tdElements.get(2).text());
                idcBean.setOpenTime(DateUtil.StringToString(tdElements.get(3).text(), DateStyle.YYYY_MM_DD_HH_MM_SS));
                idcBean.setValidTime(DateUtil.StringToString(tdElements.get(4).text(), DateStyle.YYYY_MM_DD_HH_MM_SS));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        httpget.releaseConnection();
        return idcBean;
    }

    public boolean extendIDCServer(CloseableHttpClient httpClient) {
        CloseableHttpResponse response;
        HttpPost httpPost = new HttpPost("http://www.idc789.com/vpsadm/selfvpsmodifyendtime.asp");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("year", "9001"));
        params.add(new BasicNameValuePair("moneynow", "0"));
        params.add(new BasicNameValuePair("id", "4519"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, "GBK");
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("html>body>a");
            if (elements != null && elements.get(0).attr("href").equals("e.asp?e=请在该产品到期前1日内进行积分免费续期！")) {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        httpPost.releaseConnection();
        return true;
    }
}

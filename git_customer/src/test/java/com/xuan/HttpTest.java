package com.xuan;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
 * @CreateDate 2015-07-04 21:44
 */

public class HttpTest {

    @Test
    public void grabPageHTML() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.idc789.com/user/userlogin.asp");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        // releaseConnection等同于reset，作用是重置request状态位，为下次使用做好准备。
        // 其实就是用一个HttpGet获取多个页面的情况下有效果；否则可以忽略此方法。
        httpget.releaseConnection();
        System.out.println(html);
    }

    @Test
    // Post方法，模拟表单提交参数登录到网站。
    public void login2Lashou() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        //第一步 用Post方法带若干参数尝试登录
        HttpPost httppost = new HttpPost("http://www.idc789.com/user/userlogin.asp");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", "pq27120"));
        params.add(new BasicNameValuePair("password", "df8867179"));
        httppost.setEntity(new UrlEncodedFormEntity(params));

        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
//        String postResult = EntityUtils.toString(entity, "GBK");
        // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
//        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
//        for(Cookie cookie: cookies)
//            System.out.println(cookie);
        httppost.releaseConnection();


        // 第二步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
        String memberpage = "http://www.idc789.com/user/logininfo_t.asp";
        HttpGet httpget = new HttpGet(memberpage);
        response = httpclient.execute(httpget); // 必须是同一个HttpClient！
        entity = response.getEntity();
        String html = EntityUtils.toString(entity, "GBK");
        httpget.releaseConnection();

        if(html.contains("欢迎您： <strong><font face=\"Courier New, Courier, mono\">pq27120</font></strong>")){
            //第三步 进入延期界面
            httppost = new HttpPost("http://www.idc789.com/vpsadm/selfvpsmodifyendtime.asp");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("year", "9001"));
            params.add(new BasicNameValuePair("moneynow", "0"));
            params.add(new BasicNameValuePair("id", "4519"));
            httppost.setEntity(new UrlEncodedFormEntity(params));
            response = httpclient.execute(httppost);
            entity = response.getEntity();
            httppost.releaseConnection();
            html = EntityUtils.toString(entity, "GBK");

            if(html.contains("请在该产品到期前1日内进行积分免费续期")){
                System.out.println("尚未续期");
            }
        }
    }
}

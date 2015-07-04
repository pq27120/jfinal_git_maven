package com.xuan.job;

import com.xuan.model.DeferLog;
import com.xuan.util.UrlUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
 * @CreateDate 2015-07-03 16:39
 */

public class DeferDFServerJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
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

            if (html.contains("欢迎您： <strong><font face=\"Courier New, Courier, mono\">pq27120</font></strong>")) {
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

                new DeferLog().set("time", new Date()).set("remark", UrlUtil.encoder(html)).save();
                if (html.contains("请在该产品到期前1日内进行积分免费续期")) {
                    System.out.println("续期完成");
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

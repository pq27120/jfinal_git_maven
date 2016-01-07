package com.xuan.job;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/01/07.
 */
public class BaoBookServerJobTest {

	private CloseableHttpClient httpclient = HttpClients.createDefault();

	@Test
	public void testBaoLogin() {
		//第一步 用Post方法带若干参数尝试登录
		HttpPost httppost = new HttpPost("http://www.baoshu8.com/login.php?verify=10ee5ca9");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pwuser", "pq27120"));
		params.add(new BasicNameValuePair("pwpwd", "pq123456"));
		params.add(new BasicNameValuePair("question", "0"));
		params.add(new BasicNameValuePair("jumpurl", "http://www.baoshu8.com/index.php"));
		params.add(new BasicNameValuePair("lgt", "0"));
		params.add(new BasicNameValuePair("step", "2"));
		CloseableHttpResponse response = null;
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		try {
			String postResult = EntityUtils.toString(entity, "GBK");
			System.out.println(postResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
		httppost.releaseConnection();
	}

	@Test
	public void testBaoSign() {
		testBaoLogin();
		HttpPost httppost = new HttpPost("http://www.baoshu8.com/hack.php?H_name=xqqiandao");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("action", "qiandao"));
		params.add(new BasicNameValuePair("qdxq", "7"));
		CloseableHttpResponse response = null;
		try {
			httppost.setEntity(new UrlEncodedFormEntity(params));
			response = httpclient.execute(httppost);
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpEntity entity = response.getEntity();
		try {
			String postResult = EntityUtils.toString(entity, "GBK");
			System.out.println(postResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
		httppost.releaseConnection();
	}
}

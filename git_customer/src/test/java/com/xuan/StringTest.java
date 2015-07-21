package com.xuan;

import org.junit.Test;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * @CreateDate 2015-07-05 00:12
 */

public class StringTest {

    @Test
    public void testUrlEncoder(){
        String html = "<head><title>对象已移动</title></head><body><h1>对象已移动</h1>可以在<a HREF=e.asp?e=请在该产品到期前1日内进行积分免费续期！>此处</a>.</body>";
        String regEx_html = "<[^>]"; // 定义HTML标签的正则表达式
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(html);
        String htmlStr = m_html.replaceAll(""); // 过滤html标签
        System.out.println(htmlStr);
    }
}

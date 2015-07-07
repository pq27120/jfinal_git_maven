package com.xuan.util;

import freemarker.template.utility.StringUtil;

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
 * @CreateDate 2015-07-05 00:23
 */

public class UrlUtil {

    public static String encoder(String html) {
        if (null != html && !"".equals(html)) {
            html = html.replaceAll("\n", "");
            String regEx_html = "<[^>]"; // 定义HTML标签的正则表达式
            Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher m_html = p_html.matcher(html);
            return m_html.replaceAll(""); // 过滤html标签
        }
        return "";
    }
}

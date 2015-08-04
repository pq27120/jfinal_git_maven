package com.xuan.filter;

import com.xuan.model.AdminUser;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 类简述
 *
 * @author pq27120@126.com
 * @version 1.0.0
 * @Company
 * @Copyright
 * @CreateDate 2015-08-04 09:13
 */
public class UserUtil {
    /**
     * 用户的Session标志
     */
    public static String USER = "user";

    /**
     * 已登录的用户
     */
    public static Map<String, AdminUser> loginUsers = new HashMap<String, AdminUser>();

    /**
     * 保存用户信息到Session
     *
     * @param adminUser
     */
    public static void saveUserToSession(HttpSession session, AdminUser adminUser) {
        session.setAttribute(USER, adminUser);
        loginUsers.put(adminUser.getStr("LOGIN_NAME"), adminUser);
    }

    /**
     * 获取当前登录的用户
     *
     * @param session
     * @return
     */
    public static AdminUser getCurrentUser(HttpSession session) {
        Object sessionUser = session.getAttribute(USER);
        if (sessionUser == null) {
            return null;
        }
        AdminUser adminUser = (AdminUser) sessionUser;
        return adminUser;
    }

    /**
     * 获取当前用户的ID
     *
     * @return 当前用户的ID
     */
    public static Long getCurrentUserId(HttpSession session) {
        return getCurrentUser(session).getLong("ID");
    }

    /**
     * 从session中移除用户
     */
    public static void removeUserFromSession(HttpSession session) {
        session.removeAttribute(USER);
    }
}

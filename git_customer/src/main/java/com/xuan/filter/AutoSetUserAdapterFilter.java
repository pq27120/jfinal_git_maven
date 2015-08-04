package com.xuan.filter;

import com.xuan.model.AdminUser;
import com.xuan.model.User;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 类简述
 *
 * @author pq27120@126.com
 * @version 1.0.0
 * @Company
 * @Copyright
 * @CreateDate 2015-08-04 09:09
 */

public class AutoSetUserAdapterFilter implements Filter {
    /**
     * Default constructor.
     */
    public AutoSetUserAdapterFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中，
     * 如果不存在使用用户查询接口查询出用户对象并设置在Session中
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // _const_cas_assertion_是CAS中存放登录用户名的session标志
        Object object = httpRequest.getSession().getAttribute("_const_cas_assertion_");

        if (object != null) {
            Assertion assertion = (Assertion) object;
            String loginName = assertion.getPrincipal().getName();
            AdminUser adminUser = UserUtil.getCurrentUser(httpRequest.getSession());

            // 第一次登录系统
            if (adminUser == null) {
                WebApplicationContext wct = WebApplicationContextUtils.getWebApplicationContext(httpRequest
                        .getSession().getServletContext());
//                UserManager userManager = (UserManager) wct.getBean("userManager");
//                user = userManager.findUserByLoginName(loginName);
                // 保存用户信息到Session
//                UserUtil.saveUserToSession(httpRequest.getSession(), adminUser);
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }
}

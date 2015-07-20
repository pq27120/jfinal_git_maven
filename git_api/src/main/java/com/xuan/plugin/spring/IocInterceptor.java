package com.xuan.plugin.spring;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;

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
 * @CreateDate 2015-07-20 14:11
 */

public class IocInterceptor implements Interceptor {
    static ApplicationContext ctx;

    public void intercept(Invocation ai) {
        Field[] fields = ai.getMethod().getDeclaringClass().getDeclaredFields();
        for (Field field : fields) {
            Object bean = null;
            if (field.isAnnotationPresent(Inject.BY_NAME.class))
                bean = ctx.getBean(field.getName());
//            else if (field.isAnnotationPresent(Inject.BY_TYPE.class))
//                bean = ctx.getBean(field.getType());
            else
                continue ;

            try {
                if (bean != null) {
                    field.setAccessible(true);
                    field.set(ai.getTarget(), bean);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        ai.invoke();
    }
}

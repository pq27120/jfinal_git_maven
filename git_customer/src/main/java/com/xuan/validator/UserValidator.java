package com.xuan.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.xuan.model.User;

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
 * @CreateDate 2015-07-07 16:54
 */

public class UserValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        validateRequiredString("user.name", "nameMsg", "请输入姓名!");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepModel(User.class);
        c.render("/app/hello/dormitory/user_edit.ftl");
    }
}

package com.xuan.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.xuan.model.Pay;

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
 * @CreateDate 2015-07-08 16:01
 */

public class PayValidator extends Validator {

    @Override
    protected void validate(Controller c) {
        validateRequiredString("pay.time", "timeMsg", "请输入时间!");
        validateRequiredString("pay.amount", "amountMsg", "请输入金额!");
        validateRequiredString("pay.remark", "remarkMsg", "请输入备注!");
    }

    @Override
    protected void handleError(Controller c) {
        c.keepModel(Pay.class);
        c.render("/app/hello/dormitory/pay_edit.ftl");
    }
}

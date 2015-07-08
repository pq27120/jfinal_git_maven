package com.xuan.producer.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.xuan.model.Pay;
import com.xuan.service.PayService;

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
 * @CreateDate 2015-07-07 17:12
 */

public class PayServiceImpl implements PayService {

    private Pay payDao;

    public Page<Pay> paginate(int pageNumber, int pageSize) {
        return payDao.paginate(pageNumber, pageSize, "select p.*,u.name", "from pay p inner join user u where u.id = p.user_id order by id asc");
    }

    public void update(Pay pay) {
        if (pay == null) {
            return;
        }
        pay.update();
    }

    public Pay save(Pay pay) {
        if (pay == null) {
            return null;
        }
        pay.save();
        return pay;
    }

    public Pay findById(String id) {
        Pay pay = payDao.findById(id);
        return pay;
    }

    public void deleteById(String id) {
        payDao.deleteById(id);
    }

    public int findCountByUserId(String userId){
        List<Pay> list = payDao.find("select * from pay where user_id = ?",userId);
        if (null != list && !list.isEmpty()) {
            return list.size();
        }
        return 0;
    }

    /**
     * @param payDao
     */
    public void setPayDao(Pay payDao) {
        this.payDao = payDao;
    }
}

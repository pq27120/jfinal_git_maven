package com.xuan.service;

import com.jfinal.plugin.activerecord.Page;
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
 * @CreateDate 2015-07-07 16:30
 */

public interface PayService {

    Page<Pay> paginate(int pageNumber, int pageSize);

    void update(Pay pay);

    Pay save(Pay pay);

    Pay findById(String id);

    void deleteById(String id);

    int findCountByUserId(String userId);

}

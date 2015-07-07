package com.xuan.service;

import com.jfinal.plugin.activerecord.Page;
import com.xuan.model.DeferLog;

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
 * @CreateDate 2015-07-07 09:51
 */

public interface DeferLogService {

    Page<DeferLog> paginate(int pageNumber, int pageSize);

    void update(DeferLog deferLog);

    DeferLog save(DeferLog deferLog);

    DeferLog findById(String id);

    void deleteById(String id);
}

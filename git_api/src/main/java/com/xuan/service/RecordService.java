package com.xuan.service;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

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
 * @CreateDate 2015-07-07 15:08
 */

public interface RecordService {

    Page<Record> paginate(int pageNumber, int pageSize);

    void update(Record pay);

    Record save(Record pay);

    Record findById(String id);

    void deleteById(String id);
}

package com.xuan.producer.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.xuan.model.DeferLog;
import com.xuan.service.DeferLogService;

public class DeferLogServiceImpl implements DeferLogService {

	private DeferLog deferLogDao;
	
	public Page<DeferLog> paginate(int pageNumber, int pageSize) {
		return deferLogDao.paginate(pageNumber, pageSize, "select *", "from defer_log order by id asc");
	}

	public void update(DeferLog blog) {
		if (blog == null) {
			return;
		}
		blog.update();
	}

	public DeferLog save(DeferLog blog) {
		if (blog == null) {
			return null;
		}
		blog.save();
		return blog;
	}

	public DeferLog findById(String id) {
        DeferLog blog = deferLogDao.findById(id);
		return blog;
	}

	public void deleteById(String id) {
		deferLogDao.deleteById(id);
	}
	
	/**
	 * 通过Spring配置文件注入Blog的dao
	 * @param deferLogDao
	 */
	public void setDeferLogDao(DeferLog deferLogDao) {
		this.deferLogDao = deferLogDao;
	}
}

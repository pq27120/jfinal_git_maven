package com.xuan.producer.service.impl;

import com.jfinal.plugin.activerecord.Page;
import com.xuan.model.DeferLog;
import com.xuan.service.DeferLogService;

public class DeferLogServiceImpl implements DeferLogService {

	private DeferLog deferLogDao;
	
	public Page<DeferLog> paginate(int pageNumber, int pageSize) {
		return deferLogDao.paginate(pageNumber, pageSize, "select *", "from defer_log order by id asc");
	}

	public void update(DeferLog deferLog) {
		if (deferLog == null) {
			return;
		}
		deferLog.update();
	}

	public DeferLog save(DeferLog deferLog) {
		if (deferLog == null) {
			return null;
		}
		deferLog.save();
		return deferLog;
	}

	public DeferLog findById(String id) {
        DeferLog deferLog = deferLogDao.findById(id);
		return deferLog;
	}

	public void deleteById(String id) {
		deferLogDao.deleteById(id);
	}
	
	/**
	 * 通过Spring配置文件注入dao
	 * @param deferLogDao
	 */
	public void setDeferLogDao(DeferLog deferLogDao) {
		this.deferLogDao = deferLogDao;
	}
}

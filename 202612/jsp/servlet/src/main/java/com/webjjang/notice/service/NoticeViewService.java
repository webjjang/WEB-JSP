package com.webjjang.notice.service;

import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.notice.vo.NoticeVO;

public class NoticeViewService implements Service{

	private NoticeDAO dao = null;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (NoticeDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.view((Long) obj);
	}

}

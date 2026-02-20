package com.webjjang.notice.service;

import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;
import com.webjjang.notice.dao.NoticeDAO;
import com.webjjang.util.page.PageObject;

public class NoticeListService implements Service{

	private NoticeDAO dao = null;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (NoticeDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject pageObject = (PageObject) obj;
		// 전체 데이터의 개수를 세팅한다. DB 가져와서 -> dao 메서드 필요.
		// 페이지 정보를 계산한다. 누락되면 페이지 정보가 이상해 진다.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}

}

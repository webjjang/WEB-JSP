package com.webjjang.image.service;

import com.webjjang.image.dao.ImageDAO;
import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;

public class ImageViewService implements Service {

	private ImageDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (ImageDAO) dao;
	}

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		return dao.view((Long) obj);
	}

}

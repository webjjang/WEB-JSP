package com.webjjang.member.service;

import com.webjjang.main.service.Service;
import com.webjjang.member.dao.MemberDAO;

public class MemberListService implements Service {

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MemberDAO dao = new MemberDAO(); // 클래스 생성한다.
		return dao.list();
	}

}

package com.webjjang.member.service;

import com.webjjang.main.service.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.MemberVO;

public class MemberCheckPwService implements Service{

	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MemberDAO dao = new MemberDAO();
		return dao.checkPw((MemberVO) obj);
	}

}

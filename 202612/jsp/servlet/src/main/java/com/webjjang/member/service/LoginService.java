package com.webjjang.member.service;

import com.webjjang.main.service.Service;
import com.webjjang.member.dao.MemberDAO;
import com.webjjang.member.vo.LoginVO;

// Service 종류라고 하려면 반드시 Service를 상속 받아야만 한다.
public class LoginService implements Service{

	@Override
	public LoginVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		MemberDAO dao = new MemberDAO();
		return dao.login((LoginVO) obj);
	}

}

package com.webjjang.qna.service;

import com.webjjang.qna.dao.QnaDAO;
import com.webjjang.qna.vo.QnaVO;
import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;

public class QnaAnswerService implements Service {

	private QnaDAO dao = null;
	
	// Init에서 이미 생성된 dao를 전달해서 저장해 놓는다. - 서버가 시작될 때 : 코딩 필
	public void setDAO(DAO dao) {
		this.dao = (QnaDAO) dao;
	}
	
	// list - List<QnaVO>, view - QnaVO, insert, update, delete - Integer
	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// 1. 관련 번호가 같은 순서 번호가 넘어오고 있는 순서번호와 같거나 큰 데이터를 +1 증가 시킨다.
		dao.increaseOrd((QnaVO) obj);
		// 2. 답변을 등록시킨다.
		return dao.answer((QnaVO) obj);
	}

}

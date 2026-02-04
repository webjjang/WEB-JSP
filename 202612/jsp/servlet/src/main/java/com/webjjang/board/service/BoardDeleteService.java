package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.service.Service;

public class BoardDeleteService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		return dao.delete((BoardVO) obj);
	}

}

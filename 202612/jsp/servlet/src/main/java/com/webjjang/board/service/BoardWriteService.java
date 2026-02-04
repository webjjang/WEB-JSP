package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.service.Service;

public class BoardWriteService implements Service {

	// list - List<BoardVO>, view - BoardVO, insert, update, delete - Integer
	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		BoardDAO dao = new BoardDAO();
		return dao.write((BoardVO) obj);
	}

}

package com.webjjang.board.service;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.service.Service;

//Main - BoardController - (BoardViewService) - BoardDAO // BoardVO
public class BoardViewService implements Service{

	// Service를 실행하면 리턴타입은 무엇이 나올까요?
	// 일반게시판 리스트의 리턴 타입 : List<BoardVO>
	// 일반게시판 글보기의 리턴 타입 : BoardVO
	// 글등록, 글수정, 글삭제 : Integer
	// 모든 타입의 데이터를 리턴하려면 Object로 한다.
	public BoardVO service(Object obj) throws Exception{
		Long[] arrs = (Long[]) obj;
		Long no = arrs[0];
		Long inc = arrs[1]; // 1-조회수 1증가 함. 0- 조회수 1증가 안함.
		BoardDAO dao = new BoardDAO();
		if(inc == 1) {
			// 1. 조회수 1 증가
			// DB 처리 : list - List<VO>, view - VO, 외 update, delete, insert 문의 결과는 Integer
			Integer result = dao.increase(no);
			if(result != 1) throw new Exception("글보기 조회수 1증가 오류 : 글번호가 존재하지 않습니다.");
		}
		// 2. 번호에 맞는 데이터를 가져와서 리턴해 준다.
		return dao.view(no);
	}
}

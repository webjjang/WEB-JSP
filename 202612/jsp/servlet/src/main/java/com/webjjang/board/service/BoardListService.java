package com.webjjang.board.service;

import java.util.List;

import com.webjjang.board.dao.BoardDAO;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.dao.DAO;
import com.webjjang.main.service.Service;

//Main - BoardController - (BoardListService) - BoardDAO // BoardVO
public class BoardListService implements Service{

	private BoardDAO dao = null;
	
	// Init에서 이미 생성된 dao를 전달해서 저장해 놓는다. - 서버가 시작될 때 : 코딩 필
	public void setDAO(DAO dao) {
		this.dao = (BoardDAO) dao;
	}
	
	// Service를 실행하면 리턴타입은 무엇이 나올까요?
	// 일반게시판 리스트의 리턴 타입 : List<BoardVO>
	// 일반게시판 글보기의 리턴 타입 : BoardVO
	// 글등록, 글수정, 글삭제 : Integer
	// 모든 타입의 데이터를 리턴하려면 Object로 한다.
	public List<BoardVO> service(Object obj) throws Exception{
		return dao.list();
	}
}

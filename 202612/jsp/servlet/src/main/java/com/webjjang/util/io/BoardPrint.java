package com.webjjang.util.io;

import java.util.List;

import com.webjjang.board.vo.BoardVO;

public class BoardPrint {

	// 1. list - 리스트
	public static void print(List<BoardVO> list) {
		System.out.println("*****************");
		System.out.println(" 일반 게시판 리스트");
		System.out.println("*****************");
		System.out.println("+===============================================+");
		System.out.println(" 번호   /    제목     / 작성자  /  작성일   /  조회수");
		System.out.println("+===============================================+");
		// 가져온 데이터 출력하기
		for (BoardVO vo : list) {
			System.out.print(" " + vo.getNo());
			System.out.print(" / " + vo.getTitle());
			System.out.print(" / " + vo.getWriter());
			System.out.print(" / " + vo.getWriteDate());
			System.out.print(" / " + vo.getHit());
			System.out.println();
		} // for의 끝

		System.out.println("+===============================================+\n");

	} // print(list)의 끝

	// 2. vo - 글보기 : print() 오버로드 - 파라메터가 틀리면 다르게 인식한다.
	public static void print(BoardVO vo) {
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("-------일반 게시판 글보기 ------");
		System.out.println("----------------------------");
		System.out.println("+------------------------------------------------+");
		System.out.println("+ 번호 : " +vo.getNo());
		System.out.println("+ 제목 : " +vo.getTitle());
		System.out.println("+ 내용 : " +vo.getContent());
		System.out.println("+ 작성자 : " +vo.getWriter());
		System.out.println("+ 작성일 : " +vo.getWriteDate());
		System.out.println("+ 조회수 : " +vo.getHit());
		System.out.println("+------------------------------------------------+\n");
	}// print(list)의 끝
}

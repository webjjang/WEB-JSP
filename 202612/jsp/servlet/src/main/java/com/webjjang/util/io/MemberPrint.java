package com.webjjang.util.io;

import java.util.List;

import com.webjjang.member.vo.MemberVO;

public class MemberPrint {

	// 1. list - 리스트
	public static void print(List<MemberVO> list) {
		System.out.println("************");
		System.out.println(" 회원 리스트");
		System.out.println("************");
		System.out.println("+==============================================================================+");
		System.out.println(" 아이디   /  이름  / 성별 / 생년월일  /     연락처   /     이메일     / 상태 / 등급명(번호)");
		System.out.println("+==============================================================================+");
		// 가져온 데이터 출력하기
		for (MemberVO vo : list) {
			System.out.print(" " + vo.getId());
			System.out.print(" / " + vo.getName());
			System.out.print(" / " + vo.getGender());
			System.out.print(" / " + vo.getBirth());
			System.out.print(" / " + vo.getTel());
			System.out.print(" / " + vo.getEmail());
			System.out.print(" / " + vo.getStatus());
			System.out.print(" / " + vo.getGradeName());
			System.out.print("(" + vo.getGradeNo() + ")");
			System.out.println();
		} // for의 끝

		System.out.println("+==============================================================================+\n");

	} // print(list)의 끝

	// 2. vo - 회원(내) 정보 보기 : print() 오버로드 - 파라메터가 틀리면 다르게 인식한다.
	//  - my == 1 : 내 정보 보기, my == 0 : 회원 정보 보기
	public static void print(MemberVO vo, Integer my) {
		System.out.println();
		System.out.println("----------------------------");
		System.out.println("- " + ((my==1)?"내":"회원") + " 정보 보기 -");
		System.out.println("----------------------------");
		System.out.println("+------------------------------------------------+");
		System.out.println("+ 아이디 : " +vo.getId());
		System.out.println("+ 이름 : " +vo.getName());
		System.out.println("+ 성별 : " +vo.getGender());
		System.out.println("+ 생년월일 : " +vo.getBirth());
		System.out.println("+ 연락처 : " +vo.getTel());
		System.out.println("+ 이메일 : " +vo.getEmail());
		System.out.println("+ 등급명 : " +vo.getGradeName());
		System.out.println("+------------------------------------------------+\n");
	}// print(list)의 끝
}

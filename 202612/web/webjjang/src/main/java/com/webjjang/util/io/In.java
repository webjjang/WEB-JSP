package com.webjjang.util.io;

import java.util.Scanner;

public class In {

	// final : 상수 - 초기값을 넣으면 변경할 수 없다. 변수이름은 모두 대문자로 표기한다.
	private final static Scanner SCANNER = new Scanner(System.in);
	
	public static String getStr() {
		// nextLine() - 키보드로 엔터 입력한데까지 문자열을 받는다.
		return SCANNER.nextLine();
	}
	
	// 오버라이드 메서드 - 메시지를 전달받아서 표시하고 입력하는 메서드
	// 사용법 : In.getStr("~~") - API
	public static String getStr(String msg) {
		System.out.print(msg + " : ");
		return getStr(); // 파라메터가 없는 오버라이드 메서드 호출
	}
	
	// Long 타입의 데이터 받기
	public static Long getLong(String msg) {
		while(true) { // 정상적인 숫자가 들어오면 빠져 나간다.
			try { // 정상 처리
				return Long.parseLong(getStr(msg));
			} catch (Exception e) {
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(" 숫자만 입력하셔야 합니다. 다시 입력해 주세요.");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
			}
		}
	}
	
}

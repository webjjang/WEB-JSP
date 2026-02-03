package com.webjjang.main.controller;

import com.webjjang.board.controller.BoardController;
import com.webjjang.member.controller.MemberController;
import com.webjjang.member.vo.Login;
import com.webjjang.util.io.In;

// 클래스에는 접근제한자는 public 과 default를 사용한다. public 클래스는 파일명과 같아야만 한다.
// 팀 프로젝트 시 PL(프로젝트 리더) 개발
public class Main {

	// project 내에 main() 는 한개만 만든다. - 실행용 : 테스팅용으로는 필요한 만큼 만들 수 있다.(실행과 상관 없음)
	public static void main(String[] args) {
		
		// 환영 인사
		//  System 안에 out static 변수 있다. out안에 println()메서드가 있다.
		//  print 데이터 출력 - ln == Line
		System.out.println("---------------------------------");
		System.out.println(" 방갑습니다. 좋은 시간 되세요.~~~~~~");
		System.out.println("---------------------------------\n");
		
		// DB 드라이버 확인.
		try {
			Class.forName("com.webjjang.util.db.DB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DB 클래스가 존재하지 않아 시스템을 종료합니다. -----------");
			System.exit(1);
		}
		
		// 종료를 선택하기 전까지 무한반복
		// while(~) - ~ 조건이 true 인 동안 반복 처리한다.
		//   - 빠져 나가는 방법 :  break; - while문 빠져 나가기. return; - main()를 빠져나간다.
		//   -     System.exit(); JVM 빠져 나간다.
		while(true) { // for(;;)
			// 로그인 정보 출력
			Login.loginPrint();
			// 메인 처리
			// 1. 메인 메뉴 출력
			System.out.println("<<< 메인 메뉴 >>>");
			System.out.println("=========================================");
			System.out.println(" 1. 일반게시판  2. 공지사항  3. 회원관리");
			System.out.println(" 0. 프로그램 종료");
			System.out.println("=========================================");
			// 2. 메인 메뉴 입력
			String menu = In.getStr("메뉴 입력");
			System.out.println(); // 줄바꿈
			// 3. 메인 메뉴 처리
			switch (menu) {
			case "1":
				// System.out.println("일반게시판 처리");
				// System.out.println("BoardController 실행 전.---------");
				// BoardController를 실행하는 방법 - 클래스 생성하고 메서드 호출
				new BoardController().execute();
				// System.out.println("BoardController 실행 후.---------");
				break;
			case "2":
				System.out.println("공지사항 처리");
				
				break;
			case "3":
				// System.out.println("회원관리 처리");
				new MemberController().execute();
				break;
			case "0":
				System.out.println("+---------------------------------------------------+");
				System.out.println("  즐거운 시간되셨나요. 불편한 점이 있으시면 글등록 부탁드립니다.");
				System.out.println("  프로그램을 종료합니다.");
				System.out.println("+---------------------------------------------------+");
				
				// 완전 종료
				System.exit(0);

			default:
				invalidMenuPrint();
				break;
			} // switch ~ case 라벨: ~ default 의 끝
			System.out.println(); // 화면을 구분하는 빈줄 출력
		}// while(true) 의 끝
		
	} // main()의 끝
	
	// 반복 , 복잡 -> 메서드나 클래스를 만든다.
	// 잘못된 메뉴 선택 출력 메서드 -> Main.invalidMenuPrint()
	public static void invalidMenuPrint() {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println(" 잘못된 메뉴를 입력하셨습니다. 다시 입력해 주세요.");
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}

} // 클래스의 끝

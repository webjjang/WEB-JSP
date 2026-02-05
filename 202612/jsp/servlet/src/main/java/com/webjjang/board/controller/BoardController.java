package com.webjjang.board.controller;

import java.util.List;

import com.webjjang.board.service.BoardDeleteService;
import com.webjjang.board.service.BoardListService;
import com.webjjang.board.service.BoardUpdateService;
import com.webjjang.board.service.BoardViewService;
import com.webjjang.board.service.BoardWriteService;
import com.webjjang.board.vo.BoardVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.Init;
import com.webjjang.main.controller.Main;
import com.webjjang.main.service.Execute;
import com.webjjang.util.io.BoardPrint;
import com.webjjang.util.io.In;

import jakarta.servlet.http.HttpServletRequest;

// Controller
//  - 메뉴 출력 -> 메뉴 입력 -> 메뉴 처리 : 무한 반복
//  - 예외 처리 - 위의 정상 처리를 try로 묶는다. catch로 예외 처리를 한다.
//  - 모듈(일반게시판)을 처리한다.
//  - 데이터 수집 : DB에서 가져온다. 사용자에게 입력 받는다.
// Main - (BoardController) - BoardListService - BoardDAO // BoardVO
public class BoardController implements Controller{

	// PL이 메서드 이름을 정한다.
	// String 리턴 타입의 데이터는 forward 시킬 JSP의 정보이거나 redirect시킬 정보이다. 
	public String execute(HttpServletRequest request) {
		try { // 정상처리
			// 요청 uri에 따라서 처리된다.
			// list - /board/list.do
			// 1. 일반게시판 메뉴 입력
			String uri = request.getServletPath();
			
			// 2. 일반게시판 메뉴 처리
			// 사용 변수 선언
			BoardVO vo;
			Integer result;
			Long no;
			switch (uri) {
			case "/board/list.do":
				// System.out.println("일반게시판 리스트 처리");
				// 생성된 Service를 가져와서 실행 -> Execute가 실행하면 로그를 남긴다.
				// DB에서 데이터 수집을 해온다.
				// 사용자에게 제공한다.
				request.setAttribute("list", Execute.execute(Init.getService(uri), null));
				break;
			case "/board/view.do":
				// System.out.println("일반게시판 글보기 처리");
				no = In.getLong("번호");
				// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
				vo = (BoardVO) Execute.execute(new BoardViewService(), new Long[] {no, 1L});
				BoardPrint.print(vo);
				
				break;
			case "/board/writeForm.do":
				// System.out.println("일반게시판 글등록 처리");
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				//   - BoardVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new BoardVO();
				vo.setTitle(In.getStr("제목"));
				vo.setContent(In.getStr("내용"));
				vo.setWriter(In.getStr("작성자"));
				vo.setPw(In.getStr("비밀번호"));
				result = (Integer) Execute.execute(new BoardWriteService(), vo);
				break;
			case "/board/write.do":
				// System.out.println("일반게시판 글등록 처리");
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				//   - BoardVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new BoardVO();
				vo.setTitle(In.getStr("제목"));
				vo.setContent(In.getStr("내용"));
				vo.setWriter(In.getStr("작성자"));
				vo.setPw(In.getStr("비밀번호"));
				result = (Integer) Execute.execute(new BoardWriteService(), vo);
				break;
			case "4":
				// System.out.println("일반게시판 글수정 처리");
				
				// 1. 수정할 글번호를 입력 받는다.
				no = In.getLong("번호");
				
				// 2. 입력 받은 글번호의 데이터를 가져온다.(vo = 글보기)
				// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
				vo = (BoardVO) Execute.execute(new BoardViewService(), new Long[] {no, 0L});
				BoardPrint.print(vo);
				// 3. 가져온 데이터를 수정 입력한다.(수정 항목 선택 -> 입력 : 입력 끝남 선택 빠져나온다.)
				Integer item = update(vo);
				// 4. DB 수정하러 간다.(BoardUpdateService)
				//   수정 데이터 확인
//					System.out.println("------- 수정 데이터 확인 -----------------");
//					BoardPrint.print(vo);
				
				if(item == 9) {
					System.out.println("**** 수정이 취소 되었습니다. ****\n");
				} else {
					// 수정하러 간다.
					// 본인 확인요 비밀번호를 받는다.
					vo.setPw(In.getStr("본인 확인용 비밀번호"));
					result = (Integer) Execute.execute(new BoardUpdateService(), vo);
					if(result >= 1) System.out.println("******* 수정이 완료 되었습니다. ********");
					else System.out.println("****** 수정이 되지 않았습니다. 정보를 다시 확인해 주세요. *******");
				}
				
				break;
			case "5":
				System.out.println("일반게시판 글삭제 처리");
				// 글번호와 비밀번호 받기 - vo
				vo = new BoardVO();
				vo.setNo(In.getLong("삭제할 글 번호"));
				vo.setPw(In.getStr("본인 확인용 비밀번호"));
				result = (Integer) Execute.execute(new BoardDeleteService(), vo);
				if(result >= 1) System.out.println("******* 삭제가 완료 되었습니다. ********");
				else System.out.println("****** 삭제가 되지 않았습니다. 정보를 다시 확인해 주세요. *******");
				break;
			case "0": // 이전 메뉴
				// 자신을 호출한 프로그램으로 돌아간다. Main.main()
				return null;

			default:
				// 잘못된 메뉴 처리
				Main.invalidMenuPrint();
				break;
			} // switch ~ case 라벨: ~ default 의 끝
			System.out.println(); // 화면을 구분하는 빈줄 출력
		} // try 정상처리 의 끝
		catch (Exception e) { // 예외 처리
			// e.printStackTrace(); // 개발자를 위한 예외 상세 출력
			// 사용자를 위한 예외 코드 작성
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			System.out.println(" 일반 게시판 처리 중 오류가 발생되었습니다.");
			System.out.println(" 오류 : " + e.getMessage());
			System.out.println(" 다시 한번 실행해 주시고 오류가 계속되면 오류 게시판에 남겨 주세요.");
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		} // catch 의 끝
		return null;
		
		
	} // execute()의 끝
	
	// 데이터를 수정하는 메서드
	// 전달된 vo는 주소가 전달이 된다. 안에서 수정을 하면 밖에서도 수정이 같이 된다.
	public Integer update (BoardVO vo) {
		while(true) {
			// 수정 메뉴 출력
			System.out.println("<<<< 일반 게시판 글수정 >>>>");
			System.out.println("***************************");
			System.out.println(" 1. 제목  2. 내용  3. 작성자");
			System.out.println(" 0. 수정 완료  9. 수정 취소");
			System.out.println("***************************");
			// 수정 메뉴 입력
			String menu = In.getStr("수정 항목");
			// 수정 항목 입력
			switch (menu) {
			case "1":
				vo.setTitle(In.getStr("제목"));
				break;
			case "2":
				vo.setContent(In.getStr("내용"));
				break;
			case "3":
				vo.setWriter(In.getStr("작성자"));
				break;
			case "0":
				return 0;
			case "9":
				return 9;

			default:
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
				System.out.println(" 잘못된 항목를 선택하셨습니다. 다시 선택해 주세요.");
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
				break;
			} // switch 의 끝
			
			// 수정된 내용 출력
			BoardPrint.print(vo);
		} // while(true)의 끝
	} // update()의 끝
	
}// 클래스의 끝

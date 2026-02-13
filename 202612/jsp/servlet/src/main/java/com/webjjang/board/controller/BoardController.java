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
		// 잘못된 URI 처리 / 오류를 위한 URL 저장
		request.setAttribute("url", request.getRequestURL());
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
				// jsp의 위치 정보 "/WEB-INF/views/" + "board/list" + ".jsp"
				return "board/list";
			case "/board/view.do":
				// 글번호를 받는다. 1증가 데이터를 받는다. - 2개의 데이터는 반드시 넘어와야만 한다.
				no = Long.parseLong(request.getParameter("no"));
				long inc = Long.parseLong(request.getParameter("inc"));

				//DB에서 데이터 가져오기
				// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
				// EL 또는 JSTL을 사용하기 위해서 4개의 저장 - request에 담자.
				request.setAttribute("vo", Execute.execute(Init.getService(uri), new Long[] {no, inc}));
				
				// jsp의 위치 정보 "/WEB-INF/views/" + "board/view" + ".jsp"
				return "board/view";
				
			case "/board/writeForm.do":
				// jsp의 위치 정보 "/WEB-INF/views/" + "board/writeForm" + ".jsp"
				return "board/writeForm";
				
			case "/board/write.do":
				// System.out.println("일반게시판 글등록 처리");
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				System.out.println("write.do - 글등록 처리");
				// 넘어오는 데이터 수집
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				//   - BoardVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new BoardVO();
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				vo.setWriter(request.getParameter("writer"));
				vo.setPw(request.getParameter("pw"));
				Execute.execute(Init.getService(uri), vo);
				
				// 처리 결과 메시지 담기.
				request.getSession().setAttribute("msg", "글등록이 되었습니다.");
				
				return "redirect:list.do";
				
			case "/board/updateForm.do":
				// System.out.println("일반게시판 글수정 폼");
				
				// 글번호를 받는다. 1증가 데이터를 받는다. - 2개의 데이터는 반드시 넘어와야만 한다.
				no = Long.parseLong(request.getParameter("no"));
				inc = Long.parseLong(request.getParameter("inc"));

				//DB에서 데이터 가져오기
				// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
				// EL 또는 JSTL을 사용하기 위해서 4개의 저장 - request에 담자.
				request.setAttribute("vo", 
						Execute.execute(Init.getService("/board/view.do"), new Long[] {no, inc}));
				return "board/updateForm";
				
			case "/board/update.do":
				// System.out.println("일반게시판 글수정 처리");
				// 넘어오는 데이터 수집
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				//   - BoardVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new BoardVO();
				vo.setNo(Long.parseLong(request.getParameter("no")));
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				vo.setWriter(request.getParameter("writer"));
				vo.setPw(request.getParameter("pw"));
				// DB에 저장
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				
				// 처리 결과 메시지 담기.
				if(result == 1)
					request.getSession().setAttribute("msg", "수정이 되었습니다.");
				else
					request.getSession().setAttribute("msg", "수정에 실패하였습니다. 정보를 확인해주세요.");
				
				return "redirect:view.do?no=" + vo.getNo() + "&inc=0";
				
			case "/board/delete.do":
				System.out.println("일반게시판 글삭제 처리");
				// request : 클라이언트가 서버에 요청. - 클라이언트 정보를 서버에 전달해주는데 저장해서 전달되는 객체
				// 글번호와 비밀번호 받기 - vo
				vo = new BoardVO();
				vo.setNo(Long.parseLong(request.getParameter("no")));
				vo.setPw(request.getParameter("pw"));
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				// 처리 결과 메시지 담기.
				if(result == 1) {
					request.getSession().setAttribute("msg", "삭제가 되었습니다.");
					return "redirect:list.do";
				} else {
					request.getSession().setAttribute("msg", "삭제에 실패하였습니다. 정보를 확인해주세요.");
					return "redirect:view.do?no=" + vo.getNo() + "&inc=0";
				}
			default:
				// /WEB-INF/views/ + error/noPage + .jsp
				return "error/noPage";
			} // switch ~ case 의 끝
		} // try 정상처리 의 끝
		catch (Exception e) { // 예외 처리
			// 개발자를 위한 코드
			e.printStackTrace();
			// 잘못된 예외 처리 - 사용자에게 보여주기
			request.setAttribute("moduleName", "일반 게시판");
			request.setAttribute("e", e);
			// /WEB-INF/views/ + error/err_500 + .jsp
			return "error/err_500";
		} // catch 의 끝
		// return null;
		
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

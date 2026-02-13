package com.webjjang.qna.controller;

import com.webjjang.qna.vo.QnaVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.Init;
import com.webjjang.main.controller.Main;
import com.webjjang.main.service.Execute;

import jakarta.servlet.http.HttpServletRequest;

// Controller
//  - 예외 처리 - 위의 정상 처리를 try로 묶는다. catch로 예외 처리를 한다.
//  - 모듈(질문답변)을 처리한다.
//  - 데이터 수집 : DB에서 가져온다. 사용자에게 입력 받는다.
// Main - (QnaController) - QnaListService - QnaDAO // QnaVO
public class QnaController implements Controller{

	// PL이 메서드 이름을 정한다.
	// String 리턴 타입의 데이터는 forward 시킬 JSP의 정보이거나 redirect시킬 정보이다. 
	public String execute(HttpServletRequest request) {
		try { // 정상처리
			// 요청 uri에 따라서 처리된다.
			// list - /qna/list.do
			// 1. 질문답변 메뉴 입력
			String uri = request.getServletPath();
			
			// 2. 질문답변 메뉴 처리
			// 사용 변수 선언
			QnaVO vo;
			Integer result;
			Long no;
			switch (uri) {
			case "/qna/list.do":
				// System.out.println("질문답변 리스트 처리");
				// 생성된 Service를 가져와서 실행 -> Execute가 실행하면 로그를 남긴다.
				// DB에서 데이터 수집을 해온다.
				// 사용자에게 제공한다.
				request.setAttribute("list", Execute.execute(Init.getService(uri), null));
				// jsp의 위치 정보 "/WEB-INF/views/" + "qna/list" + ".jsp"
				return "qna/list";
			case "/qna/view.do":
				// 글번호를 받는다. 1증가 데이터를 받는다. - 2개의 데이터는 반드시 넘어와야만 한다.
				no = Long.parseLong(request.getParameter("no"));
				long inc = Long.parseLong(request.getParameter("inc"));

				//DB에서 데이터 가져오기
				// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
				// EL 또는 JSTL을 사용하기 위해서 4개의 저장 - request에 담자.
				request.setAttribute("vo", Execute.execute(Init.getService(uri), new Long[] {no, inc}));
				
				// jsp의 위치 정보 "/WEB-INF/views/" + "qna/view" + ".jsp"
				return "qna/view";
				
			case "/qna/questionForm.do":
				// jsp의 위치 정보 "/WEB-INF/views/" + "qna/writeForm" + ".jsp"
				return "qna/questionForm";
				
			case "/qna/question.do":
				// System.out.println("질문 등록 처리");
				// 사용자에게 데이터 받기 - 제목, 내용 - vo에 저장한다.
				System.out.println("question.do - 질문 등록 처리");
				// 넘어오는 데이터 수집
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				//   - QnaVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new QnaVO();
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				// 원래 아이디는 세션에서 가져와야 한다. Member table 에 있는 아이디를 하드코딩해서 입력
				vo.setId("test");
				Execute.execute(Init.getService(uri), vo);
				
				// 처리 결과 메시지 담기.
				request.getSession().setAttribute("msg", "질문이 등록되었습니다.");
				
				return "redirect:list.do";
				
			case "/qna/answer.do":
				// System.out.println("답변 등록 처리");
				// 사용자에게 데이터 받기 - 제목, 내용, 4개의 번호 - vo에 저장한다.
				System.out.println("answer.do - 답변 등록 처리");
				// 넘어오는 데이터 수집
				// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
				//   - QnaVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new QnaVO();
				vo.setRefNo(Long.parseLong(request.getParameter("refNo")));
				vo.setOrdNo(Long.parseLong(request.getParameter("ordNo")));
				vo.setLevNo(Long.parseLong(request.getParameter("levNo")));
				vo.setParentNo(Long.parseLong(request.getParameter("parentNo")));
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				// 원래 아이디는 세션에서 가져와야 한다. Member table 에 있는 아이디를 하드코딩해서 입력
				vo.setId("admin");
				Execute.execute(Init.getService(uri), vo);
				
				// 처리 결과 메시지 담기.
				request.getSession().setAttribute("msg", "질문이 등록되었습니다.");
				
				return "redirect:list.do";
				
			case "/qna/updateForm.do":
				// System.out.println("질문답변 글수정 폼");
				
				// 글번호를 받는다. 1증가 데이터를 받는다. - 2개의 데이터는 반드시 넘어와야만 한다.
				no = Long.parseLong(request.getParameter("no"));
				inc = Long.parseLong(request.getParameter("inc"));

				//DB에서 데이터 가져오기
				// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
				// EL 또는 JSTL을 사용하기 위해서 4개의 저장 - request에 담자.
				request.setAttribute("vo", 
						Execute.execute(Init.getService("/qna/view.do"), new Long[] {no, inc}));
				return "qna/updateForm";
				
			case "/qna/update.do":
				// System.out.println("질문답변 글수정 처리");
				// 넘어오는 데이터 수집
				// 사용자에게 데이터 받기 - 번호, 제목, 내용 - vo에 저장한다.
				//   - QnaVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
				vo = new QnaVO();
				vo.setNo(Long.parseLong(request.getParameter("no")));
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				// DB에 저장
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				
				// 처리 결과 메시지 담기.
				if(result == 1)
					request.getSession().setAttribute("msg", "수정이 되었습니다.");
				else
					request.getSession().setAttribute("msg", "수정에 실패하였습니다. 정보를 확인해주세요.");
				
				return "redirect:view.do?no=" + vo.getNo() + "&inc=0";
				
			case "/qna/delete.do":
				System.out.println("질문답변 글삭제 처리");
				// request : 클라이언트가 서버에 요청. - 클라이언트 정보를 서버에 전달해주는데 저장해서 전달되는 객체
				// 글번호만 받는다.
				no = Long.parseLong(request.getParameter("no"));
				result = (Integer) Execute.execute(Init.getService(uri), no);
				// 처리 결과 메시지 담기.
				if(result == 1) {
					request.getSession().setAttribute("msg", "삭제가 되었습니다.");
					return "redirect:list.do";
				} else {
					request.getSession().setAttribute("msg", "삭제에 실패하였습니다. 정보를 확인해주세요.");
					return "redirect:view.do?no=" + no + "&inc=0";
				}
			default:
				// 잘못된 URI 처리
				request.setAttribute("url", request.getRequestURL());
				// /WEB-INF/views/ + error/noPage + .jsp
				return "error/noPage";
			} // switch ~ case 라벨: ~ default 의 끝
		} // try 정상처리 의 끝
		catch (Exception e) { // 예외 처리
			e.printStackTrace(); // 개발자를 위한 예외 상세 출력
			// 잘못된 예외 처리 - 사용자에게 보여주기
			request.setAttribute("url", request.getRequestURL());
			request.setAttribute("moduleName", "질문 답변");
			request.setAttribute("e", e);
			// /WEB-INF/views/ + error/err_500 + .jsp
			return "error/err_500";
		} // catch 의 끝
		// return null;
		
		
	} // execute()의 끝
	
	
}// 클래스의 끝

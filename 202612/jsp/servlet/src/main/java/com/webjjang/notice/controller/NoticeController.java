package com.webjjang.notice.controller;

import java.net.URLEncoder;

import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.Init;
import com.webjjang.main.service.Execute;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.page.PageObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//Controller
//- 웹에서 메뉴에 해당되는 URI -> 메뉴 처리
//- 예외 처리 - 위의 정상 처리를 try로 묶는다. catch로 예외 처리를 한다.
//- 모듈(공지사항)을 처리한다.
//- 데이터 수집 : DB에서 가져온다. 사용자에게 입력 받는다.
// DispatcherServlet - (NoticeController) - NoticeListService - NoticeDAO // NoticeVO
public class NoticeController implements Controller {

	// PL이 메서드 이름을 정한다.
	// String 리턴 타입의 데이터는 forward 시킬 JSP의 정보이거나 redirect시킬 정보이다. 
	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 메시지 처리를 위한 세션 꺼내기 
		HttpSession session = request.getSession();
		
		// 잘못된 URI 처리 / 오류를 위한 URL 저장
		request.setAttribute("url", request.getRequestURL());
		try { // 정상처리
			// 요청 uri에 따라서 처리된다.
			// list - /notice/list.do
			// 1. 공지사항 메뉴 입력
			String uri = request.getServletPath();
			
			// 2. 공지사항 처리
			// 필요한 객체 선언
			NoticeVO vo;
			Integer result;
			Long no;
			
			switch (uri) {
			// 1. 공지 리스트
			case "/notice/list.do":
				// System.out.println("공지사항 리스트 처리");
				
				// 페이지 처리를 위한 객체
				// getInstance() - 객체를 생성해서 넘겨주세요.
				// - 1. PageObject를 생성한다. 2. request에서 page / 검색 정보를 받아서 세팅한다.
				PageObject pageObject = PageObject.getInstance(request);
				
				// period 정보를 받아서 세팅한다.
				String period = request.getParameter("period");
				if(period != null)
					pageObject.setPeriod(period);
				
				// 생성된 Service를 가져와서 실행 -> Execute가 실행하면 로그를 남긴다.
				// DB에서 데이터 수집을 해온다.
				// 사용자에게 제공한다.
				request.setAttribute("list", Execute.execute(Init.getService(uri), pageObject));
				// 처리된 후의 pageObject 데이터 확인
				System.out.println("NoticeController.execuete().pageObject - " + pageObject);
				request.setAttribute("pageObject", pageObject);
				// jsp의 위치 정보 "/WEB-INF/views/" + "board/list" + ".jsp"
				return "notice/list";
				
			// 2. 공지 보기
			case "/notice/view.do":
				// 넘어 오는 데이터 수집
				// 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB 데이터를 가져온다. request 에 저장한다.
				request.setAttribute("vo", Execute.execute(Init.getService(uri), no));
				
				return "notice/view";
				
			// 3-1. 등록 화면
			case "/notice/writeForm.do":
				return "notice/writeForm";
				
			// 3-2. 등록 처리
			case "/notice/write.do":
				// 데이터 수집 - NoticeVO : 제목, 내용, 시작일, 종료일
				vo = new NoticeVO();
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				vo.setStartDate(request.getParameter("startDate"));
				vo.setEndDate(request.getParameter("endDate"));
				
				// 처리 NoticeWriteServcie - NoticeDAO
				// 등록이 되거나 오류가 난다.
				Execute.execute(Init.getService(uri), vo);
				
				// 등록이 되면 메시지 처리를 한다.
				session.setAttribute("msg", "새로운 공지가 등록되었습니다.");
				
				return "redirect:list.do?perPageNum=" + request.getParameter("perPageNum") ;
				
			// 4-1. 공지 수정 폼
			case "/notice/updateForm.do":
				// 넘어 오는 데이터 수집
				// 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB 데이터를 가져온다. request 에 저장한다.
				request.setAttribute("vo", Execute.execute(Init.getService("/notice/view.do"), no));
				
				return "notice/updateForm";
					
				// 4-2. 공지 수정 처리
			case "/notice/update.do":
				// 넘어 오는 데이터 수집 - vo : 번호, 제목, 내용, 시작일, 종료일
				vo = new NoticeVO();
				// 데이터 수집 - 번호
				vo.setNo(Long.parseLong(request.getParameter("no")));
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				vo.setStartDate(request.getParameter("startDate"));
				vo.setEndDate(request.getParameter("endDate"));
				
				// DB 데이터를 주정한다. - update, insert, delete 쿼리를 실행하면 int 데이터가 나온다.
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				
				// 메시지 처리
				if(result == 1) session.setAttribute("msg", "공지가 수정되었습니다.");
				else session.setAttribute("msg", "공지 수정에 실패하셨습니다.");
				
				return "redirect:view.do?no=" + vo.getNo()
					+ "&page=" + request.getParameter("page")
					+ "&perPageNum=" + request.getParameter("perPageNum")
					+ "&key=" + request.getParameter("key")
					+ "&word=" + URLEncoder.encode(request.getParameter("word"),"utf-8")
					+ "&period=" + request.getParameter("period")
				;
				
				// 5. 공지 삭제
				case "/notice/delete.do":
					// 넘어 오는 데이터 수집
					// 데이터 수집 - 번호
					no = Long.parseLong(request.getParameter("no"));
					
					// DB 데이터를 제거한다.
					result = (Integer) Execute.execute(Init.getService(uri), no);
					
					// 메시지 처리
					if(result == 1) session.setAttribute("msg", "공지가 삭제되었습니다.");
					else session.setAttribute("msg", "이미 삭제된 공지입니다.");
					
					return "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
						
			default:
				// /WEB-INF/views/ + error/noPage + .jsp
				return "error/noPage";
			} // switch ~ case 의 끝

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); // 개발자를 위한 코드
		}
		return null;
	}

}

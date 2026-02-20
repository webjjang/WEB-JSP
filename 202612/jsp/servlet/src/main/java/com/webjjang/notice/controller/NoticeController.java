package com.webjjang.notice.controller;

import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.Init;
import com.webjjang.main.service.Execute;
import com.webjjang.notice.vo.NoticeVO;
import com.webjjang.util.page.PageObject;

import jakarta.servlet.http.HttpServletRequest;

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
			case "/notice/list.do":
				// System.out.println("공지사항 리스트 처리");
				// 페이지 처리를 위한 객체
				// getInstance() - 객체를 생성해서 넘겨주세요.
				// - 1. PageObject를 생성한다. 2. request에서 page / 검색 정보를 받아서 세팅한다.
				PageObject pageObject = PageObject.getInstance(request);
				
				// 생성된 Service를 가져와서 실행 -> Execute가 실행하면 로그를 남긴다.
				// DB에서 데이터 수집을 해온다.
				// 사용자에게 제공한다.
				request.setAttribute("list", Execute.execute(Init.getService(uri), pageObject));
				// 처리된 후의 pageObject 데이터 확인
				System.out.println("NoticeController.execuete().pageObject - " + pageObject);
				request.setAttribute("pageObject", pageObject);
				// jsp의 위치 정보 "/WEB-INF/views/" + "board/list" + ".jsp"
				return "notice/list";
				
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

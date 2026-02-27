package com.webjjang.image.controller;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

import com.webjjang.image.vo.ImageVO;
import com.webjjang.main.controller.Controller;
import com.webjjang.main.controller.Init;
import com.webjjang.main.service.Execute;
import com.webjjang.util.page.PageObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class ImageController implements Controller {

	// 파일이 저장되는 위치 - 전역 변수 사용
	private String path = "/upload/image";

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// String jsp = null;
		HttpSession session = request.getSession();
		
		// 코딩해라.. uri로 실행되는 switch case를 만든다.
		// 잘못된 URI 처리 / 오류를 위한 URL 저장
		request.setAttribute("url", request.getRequestURL());
		try { // 정상처리
			// 요청 uri에 따라서 처리된다.
			// list - /board/list.do
			// 1. 일반게시판 메뉴 입력
			String uri = request.getServletPath();
			
			// 2. 일반게시판 메뉴 처리
			// 사용 변수 선언
			ImageVO vo;
			Integer result;
			Long no;
			
			// 메뉴 처리 - switch
			switch (uri) {
			// 1. 이미지 리스트
			case "/image/list.do":
				// System.out.println("이미지 게시판 리스트 처리");
				// 페이지 처리를 위한 객체
				// getInstance() - 객체를 생성해서 넘겨주세요.
				// - 1. PageObject를 생성한다. 2. request에서 page / 검색 정보를 받아서 세팅한다.
				PageObject pageObject = PageObject.getInstance(request);
				// 한페이지에 보여줄 데이터의 갯수 조정
				String perPageNum = request.getParameter("perPageNum");
				if(perPageNum == null || perPageNum.equals(""))
					pageObject.setPerPageNum(8);
				
				// 생성된 Service를 가져와서 실행 -> Execute가 실행하면 로그를 남긴다.
				// DB에서 데이터 수집을 해온다.
				// 사용자에게 제공한다.
				request.setAttribute("list", Execute.execute(Init.getService(uri), pageObject));
				// 처리된 후의 pageObject 데이터 확인
				System.out.println("ImageController.execuete().pageObject - " + pageObject);
				request.setAttribute("pageObject", pageObject);
				// jsp의 위치 정보 "/WEB-INF/views/" + "image/list" + ".jsp"
				return "image/list";
				
			// 2. 보기
			case "/image/view.do":
				// 넘어오는 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB에서 데이터를 가져와서 request에 담는다.
				request.setAttribute("vo",Execute.execute(Init.getService(uri), no));
				
				// jsp의 위치 정보 "/WEB-INF/views/" + "image/view" + ".jsp"
				return "image/view";

			// 3-1. 등록 폼
			case "/image/writeForm.do":
				// jsp의 위치 정보 "/WEB-INF/views/" + "image/writeForm" + ".jsp"
				return "image/writeForm";
				
			// 3-2. 등록 처리
			case "/image/write.do":
				// 전달되는 데이터 받기 - 파일 아닌 텍스트 데이터 받기
				vo = new ImageVO();
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				// id는 login에서 가져온다. 하드 코딩
				vo.setId("test");
				System.out.println("ImageController.execute().vo:파일처리 전 - " + vo);
				
				// 전달된 파일을 서버 컴퓨터 접근할 수 있는 폴더에 저장한다.
				// 저장 위치의 절대 위치 찾기
				String savePath = request.getServletContext().getRealPath(path);
				System.out.println("ImageController.execute().savePath - " + savePath);
				
				// 폴더가 없는 경우 생성한다. 자동으로 생성
				File saveDir = new File(savePath); // 저장위치로 폴더 작업이 가능한 File 객체로 만든다.
		        if (!saveDir.exists()) { // 폴더가 존재하지 않으면
		        	saveDir.mkdirs(); // 중간에 폴더부터 다 만든다. : mkdirs()
		        }
				
		        // 전달되는 파일 꺼내오기
		        Part filePart = request.getPart("imageFile");
		        
		        // 파일명 꺼내기
		        String fileName = Paths.get(filePart.getSubmittedFileName())
                        .getFileName()
                        .toString();
		        System.out.println("ImageController.execute().fileName - " + fileName);
		        // 중복된 파일을 처리하기 위해서 파일명 앞에 붙여둔다.
		        String uuid = UUID.randomUUID().toString();
		        String savedFileName = uuid + "_" + fileName;
		        
		        // DB에 저장하는 이름
		        System.out.println("ImageController.execute().saveFileName - " + path + "/" + savedFileName);
		        
		        // 파일 서버에 저장(중복 처리가 안된 저장) - 파일이 중복되면 저장하지 않는다.
		        filePart.write(savePath + File.separator + savedFileName);
		        vo.setFileName(path + "/" + savedFileName);
		        
				System.out.println("ImageController.execute().vo:파일처리 후 - " + vo);
		        
				// DB에 저장하기
				Execute.execute(Init.getService(uri), vo);
				
				// 결과 메시지 처리
				session.setAttribute("msg", "새로운 이미지가 등록되었습니다.");
				
				// 파일이 큰 경우 업로드 되는데 시간이 필요하다. 그래서 이동되는 시간도 딜레이시켜준다.
				Thread.sleep(1000);
				
				// jsp의 위치 정보 "/WEB-INF/views/" + "image/writeForm" + ".jsp"
				// return null; // 오류 남.
				return "redirect:list.do?perPageNum=" + request.getParameter("perPageNum");
				
			// 4-1. 정보 수정 폼
			case "/image/updateForm.do":
				// 넘어오는 데이터 수집 - 번호
				no = Long.parseLong(request.getParameter("no"));
				
				// DB에서 데이터를 가져와서 request에 담는다.
				request.setAttribute("vo",Execute.execute(Init.getService("/image/view.do"), no));
				
				// jsp의 위치 정보 "/WEB-INF/views/" + "image/updateForm" + ".jsp"
				return "image/updateForm";
				
			// 4-2. 정보 수정 처리(텍스트)
			case "/image/update.do":
				// 넘어오는 데이터 수집 - 번호, 제목, 내용
				// session에서 id를 꺼내온다.
				vo = new ImageVO();
				vo.setNo(Long.parseLong(request.getParameter("no")));
				vo.setTitle(request.getParameter("title"));
				vo.setContent(request.getParameter("content"));
				// 로그인한 정보에서 아이디가져와 세팅하기
				vo.setId("test");
				
				// DB에 데이터 수정하기.
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				
				// result = 1 수정 완료. 0 수정 안됨.
				if(result ==1) session.setAttribute("msg", "정보 수정이 되었습니다.");
				else session.setAttribute("msg", "정보를 확인하고 다시 시도해주세요.");
				
				// jsp의 위치 정보 "/WEB-INF/views/" + "image/updateForm" + ".jsp"
				return "redirect:view.do?no=" + request.getParameter("no")
						+ "&page=" + request.getParameter("page")
						+ "&perPageNum=" + request.getParameter("perPageNum")
						+ "&key=" + request.getParameter("key")
						+ "&word=" + request.getParameter("word");
				
			// 4-3. 이미지 변경 처리(파일)
			case "/image/changeImage.do":
				// 넘어오는 데이터 수집 - 번호, 바꿔야할 파일, 삭제할 파일
				vo = new ImageVO();
				vo.setNo(Long.parseLong(request.getParameter("no")));
				// id는 login에서 가져온다. 하드 코딩
				vo.setId("test");
				
				// 파일 업로드 -> 이미지 등록 처리를 보세요.
				// 전달된 파일을 서버 컴퓨터 접근할 수 있는 폴더에 저장한다.
				// 저장 위치의 절대 위치 찾기
				savePath = request.getServletContext().getRealPath(path);
				System.out.println("ImageController.execute().savePath - " + savePath);
				
				// 폴더가 없는 경우 생성한다. 자동으로 생성
				saveDir = new File(savePath); // 저장위치로 폴더 작업이 가능한 File 객체로 만든다.
		        if (!saveDir.exists()) { // 폴더가 존재하지 않으면
		        	saveDir.mkdirs(); // 중간에 폴더부터 다 만든다. : mkdirs()
		        }
				
		        // 전달되는 파일 꺼내오기
		        filePart = request.getPart("imageFile");
		        
		        // 파일명 꺼내기
		        fileName = Paths.get(filePart.getSubmittedFileName())
                        .getFileName()
                        .toString();
		        System.out.println("ImageController.execute().fileName - " + fileName);
		        // 중복된 파일을 처리하기 위해서 파일명 앞에 UUID 붙여둔다.
		        uuid = UUID.randomUUID().toString();
		        savedFileName = uuid + "_" + fileName;
		        
		        // DB에 저장하는 이름
		        System.out.println("ImageController.execute().saveFileName - " + path + "/" + savedFileName);
		        
		        // 파일 서버에 저장(중복 처리가 안된 저장) - 파일이 중복되면 저장하지 않는다.
		        filePart.write(savePath + File.separator + savedFileName);
		        vo.setFileName(path + "/" + savedFileName);
		        
				System.out.println("ImageController.execute().vo:파일처리 후 - " + vo);
		        
				// DB에 저장하기
				result = (Integer) Execute.execute(Init.getService(uri), vo);
				
				// 수정 후 처리
				if(result == 1) {
					// 결과 메시지 처리
					session.setAttribute("msg", "이미지가 변경되었습니다.");
					// 이전 파일 지우기
					String delFileName = request.getParameter("delFileName");
					new File(request.getServletContext().getRealPath(delFileName)).delete(); // 삭제 처리
				} else {
					session.setAttribute("msg", "이미지 변경에 실패하셔습니다.");
				}
				
				// 파일이 큰 경우 업로드 되는데 시간이 필요하다. 그래서 이동되는 시간도 딜레이시켜준다.
				Thread.sleep(1000);
				
				
				return "redirect:view.do?no=" + request.getParameter("no")
				+ "&page=" + request.getParameter("page")
				+ "&perPageNum=" + request.getParameter("perPageNum")
				+ "&key=" + request.getParameter("key")
				+ "&word" + request.getParameter("word");
					
			default:
				// /WEB-INF/views/ + error/noPage + .jsp
				return "error/noPage";
			} // switch ~ case 의 끝
			
		} // try 정상처리 의 끝
		catch (Exception e) { // 예외 처리
			// 개발자를 위한 코드
			e.printStackTrace();
			// 잘못된 예외 처리 - 사용자에게 보여주기
			request.setAttribute("moduleName", "이미지 게시판");
			request.setAttribute("e", e);
			// /WEB-INF/views/ + error/err_500 + .jsp
			return "error/err_500";
		} // catch 의 끝
		
		// return jsp;
	}

}

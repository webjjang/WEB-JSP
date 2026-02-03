<%@page import="com.webjjang.board.service.BoardWriteService"%>
<%@page import="com.webjjang.main.service.Execute"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바로 DB에 넘어오는 데이터 저장 : [BoardController:list] - BoardListService - BoardDAO.list(vo) -->
<%
System.out.println("write.jsp - 글등록 처리");
// 넘어오는 데이터 수집
// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
//   - BoardVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
BoardVO vo = new BoardVO();
vo.setTitle(request.getParameter("title"));
vo.setContent(request.getParameter("content"));
vo.setWriter(request.getParameter("writer"));
vo.setPw(request.getParameter("pw"));
int result = (Integer) Execute.execute(new BoardWriteService(), vo);

// DB에 저장
// 자동으로 리스트로 돌아간다.
response.sendRedirect("list.jsp");
%>
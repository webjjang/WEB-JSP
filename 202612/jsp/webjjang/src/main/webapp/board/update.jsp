<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@page import="com.webjjang.board.service.BoardUpdateService"%>
<%@page import="com.webjjang.main.service.Execute"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바로 DB에 넘어오는 데이터 저장 : [BoardController:update] - BoardUpdateService - BoardDAO.update(vo) -->
<%
System.out.println("update.jsp - 글수정 처리");
// 넘어오는 데이터 수집
// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장한다.
//   - BoardVO를 생성 -> 데이터 받기 setter()와 In.getStr()이용.
BoardVO vo = new BoardVO();
vo.setNo(Long.parseLong(request.getParameter("no")));
vo.setTitle(request.getParameter("title"));
vo.setContent(request.getParameter("content"));
vo.setWriter(request.getParameter("writer"));
vo.setPw(request.getParameter("pw"));
// DB에 저장
int result = (Integer) Execute.execute(new BoardUpdateService(), vo);

// 자동으로 글보기로 돌아간다.
if(result == 1)
	response.sendRedirect("view.jsp?no=" + vo.getNo() + "&inc=0");
else
	response.sendRedirect("view.jsp?no=" + vo.getNo() + "&inc=0&up=err");
%>
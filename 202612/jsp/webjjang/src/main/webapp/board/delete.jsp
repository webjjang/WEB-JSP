<%@page import="com.webjjang.board.service.BoardDeleteService"%>
<%@page import="com.webjjang.main.service.Execute"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 여기는 자바입니다.
System.out.println("delete.jsp----------------------------------");
// request : 클라이언트가 서버에 요청. - 클라이언트 정보를 서버에 전달해주는데 저장해서 전달되는 객체
// 글번호와 비밀번호 받기 - vo
BoardVO vo = new BoardVO();
vo.setNo(Long.parseLong(request.getParameter("no")));
vo.setPw(request.getParameter("pw"));
int result = (Integer) Execute.execute(new BoardDeleteService(), vo);


// 삭제가 되었으면 자동으로 일반게시판 리스트로 이동시킨다.
if(result == 1)
	response.sendRedirect("list.jsp");
else
	response.sendRedirect("view.jsp?no=" + vo.getNo() + "&inc=0&del=err");
%>
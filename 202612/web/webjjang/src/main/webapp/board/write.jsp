<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바로 DB에 넘어오는 데이터 저장 : [BoardController:list] - BoardListService - BoardDAO.list(vo) -->
<%
System.out.println("write.jsp - 글등록 처리");
// 넘어오는 데이터 수집
// DB에 저장
// 자동으로 리스트로 돌아간다.
response.sendRedirect("list.jsp");
%>
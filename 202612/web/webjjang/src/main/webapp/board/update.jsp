<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바로 DB에 넘어오는 데이터 저장 : [BoardController:update] - BoardUpdateService - BoardDAO.update(vo) -->
<%
System.out.println("update.jsp - 글수정 처리");
// 넘어오는 데이터 수집
// DB에 저장
// 자동으로 글보기로 돌아간다.
response.sendRedirect("view.jsp?no=" + request.getParameter("no"));
%>
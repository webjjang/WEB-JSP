<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 여기는 자바입니다.
System.out.println("delete.jsp----------------------------------");
// request : 클라이언트가 서버에 요청. - 클라이언트 정보를 서버에 전달해주는데 저장해서 전달되는 객체
// getParameter("no") : form tag의 input, textarea, select 의 name 속성의 이름으로 전달되는 데이터를 받는다.
System.out.println("no : " + request.getParameter("no"));
System.out.println("pw : " + request.getParameter("pw"));
// 자동으로 일반게시판 리스트로 이동시킨다.
response.sendRedirect("list.jsp");
%>
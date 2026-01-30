<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- DB 저장하고 리스트로 자동으로 이동시킨다. : 자바 프로그램으로 작성 -->
<%
 // 이 영역은 자바 입니다.
 System.out.println("write.jsp - 처리");
 // DB와 연결해서 받은 데이터를 저장하는 처리를 한다. - 미구현
 // 리스트로 자동으로 이동시킨다.
 response.sendRedirect("list.jsp");
%>
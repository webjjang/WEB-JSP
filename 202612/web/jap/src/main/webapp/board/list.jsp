<%-- jsp 주석입니다. : page 디렉티브 태그 --%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
 // 스트립틀릿 - 자바 소스 구역
 // 자바 변수 선언하고 데이터 세팅
 BoardVO vo = new BoardVO();
 vo.setNo(10);
 vo.setTitle("자바란 무엇일까요?");
 vo.setWriter("홍길동");
 
 // vo 객체를 request에 담기
 request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 리스트</title>
</head>
<body>
<h2>request 의 정보 출력해 보기</h2>
request.getContextPath() : <%= request.getContextPath() %><br>
request.getLocalAddr() : <%= request.getLocalAddr() %><br>
request.getLocalName() : <%= request.getLocalName() %><br>
request.getLocalPort() : <%= request.getLocalPort() %><br>
request.getMethod() : <%= request.getMethod() %><br>
- 넘어오는 데이터 no를 받아보자. 넘길때 url?no=10&amp;mod=w<br>
request.getParameter("no") : <%= request.getParameter("no") %><br>
request.getPathInfo() : <%= request.getPathInfo() %><br>
request.getQueryString() : <%= request.getQueryString() %><br>
request.getRemoteAddr() : <%= request.getRemoteAddr() %><br>
- 사용자에게 번호를 선택하도록 시켰다. 그런데 지금 URI를 사용해서 처리를 시킨다.<br>
request.getRequestURI() : <%= request.getRequestURI() %><br>
request.getServletContext().getRealPath(".") 
: <%= request.getServletContext().getRealPath("/image") %><br>

<hr><br>
<h2>일반게시판 리스트</h2>
- 자바 일반 변수 사용 - vo.getNo()<br>
글번호 : <%= vo.getNo() %><br>
제목 : <%= vo.getTitle() %><br>
작성자 : <%= vo.getWriter() %><br>
<hr>
- 자바 request의 속성 사용 - vo.no : EL 객체<br>
- pageScope, requestScope, sessionScope, applicationScope : 4가지 영역 지정할 수 있다.<br>
- 지정을 안하면 pageContext -> request - > session- application : 없으면 데이터를 표시하지 않는다.<br>
글번호 : ${requestScope.vo.no }<br>
제목 : ${vo.title }<br>
작성자 : ${requestScope.vo.writer }<br>

</body>
</html>
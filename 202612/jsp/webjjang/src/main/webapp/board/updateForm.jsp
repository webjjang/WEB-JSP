<%@page import="com.webjjang.board.service.BoardViewService"%>
<%@page import="com.webjjang.main.service.Execute"%>
<%@page import="com.webjjang.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 글번호를 받는다. 1증가 데이터를 받는다. - 2개의 데이터는 반드시 넘어와야만 한다.
long no = Long.parseLong(request.getParameter("no"));
long inc = Long.parseLong(request.getParameter("inc"));

//DB에서 데이터 가져오기
// new Long[] {no, 1L} - new Long[] {번호[0], 증가[1]} - 생성하고 바로 초기값을 세팅한다.
BoardVO vo = (BoardVO) Execute.execute(new BoardViewService(), new Long[] {no, inc});

// EL 또는 JSTL을 사용하기 위해서 4개의 저장 - request에 담자.
request.setAttribute("vo", vo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 글수정</title>
<!-- Bootstrap 라이브러리 등록 --------- -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
 
<!-- jQuery 라이브러리 등록 - 자바스크립트 함수 : jQuery() ==> $() -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 
  <script type="text/javascript">
  $(function(){
	  $(".cancelBtn").click(function(){
		 // alert("취소 버튼 클릭~~~!");
		 // BOM 객체 안에 history 객체가 있다. - 이동한 url을 저장해 놓는 객체
		 // history.go(1), history.back() == history.go(-1)
		 history.back(); // JavaScript
	  });
  });
  </script>
  
</head>
<body>
<div class="container">
	<h2>일반게시판 글수정</h2>
	<!-- URL & Header & body(data) 으로 넘기는 방식 : post -- 넘어가는 데이터가 보이지 않는다. -->
	<form action="update.jsp" method="post">
	  <div class="mb-3 mt-3">
	    <label for="no" class="form-label">번호</label>
	    <input type="text" class="form-control" id="no" 
	     name="no" value="${vo.no }" readonly="readonly" >
	  </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="title" class="form-label">제목</label>
	    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요."
	     name="title" value="${vo.title }">
	  </div>
	  
	    <div class="mb-3 mt-3">
	      <label for="content">내용</label>
	      <textarea class="form-control" rows="5" id="content" name="content"
	       placeholder="내용을 입력하세요.">${vo.content }</textarea>
	    </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="writer" class="form-label">작성자</label>
	    <input type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요."
	     name="writer" value="${vo.writer }">
	  </div>
	  <div class="mb-3">
	    <label for="pw" class="form-label">본인 확인용 비밀번호</label>
	    <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력하세요." name="pw">
	  </div>
	  <button type="submit" class="btn btn-primary">수정</button>
	  <button type="reset" class="btn btn-warning">새로입력</button>
	  <button type="button" class="cancelBtn btn btn-secondary">취소</button>
	</form>
</div>
</body>
</html>
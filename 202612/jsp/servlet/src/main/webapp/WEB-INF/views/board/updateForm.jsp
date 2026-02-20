<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form action="update.do" method="post">
		<!--  페이지 정보를 숨겨서 넘기기 -->
		<input type="hidden" name="page" value="${param.page }">
		<input type="hidden" name="perPageNum" value="${param.perPageNum }">
		<input type="hidden" name="key" value="${param.key }">
		<input type="hidden" name="word" value="${param.word }">
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
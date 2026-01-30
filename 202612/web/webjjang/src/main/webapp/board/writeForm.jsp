<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 글등록</title>
<!-- Bootstrap 라이브러리 등록 --------- -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
	<h2>일반게시판 글등록</h2>
	<!-- URL & Header & body(data) 으로 넘기는 방식 : post -- 넘어가는 데이터가 보이지 않는다. -->
	<form action="write.jsp" method="post">
	  <div class="mb-3 mt-3">
	    <label for="title" class="form-label">제목</label>
	    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title">
	  </div>
	  
	    <div class="mb-3 mt-3">
	      <label for="content">내용</label>
	      <textarea class="form-control" rows="5" id="content" name="content" placeholder="내용을 입력하세요."></textarea>
	    </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="writer" class="form-label">작성자</label>
	    <input type="text" class="form-control" id="writer" placeholder="작성자를 입력하세요." name="writer">
	  </div>
	  <div class="mb-3">
	    <label for="pw" class="form-label">비밀번호</label>
	    <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력하세요." name="pw">
	  </div>
	  <div class="mb-3">
	    <label for="pw2" class="form-label">비밀번호 확인</label>
	    <input type="password" class="form-control" id="pw2" placeholder="비밀번호 확인을 입력하세요.">
	  </div>
	  <button type="submit" class="btn btn-primary">등록</button>
	</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 등록</title>
<!-- Bootstrap / jQuery / jQuery UI / icon(Awe some 4) 라이브러리 등록 : defaulte_decorator.jsp --------- -->
 
  <script type="text/javascript">
  $(function(){
	  $(".cancelBtn").click(function(){
		 // alert("취소 버튼 클릭~~~!");
		 history.back();
	  });
  });
  </script>
  
</head>
<body>
<div class="container">
	<h2>이미지 등록</h2>
	<!-- URL & Header & body(data) 으로 넘기는 방식 :
		파일 업로드를 위한 속성 설정 : method="post" -- 넘어가는 데이터가 보이지 않는다.
				enctype="multipart/form-data" -->
	<form action="write.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
	  <div class="mb-3 mt-3">
	    <label for="title" class="form-label">제목</label>
	    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title"
	     title="제목은 필수 입력 항목입니다." required>
	  </div>
	  
	    <div class="mb-3 mt-3">
	      <label for="content">내용</label>
	      <textarea class="form-control" rows="5" id="content" name="content" required
	       placeholder="내용을 입력하세요."></textarea>
	    </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="imageFile" class="form-label">이미지 파일</label>
	    <input type="file" class="form-control" id="imageFile" name="imageFile" required>
	  </div>
	  
	  <button type="submit" class="btn btn-primary">등록</button>
	  <button type="reset" class="btn btn-warning">새로입력</button>
	  <button type="button" class="cancelBtn btn btn-secondary">취소</button>
	</form>
</div>
</body>
</html>
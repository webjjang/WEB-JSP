<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 수정</title>
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
	<h2>이미지 수정(텍스트)</h2>
	<!-- URL & Header & body(data) 으로 넘기는 방식 :
		파일 업로드를 위한 속성 설정 : method="post" -- 넘어가는 데이터가 보이지 않는다.
				enctype="multipart/form-data" -->
	<form action="update.do" method="post">
	<input type="hidden" name="page" value="${param.page }">
	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
	<input type="hidden" name="key" value="${param.key }">
	<input type="hidden" name="word" value="${param.word }">
	  <div class="mb-3 mt-3">
	    <label for="no" class="form-label">번호</label>
	    <input type="text" class="form-control" id="no" name="no"
	     readonly value="${vo.no }">
	  </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="title" class="form-label">제목</label>
	    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title"
	     title="제목은 필수 입력 항목입니다." required value="${vo.title }">
	  </div>
	  
	    <div class="mb-3 mt-3">
	      <label for="content">내용</label>
	      <textarea class="form-control" rows="5" id="content" name="content" required
	       placeholder="내용을 입력하세요.">${vo.content }</textarea>
	    </div>
	  
	  <button type="submit" class="btn btn-primary">수정</button>
	  <button type="reset" class="btn btn-warning">새로입력</button>
	  <button type="button" class="cancelBtn btn btn-secondary">취소</button>
	  
	  <div class="mb-3 mt-3">
	    <label for="imageFile" class="form-label">이미지 파일</label>
	    <img src="${vo.fileName }" class="img-fluid" style="max-width: 100%; height: auto">
	  </div>
	</form>
</div>
</body>
</html>
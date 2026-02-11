<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- Bootstrap 라이브러리 등록 --------- -->

<!-- jQuery 라이브러리 등록 - 자바스크립트 함수 : jQuery() ==> $() -->
 
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
	<h2>회원가입</h2>
	<!-- URL & Header & body(data) 으로 넘기는 방식 : post -- 넘어가는 데이터가 보이지 않는다. -->
	<form action="write.do" method="post">
	  <div class="mb-3 mt-3">
	    <label for="id" class="form-label">아이디</label>
	    <!-- required : 필수, autofocus : 페이지가 열리면 커서를 위치시킨다.
	      	아이디는 맨 앞자는 영문자로 하고 뒤에는 숫자나 영문자를 사용할 수 있다. 4자~20 -->
	    <input type="text" class="form-control" id="id" placeholder="아이디를 입력하세요." name="id"
	     title="아이디는 영문부터 영숫자만 4~20 사이로 입력하셔야 합니다." required autofocus maxlength="20"
	     pattern="[a-zA-Z][a-zA-Z0-9]{3,19}" >
	  </div>
	  
 	  <div class="mb-3">
	    <label for="pw" class="form-label">비밀번호</label>
	    <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력하세요."
	     title="비밀번호는 4~20자 사이로 입력하셔야 합니다." maxlength="20"
	     name="pw" required pattern=".{4,20}">
	  </div>
	  
	  <div class="mb-3">
	    <label for="pw2" class="form-label">비밀번호 확인</label>
	    <input type="password" class="form-control" id="pw2" placeholder="비밀번호 확인을 입력하세요."
	     title="비밀번호확인은 4~20자 사이로 입력하셔야 합니다." maxlength="20"
	     required pattern=".{4,20}">
	  </div>	
	  
	  <div class="mb-3 mt-3">
	    <label for="name" class="form-label">이름</label>
	    <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요."
	     title = "이름은 2~10자 한글로 입력하세요." pattern="[가-힣]{2,10}"
	     maxlength="10" name="name" required>
	  </div>
	  
	  <!-- 성별 항목에 대한 div 시작 -->
	  <div class="mb-3 mt-3">
		    <label class="form-label">성별</label>
		    <!-- 항목을 한줄로 하기위한 div -->
		  <div class="d-flex p-1">
		    <div class="form-check m-3">
		      <!-- radio 또는 check box 버튼인 input tag를 label로 감싸면 글자를 클릭해도 동작된다. -->
		      <label class="form-check-label" for="gender1">
				  <input type="radio" class="form-check-input" id="gender1" name="gender" 
				   value="남자" checked>남자
			  </label>
			</div>
			<div class="form-check m-3">
			  <label class="form-check-label" for="gender2">
				  <input type="radio" class="form-check-input" id="gender2" name="gender"
				   value="여자">여자
			  </label>
			</div>
		  </div>
		    <!-- 항목을 한줄로 하기위한 div 의 끝 -->
		</div>
	  <!-- 성별 항목에 대한 div 끝 -->
	  
	  <div class="mb-3 mt-3">
	    <label for="birth" class="form-label">생년월일</label>
	    <!-- 숫자나 날짜 같은 크기를 나타내는 데이터인 경우 min 과 max를 선언할 수 있다. -->
	    <input type="date" class="form-control" id="birth" placeholder="이름을 입력하세요."
	     name="birth" required min="1940-01-01" max="2015-12-31">
	  </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="tel" class="form-label">연락처</label>
	    <!-- 숫자나 날짜 같은 크기를 나타내는 데이터인 경우 min 과 max를 선언할 수 있다. -->
	    <input type="tel" class="form-control" id="tel" placeholder="연락처를 입력하세요."
	     title="02-xxx-xxxx 또는 010-xxxx-xxxx 형식으로 입력하세요."
	     name="tel" pattern="0\d{1,2}-\d{3,4}-\d{4}">
	  </div>
	  
	  <button type="submit" class="btn btn-primary">등록</button>
	  <button type="reset" class="btn btn-warning">새로입력</button>
	  <button type="button" class="cancelBtn btn btn-secondary">취소</button>
	</form>
</body>
</html>
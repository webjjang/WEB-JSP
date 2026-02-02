<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반게시판 글보기</title>
<!-- Bootstrap 5 라이브러리 등록 --------- -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- jQuery 라이브러리 등록 - 자바스크립트 함수 : jQuery() ==> $() -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<style type="text/css">
#deleteDiv{
	display: none;
}
</style>

<!-- 동작을 시키는 JS : 위치와 상관없이 코딩할 수 있다. -->
<script type="text/javascript">
 // jQuery :: 아래 HTML이 로딩이 끝나면 실행 줘 - $() 사이에 실행할 function을 넘긴다. body가 다 로딩이 되면 function이 실행됨.
 $(function(){
	 // alert("jQuery 영역이 실행됐다.~~~"); // 자바 스크립트의 팝업 열기
	 $("#deleteBtn").click(function(){
		// alert("삭제 버튼 클릭");
		// 입력된 비밀번호 삭제
		$("#pw").val(""); //jQuery val() - getter, val(data) - setter
		// disply 속성에서 보이게 show(), 안보이게 hide(), 보이면 안보이게 안보이면 보이게 toggle()
		$("#deleteDiv").toggle();
	 });
 });
</script>
</head>
<body>
<div class="container">
	<h2>일반게시판 글보기</h2>
	<table class="table">
		<tbody>
			<tr>
				<th>번호</th>
				<td class="no">${param.no }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>자바란 무엇인가?</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>자바는 컴퓨터 언어 입니다.</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>홍길동</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>2026-02-02</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>20</td>
			</tr>
		</tbody>
	</table>
	<a href="updateForm.jsp?no=${param.no }" class="btn btn-primary">수정</a>
	<a id="deleteBtn" class="btn btn-danger">삭제</a>
	<a href="list.jsp" class="btn btn-warning">리스트</a>
	
	<!-- 삭제를 위한 비밀번호 입력 div -->
	<div id="deleteDiv">
		<form action="delete.jsp" method="post">
			<!-- 글번호는 자동으로 넘어가게한다. 안보이게(사용자가 입력할 수 없다) 세팅해 준다. -->
			<input type="hidden" name="no" value="${param.no }">
		  <div class="mb-3">
		    <label for="pw" class="form-label">본인 확인 비밀번호 입력</label>
		    <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력하세요."
		     name="pw">
		  </div>
		  <!-- button tag의 기본 타입은 submit 입니다. 타입에서 button -동작없음. reset - 처음 데이터 -->
		  <button class="btn btn-danger btn-sm">전송</button>
		</form>
	</div>
</div>
</body>
</html>
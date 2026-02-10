<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문/답변 보기</title>
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
 ${(!empty msg)?"alert('" += msg += "');":""}
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
	 
	 $("#answerBtn").click(function(){
		 alert("답변 버튼 눌려짐.");
	 });
 });
</script>
</head>
<body>

<!-- 메인 메뉴 부분 ------------------------------------ -->
<%@ include file="../inc/mainMenu.jsp" %>
<!-- 메인 메뉴 부분 끝 ------------------------------------ -->

<div class="container">
	<h2>질문/답변 보기</h2>
	<table class="table">
		<tbody>
			<tr>
				<th>번호</th>
				<td class="no">${vo.no }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${vo.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${vo.content }</td>
			</tr>
			<tr>
				<th>작성자(id)</th>
				<td>${vo.name }(${vo.id })</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${vo.writeDate }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${vo.hit }</td>
			</tr>
		</tbody>
	</table>
	<a href="javascript:void(0)" id="answerBtn"
	 class="btn btn-primary">답변</a>
	<a href="updateForm.do?no=${param.no }&inc=0" class="btn btn-primary">수정</a>
	<a id="deleteBtn" class="btn btn-danger">삭제</a>
	<a href="list.do" class="btn btn-warning">리스트</a>
	
	<!-- 삭제를 위한 비밀번호 입력 div -->
	<div id="deleteDiv">
		<form action="delete.do" method="post">
			<!-- 글번호는 자동으로 넘어가게한다. 안보이게(사용자가 입력할 수 없다) 세팅해 준다. -->
			<input type="hidden" name="no" value="${param.no }">
		  <div class="mb-3">
		    <label for="pw" class="form-label">본인 확인 비밀번호 입력</label>
		    <input type="password" class="form-control" id="pw" placeholder="비밀번호를 입력하세요."
		     name="pw" required>
		  </div>
		  <!-- button tag의 기본 타입은 submit 입니다. 타입에서 button -동작없음. reset - 처음 데이터 -->
		  <button class="btn btn-danger btn-sm">전송</button>
		</form>
	</div>
	
	<!-- 답변을 위한 div -->
	<div id="answerDiv">
		<!-- URL & Header & body(data) 으로 넘기는 방식 : post -- 넘어가는 데이터가 보이지 않는다. -->
		<form action="answer.do" method="post">
			<input type="hidden" name="refNo" value="${vo.refNo }">
			<input type="hidden" name="ordNo" value="${vo.ordNo + 1 }">
			<input type="hidden" name="levNo" value="${vo.levNo + 1 }">
			<input type="hidden" name="parentNo" value="${vo.no }">
		  <div class="mb-3 mt-3">
		    <label for="title" class="form-label">제목</label>
		    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title"
		     title="제목은 필수 입력 항목입니다." required value="[답변]${vo.title }">
		  </div>
		  
		    <div class="mb-3 mt-3">
		      <label for="content">내용</label>
		      <textarea class="form-control" rows="5" id="content" name="content" required
		       placeholder="내용을 입력하세요."></textarea>
		    </div>
		  
		  <button type="submit" class="btn btn-primary">등록</button>
		  <button type="reset" class="btn btn-warning">새로입력</button>
		  <button type="button" class="closeBtn btn btn-secondary">취소</button>
		</form>
		
	</div>
</div>
</body>
</html>
<%
	// 글등록한 결과 메시지 지우기
	session.removeAttribute("msg");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 보기</title>
<!-- Bootstrap 5 /jQuery / awe some 4(icon) 라이브러리 등록 : default_decorator.jsp --------- -->

<!-- 동작을 시키는 JS : 위치와 상관없이 코딩할 수 있다. -->
<script type="text/javascript">
 // jQuery :: 아래 HTML이 로딩이 끝나면 실행 줘 - $() 사이에 실행할 function을 넘긴다. body가 다 로딩이 되면 function이 실행됨.
 $(function(){
	 // alert("jQuery 영역이 실행됐다.~~~"); // 자바 스크립트의 팝업 열기
	 $("#deleteBtn").click(function(){
		// alert("삭제 버튼 클릭");
		if(!confirm("정말 삭제하시겠습니까?")) return false;
	 });
 });
</script>
</head>
<body>
	<h2>공지 보기</h2>
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
				<th>공지 기간</th>
				<td>${vo.startDate } ~ ${vo.endDate }</td>
			</tr>
			<tr>
				<th>최근 수정일</th>
				<td>${vo.updateDate }</td>
			</tr>
			<tr>
				<th>최초 등록일</th>
				<td>${vo.writeDate }</td>
			</tr>
		</tbody>
	</table>
	<a href="updateForm.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum }&key=${param.key }&word=${param.word }&period=${param.period}"
	 class="btn btn-primary">수정</a>
	<a id="deleteBtn" href="delete.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum }&key=${param.key }&word=${param.word }"
	 class="btn btn-danger">삭제</a>
	<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum }&key=${param.key }&word=${param.word }&period=${param.period}"
	 class="btn btn-warning">리스트</a>
	
</body>
</html>

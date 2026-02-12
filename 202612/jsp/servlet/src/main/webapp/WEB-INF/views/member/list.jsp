<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 리스트</title>
<!-- Bootstrap 라이브러리 등록 --------- -->

<!-- jQuery 라이브러리 등록 - 자바스크립트 함수 : jQuery() ==> $() -->

<style type="text/css">
/* :hover - 마우스가 오라 갔을 때 CSS. 공백없이 :hover 작성  */
.dataRow:hover{
	cursor: pointer; /* 손가락 */
}
</style>

<!-- 동작을 시키는 JS : 위치와 상관없이 코딩할 수 있다. -->
<script type="text/javascript">
 // jQuery :: 아래 HTML이 로딩이 끝나면 실행 줘 - $() 사이에 실행할 function을 넘긴다. body가 다 로딩이 되면 function이 실행됨.
 $(function(){
	 // alert("jQuery 영역이 실행됐다.~~~"); // 자바 스크립트의 팝업 열기
	 $(".dataRow").click(function(){ // jquery입니다. 클래스가 dataRow인 것을 찾아서 클릭을 하면 전달된 함수를 실행한다.
		 // alert("데이터 클릭 - 글보기 이동 준비 중....");
	 	// 글번호 수집
	 	// text() - 글자만 가져온다. html() - tag도 가져온다. :: jQuery
	 	// js의 변수는 타입이 없다. - 변수 = 10 - 선언 없이 바로 사용가능. var로 변수 선언. let으로 변수 선언.-지역변수 구분 확실
	 	let no = $(this).find(".no").text(); // js = jQuery
	 	// alert("클릭한 글번호 : " + no); // js
	 	// 페이지 이동 시키기 - 브라우저 객체 중 location 객체가 있다. 보여지는 페이지들의 정보를 가지고 있는 객체
	 	// location 객체 - BOM 객체 중에 하나.
	 	// location.href = "view.jsp?no=" + no; // location = "url" == location.href = "url"
	 	// location = "url" : 자동으로 location.href에 들어간다.
	 	location = "view.do?no=" + no + "&inc=1";
	 }).mouseover(function(){
		 $(this).addClass("table-success");
	 }).mouseout(function(){
		 $(this).removeClass("table-success");
	 });
 });
</script>
</head>
<body>
	<h2>회원 리스트</h2>
	<table class="table">
		<thead class="table-dark">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>연락처</th>
				<th>이메일</th>
				<th>상태</th>
				<th>등급</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${empty list }">
			<tr>
				<td colspan="8">데이터가 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="vo" >
				<tr class="dataRow">
					<td class="id">${vo.id }</td>
					<td>${vo.name }</td>
					<td>${vo.gender }</td>
					<td>${vo.birth }</td>
					<td>${vo.tel }</td>
					<td>${vo.email }</td>
					<td>${vo.status }</td>
					<td>${vo.gradeName }</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	<a href="list.do" class="btn btn-success">새로고침</a>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테이블 페이지</title>
<style type="text/css">
#subjectTable{
	width: 400px; /* CSS의 주석 - 크기 표시 단위 : px(화면), pt(인쇄), cm, inch, em(배수) ... */
	/* border - 테두리, 1px - 두께, solid - 선 타입(실선), #444 - 색상
	   색상 - RGB : 0 ~ F(15) - 0에 가까울 수록 검정색, F에 가까울 수록 흰색
	   색상을 #444 - 빨강, 녹색, 파랑색을 16 단계로 나눠서 표시
	   색상을 #444444 - 빨강, 녹색, 파랑색을 256 단계로 나눠서 표시 */
	border: 1px solid #444;
	margin: auto; /* 가운데 정렬 */
}

td,th{
	border: 1px solid #444;
	padding: 5px;
}
th{
	background: #444;
	color: white;
}
</style>
</head>
<body>
<table id="subjectTable">
	<caption>개설 과목 Table</caption>
	<thead>
		<tr>
			<th>번호</th>
			<th>과목</th>
			<th>강사</th>
			<th>요일</th>
			<th>시간</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td rowspan="2">1</td>
			<td rowspan="2">JAVA</td>
			<td rowspan="2">이영환</td>
			<td>화요일</td>
			<td>오전 9시</td>
		</tr>
		<tr>
			<td>목요일</td>
			<td>오후 2시</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Oracle</td>
			<td>홍길동</td>
			<td>수요일</td>
			<td>오전 9시</td>
		</tr>
		<tr>
			<td rowspan="2">3</td>
			<td rowspan="2">웹표준</td>
			<td rowspan="2">빌게이츠</td>
			<td>월요일</td>
			<td>오후 2시</td>
		</tr>
		<tr>
			<td>금요일</td>
			<td>오후 2시</td>
		</tr>
	</tbody>
</table>
</body>
</html>
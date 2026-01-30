<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BS - 테이블 페이지</title>
<!-- BootStrap 라이브러리 등록 -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- 개발자 CSS -->
<style type="text/css">
th{ /* th - th 태그, .th - th 클래스, #th - th 아이디 */
	color: white;
	background: #444;
}
</style>
</head>
<body>
<div class="container">
	<table id="subjectTable" class="table bs-table">
		<caption>개설 과목 Table</caption>
		<thead>
			<tr>
				<th style="color: white;background: #444;">번호</th>
				<th style="color: white;background: #444;">과목</th>
				<th style="color: white;background: #444;">강사</th>
				<th style="color: white;background: #444;">요일</th>
				<th style="color: white;background: #444;">시간</th>
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
</div>
</body>
</html>
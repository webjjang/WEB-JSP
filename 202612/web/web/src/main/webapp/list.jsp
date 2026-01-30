<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list 페이지</title>
  <!-- Bootstarp5 API 라이브러리 등록 ------------------------------------>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

  <!-- Google Icon API 라이브러리 등록 ------------------------------------>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
<!-- 맨처음 나타나는 메뉴 처리하는 부분의 시작 ----------------------------------------- -->
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="javascript:void(0)">WebjjangShop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">공지사항</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">일반게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="javascript:void(0)">로그인</a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<!-- 맨처음 나타나는 메뉴 처리하는 부분의 끝 ------------------------------------------- -->

<h2>배우는 기술</h2>
<ul type="circle">
	<li>자바</li>
	<li>오라클</li>
	<li>Web Programming</li>
	<li>JSP &amp; Servlet</li>
	<li>MariaDB</li>
</ul>
<h2>배우는 기술의 순서</h2>
<ol type="I">
	<li>자바</li>
	<li>오라클</li>
	<li>Web Programming</li>
	<li>JSP &amp; Servlet</li>
	<li>MariaDB</li>
</ol>
<h2>핵심 기술</h2>
<ul style="list-style-type: none">
	<li><sub><i class="material-icons" style="color: red;">directions_run</i></sub> 자바</li>
	<li><sub><i class="material-icons">directions_run</i></sub> DB</li>
	<li><sub><i class="material-icons">directions_run</i></sub> React</li>
	<li><sub><i class="material-icons">directions_run</i></sub> API</li>
</ul>
</body>
</html>
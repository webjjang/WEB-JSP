<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top">
  <div class="container-fluid">
    <a class="navbar-brand" href="/">Webjjang Co.</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mynavbar">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link" href="/notice/list.do">공지사항</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/shop/list.do">쇼핑몰</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/list.do">일반게시판</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/qna/list.do">질문답변</a>
        </li>
      </ul>
      <form class="d-flex">
        <input class="form-control me-2" type="text" placeholder="Search">
        <button class="btn btn-primary" type="button">Search</button>
      </form>
    </div>
  </div>
</nav>

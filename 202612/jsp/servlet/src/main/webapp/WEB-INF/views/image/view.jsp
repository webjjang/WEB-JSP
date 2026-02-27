<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 보기</title>
<!-- Bootstrap 5 /jQuery / awe some 4(icon) 라이브러리 등록 : default_decorator.jsp --------- -->

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

	<h2>이미지 보기</h2>
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
				<th>
					이미지<br>
					<button class="btn btn-success" data-bs-toggle="modal" 
					 data-bs-target="#changeImageModal">이미지 변경</button><br>
					<a href="${vo.fileName }" class="btn btn-warning" download>다운로드</a>
				</th>
				<td>
					<img alt="이미지입니다." src="${vo.fileName }"
					 class="img-fluid" style="max-width: 100%; height: auto">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><pre>${vo.content }</pre></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${vo.name }(${vo.id })</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${vo.writeDate }</td>
			</tr>
		</tbody>
	</table>
	<a href="updateForm.do?no=${param.no }&page=${param.page }&perPageNum=${param.perPageNum }&key=${param.key }&word=${param.word }"
	 class="btn btn-primary">수정(텍스트 정보)</a>
	 <!-- 보이지 않는 폼을 한개 만든다. 넘기는 모든 데이터는 hidden 처리한다. js로 폼의 submit() 호출해서 넘긴다.
	  		넘길 데이터 : 번호, 삭제할 파일(현재 보여지고 있는 파일), perPageNum : post 방식으로 전달 -->
	<a id="deleteBtn" class="btn btn-danger">삭제</a>
	<a href="list.do?page=${param.page }&perPageNum=${param.perPageNum }&key=${param.key }&word=${param.word }"
	 class="btn btn-warning">리스트</a>
	

<!-- The Modal -->
<div class="modal" id="changeImageModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">변경할 이미지를 선택하세요.</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

		<form action="changeImage.do" method="post" enctype="multipart/form-data">
		<input name="no" value="${param.no }" type="hidden">
		<input name="page" value="${param.page }" type="hidden">
		<input name="perPageNum" value="${param.perPageNum }" type="hidden">
		<input name="key" value="${param.key }" type="hidden">
		<input name="word" value="${param.word }" type="hidden">
		<input name="delFileName" value="${vo.fileName }" type="hidden">
	      <!-- Modal body -->
	      <div class="modal-body">
	        <input type="file" class="form-control" name="imageFile" required >
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<button class="btn btn-primary" >변경</button>
	        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
	      </div>
		</form>

    </div>
  </div>
</div>


</body>
</html>

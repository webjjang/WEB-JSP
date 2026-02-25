<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 등록</title>
<!-- Bootstrap / jQuery / icon:awe some 라이브러리 등록 : decorators/default_decorator.jsp --------- -->

  <script type="text/javascript">
  $(function(){
	  $(".cancelBtn").click(function(){
		 // alert("취소 버튼 클릭~~~!");
		 history.back();
	  });
	  
	  // datepicker 적용
	  $(".datepicker").datepicker(
		  {
			   changeMonth: true,
			   changeYear: true,
			   dateFormat: "yy-mm-dd",
			   dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
			   monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		   }
	  );

	  // 공지 시작일 & 공지 종료일 처리
	  $("#startDate").datepicker("option",
	   {
		  // 최소 날짜 세팅
	      "minDate" : new Date(),
	      // 닫히는 이벤트가 발생되면(날짜를 선택하거나 다른 곳을 클릭하면 닫힌다.) 선택한 날짜를 selectedDate 파라메터로 받는다.
	      onClose : function(selectedDate) {
	    	  if($(this).val() != "") // 다른 곳을 클릭한 경우가 아닌 (날짜를 클릭한) 경우의 처리
	          	$("#endDate" ).datepicker( "option", "minDate", selectedDate ); // 종료일에 최소 날짜를 바꾼다.
      	 } // onClose 의 끝
	   }); // startDate의 끝
	  
	   $("#endDate").datepicker("option", 
		{
	      "minDate" : new Date(),
	      // 닫히는 이벤트가 발생되면(날짜를 선택하거나 다른 곳을 클릭하면 닫힌다.) 선택한 날짜를 selectedDate 파라메터로 받는다.
	      onClose : function( selectedDate ) {
	         if($(this).val() != "") // 다른 곳을 클릭한 경우가 아닌 (날짜를 클릭한) 경우의 처리 
	                $( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
		   } // onClose의 끝
	   }); // endDate의 끝
  });
  </script>
  
</head>
<body>
<div class="container">
	<h2>공지 등록</h2>
	<!-- URL & Header & body(data) 으로 넘기는 방식 : post -- 넘어가는 데이터가 보이지 않는다. -->
	<form action="write.do" method="post">
	<input type="hidden" name="perPageNum" value="${param.perPageNum }">
	  <div class="mb-3 mt-3">
	    <label for="title" class="form-label">제목</label>
	    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title"
	     title="제목은 필수 입력 항목입니다." required>
	  </div>
	  
	    <div class="mb-3 mt-3">
	      <label for="content">내용</label>
	      <textarea class="form-control" rows="5" id="content" name="content" required
	       placeholder="내용을 입력하세요."></textarea>
	    </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="startDate" class="form-label">공지 시작일</label>
	    <input type="text" class="form-control datepicker" id="startDate" placeholder="xxxx-xx-xx" 
	     name="startDate" autocomplete="off" readonly>
	  </div>

	  <div class="mb-3 mt-3">
	    <label for="endDate" class="form-label">공지 종료일</label>
	    <!-- readonly : 키보드로 입력 못함. 달력을 클릭해서 입력한다. -->
	    <input type="text" class="form-control datepicker" id="endDate" placeholder="xxxx-xx-xx" 
	     name="endDate" autocomplete="off" readonly>
	  </div>

	  <button type="submit" class="btn btn-primary">등록</button>
	  <button type="reset" class="btn btn-warning">새로입력</button>
	  <button type="button" class="cancelBtn btn btn-secondary">취소</button>
	</form>
</div>
</body>
</html>
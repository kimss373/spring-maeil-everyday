<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 추가</title>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- datepicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- custom -->
<style>
	@charset "UTF-8";

.add-button {
    position: absolute;
    top: 1px;
    right: 230px;
    background: #2C3E50;
    border: 0;
    color: white;
    height: 35px;
    border-radius: 3px;
    width: 157px;
}

<!-- -->

ul {
	list-style : none;
}

.all {
	background-color: rgba( 0, 0, 0, 0.5 );

}
.zTree-h1 {
	font-weight : 350;
	font-size :21px;
}
.zTree-h3 {
	font-size : 15px;
	font-weight : 450;
	margin : 0;
}
.group {
	width : 580px;
	box-shadow : 0 5px 15px rgba(0,0,0,.5);
	margin : 0 auto;
	border : 1px solid #ddd;
}

.group-head {
	height : 50px;
	border-bottom : 1px solid #34aadc;
	position : relative;
	background-color : white;
}
.close-button {
	position : absolute;
	top: 12px;
	right : 10px;
	border: 0;
    background: #34aadc;
    width: 70px;
    height: 27px;
    cursor: pointer;
    color: white;
	border-radius: 6px;
}

.ok-button {
	width: 70px;
	height: 27px;
	float: right;
    margin-right: 20px;
    border: 0;
    background: #34aadc;
    margin-top: 30px;
    font-size: 15px;
    cursor: pointer;
    color: white;
    border-radius: 6px;
}
.group-body {
	height :515px;
	position : relative;
	background-color : white;
	margin-left: 40px;
}

.top {
	height : 50px;
	margin-top: 20px;
}

.subject {
	border: 1px solid #34aadc;
	border-radius: 5px;
    width: 500px;
    height: 30px;
}

.domain {
	margin-top : 20px;
}

.date {
	text-align: center;
    background: white;
    border-radius: 5px;
    border: 1px solid #34aadc;
    width: 300px;
    height: 25px;
}

.memo {
	width : 500px;
	resize : none;
	border-radius : 5px;
	border : 1px solid #34aadc;
}
</style>
<script>
	
$(function() {
	$.datepicker.setDefaults({
		dateFormat : 'yy-mm-dd',
		showOtherMonths : true,
		showMonthAfterYear : true,
		changeYear : true,
		changeMonth : true,          
       yearSuffix: "년",
      	monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
      	monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
       dayNamesMin: ['일','월','화','수','목','금','토'],
       dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일']	
	});
	$("#startDt").datepicker();
	$("#endDt").datepicker();
	
	$("#startDt").datepicker('setDate', 'today');
	$("#endDt").datepicker('setDate', 'today');
});

$().ready(function() {
	
	console.log($("#startDt").val());
	
	$("#startDt").change(function(){
		
		console.log($(this).val());
		console.log(typeof $(this).val());
	});
	
});

	function click_ok() {
		
		if (document.getElementById("title").value == "") {
			alert("제목을 입력하세요");
			return false;
		}
		document.scheduleData.submit();
		return true;
		
	}
	
	
</script>
</head>
<body>
<div class = "group" id = "popupGroup">	
	<div class = "group-head">
		<h1 class = "zTree-h1"> 일정 추가 </h1>
	</div>
	<div class = "group-body">
	<form id = "scheduleData" name="scheduleData" action="/mySchedule/addSchedule" method="post">
		<div class = "top">
			<input class = "subject" id = "title" type = "text" name = "title" placeholder="일정을 입력해주세요">
		</div>
		<div class = "domain">
			<h3 class = "zTree-h3"> 시작 </h3>
		</div>
		<div class = "domain">
			<input class = "date" id = "startDt" type = "text" name = "startDt" id = "startDt">
		</div>
		<div class = "domain">
			<h3 class = "zTree-h3"> 종료 </h3>
		</div>
		<div class = "domain">
			<input class = "date" id = "endDt" type = "text" name = "endDt" id = "endDt">
		</div>
		<div class = "domain">
			<h3 class = "zTree-h3"> 메모 </h3>
		</div>
		<div class = "domain">
			<textarea class = "memo" id = "memo" name = "memo" rows = "5" cols = "20" placeholder="100글자까지 입력 가능합니다"></textarea> 
		</div>
		<button class = "ok-button" type= "button" onclick="click_ok();">확인</button>
	</form>
	</div>	
</div>
</body>
</html>
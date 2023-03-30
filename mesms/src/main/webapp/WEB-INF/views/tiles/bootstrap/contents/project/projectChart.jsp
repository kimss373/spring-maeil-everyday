<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ymd" value="<%=new java.util.Date()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" crossorigin="anonymous"></script>
<style>
	#div1 {
	
		 width: 600px;
         height: 400px;
	}
</style>
</head>
<body>
<main>
<header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
    <div class="container-fluid">
        <div class="page-header-content">
            <div class="row align-items-center justify-content-between pt-3">
                <div class="col-auto mb-3">
                    <h1 class="page-header-title">
                        <div class="page-header-icon"><i data-feather="user"></i></div>
                        ${projectDTO.projectNm } - 그래프
                    </h1>
                </div>
            </div>
        </div>
    </div>
</header>
<nav class="nav nav-borders">
	<a class="nav-link" href="${contextPath }/project/projectBacklog?projectCd=${projectDTO.projectCd }">백로그</a>
	<a class="nav-link" href="${contextPath }/project/projectMain?projectCd=${projectDTO.projectCd }">보드</a>
	<a class="nav-link active" href="${contextPath }/project/chart?projectCd=${projectDTO.projectCd }">그래프</a>
	<a class="nav-link ml-0" href="${contextPath }/project/projectSetting?projectCd=${projectDTO.projectCd }">프로젝트 설정</a>
	<a class="nav-link" href="${contextPath }/project/inviteMember?projectCd=${projectDTO.projectCd }">초대하기</a>
</nav>
<hr class="mt-0 mb-4" />
<div class="row">
	<c:forEach var="chartDTO" items="${chartList }">
	    <div class="col-lg-6">
	        <!-- Bar chart example-->
	        <div class="card mb-4" id="div1">
	            <div class="card-header">${chartDTO.sprintNm } 	<span style="color:green">${chartDTO.sprintDone }</span></div>
	            <div class="card-body">
	                <div class="chart-bar"><canvas id="${chartDTO.sprintNm }" width="550" height="250"></canvas></div>
	            </div>
	            <div class="card-footer small text-muted">Updated <fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd H:m" /></div>
	        </div>
	    </div>
	    <script>
			var ctx = document.getElementById('${chartDTO.sprintNm}').getContext('2d');
			var chart = new Chart(ctx, {
			    // type : 'bar' = 막대차트를 의미합니다.
			    type: 'bar', // 
			    data: {
			        labels: ['해야 할 일', '진행 중', '완료 된 일','전체'],
			        datasets: [{
			            label: '갯수',
			            backgroundColor: 'skyblue',
			            borderColor: 'skyblue',
			            data: [parseInt('${chartDTO.TODO}') ,parseInt('${chartDTO.inProgress}'), parseInt('${chartDTO.Done}'), parseInt('${chartDTO.total}')]
			        }]
			    },
			    options: {
			    	responsive: false,
			    	scales: {
						yAxes: [{
							ticks: {
								beginAtZero: true,
								stepSize : 1,
								fontSize : 14,
								
							}
						}]
			    	}
			    }
			});
	
		</script>
		
	</c:forEach>
    
</div>
</main>


</body>
</html>
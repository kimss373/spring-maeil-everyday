<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.img-thumbnails{
		width:45px;
    	height:45px;
	}
</style>
<script>

	function modifyKeyword(num) {
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/mailAlarmService/modifyKeyword?keyword=keyword" + num;
		window.open(openUrl, 'pop', popOption);
	}
	
	function finish(){
		
		if (confirm("정말 탈퇴하시겠습니까??") == true){    //확인

		     document.finishSubscription.submit();

		 }else{   //취소

		     return false;

		 }
		
	}
	
</script>
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
                            Mail-Everyday 메일 알람 서비스
                        </h1>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Main page content-->
    <div class="container mt-4">
        <div class="row">
            <div class="col-lg-6 mb-6">
                <!-- Billing card 1-->
                <div class="card h-100 border-left-lg border-left-primary">
                    <div class="card-body">
                        <div class="small">등록한 이메일 주소</div>
                        <div class="h3">
                       		${keywordDTO.emailAddress }
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 mb-6">
                <!-- Billing card 2-->
                <div class="card h-100 border-left-lg border-left-secondary">
                    <div class="card-body">
                    <form action="/mailAlarmService/finishSubscription" name="finishSubscription" method="post">
                        <div class="h3">
	                    	서비스 구독 취소하기
                        </div>
                        <button type="button" class="btn btn-sm btn-primary" href="#!" onclick="finish()">
                            정보, 권한 삭제
                            <i data-feather="arrow-right"></i>
                        </button>
                        <input type="hidden" name="memberId" value="${keywordDTO.memberId }" />
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="container mt-4">
        <hr class="mt-0 mb-4" />
        <div class="row">
            <div class="col-lg-4 mb-4">
                <!-- Billing card 1-->
                <div class="card h-100 border-left-lg border-left-primary">
                    <div class="card-body">
                        <div class="small text-muted">키워드 1</div>
                        <div class="h3">
                        <c:choose>
                        	<c:when test="${keywordDTO.keyword1 eq ''}">
                        		<span class="small text-muted">키워드를 입력하세요.</span>
                        	</c:when>
                        	<c:otherwise>
                        		${keywordDTO.keyword1 }
                        	</c:otherwise>
                        </c:choose>
                        </div>
                        <a class="text-arrow-icon small text-secondary" href="#!" onclick="modifyKeyword(1)">
                            수정
                            <i data-feather="arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 mb-4">
                <!-- Billing card 2-->
                <div class="card h-100 border-left-lg border-left-secondary">
                    <div class="card-body">
                        <div class="small text-muted">키워드 2</div>
                        <div class="h3">
                        	<c:choose>
	                        	<c:when test="${keywordDTO.keyword2 eq '' }">
	                        		<span class="small text-muted">키워드를 입력하세요.</span>
	                        	</c:when>
	                        	<c:otherwise>
	                        		${keywordDTO.keyword2 }
	                        	</c:otherwise>
	                        </c:choose>
                        </div>
                        <a class="text-arrow-icon small text-secondary" href="#!" onclick="modifyKeyword(2)">
                            수정
                            <i data-feather="arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 mb-4">
                <!-- Billing card 2-->
                <div class="card h-100 border-left-lg border-left-secondary">
                    <div class="card-body">
                        <div class="small text-muted">키워드 3</div>
                        <div class="h3">
                        	<c:choose>
	                        	<c:when test="${keywordDTO.keyword3 eq '' }">
	                        		<span class="small text-muted">키워드를 입력하세요.</span>
	                        	</c:when>
	                        	<c:otherwise>
	                        		${keywordDTO.keyword3 }
	                        	</c:otherwise>
	                        </c:choose>
                        </div>
                        <a class="text-arrow-icon small text-secondary" href="#!" onclick="modifyKeyword(3)">
                            수정
                            <i data-feather="arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
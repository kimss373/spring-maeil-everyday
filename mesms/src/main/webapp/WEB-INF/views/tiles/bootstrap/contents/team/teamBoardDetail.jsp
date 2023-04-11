<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ymd" value="${teamBoardDTO.createDt }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	function deleteTeamBoard(){
		
		if (confirm("정말 삭제하시겠습니까??") == true){    //확인
	
		     document.deleteBoard.submit();
	
		 }else{   //취소
	
		     return false;
	
		 }
		
	}

</script>
</head>
<body>

<main>
    <header class="page-header page-header-dark bg-gradient-primary-to-secondary pb-10">
        <div class="container">
            <div class="page-header-content pt-4">
                <div class="row align-items-center justify-content-between">
                    <div class="col-auto mt-4">
                        <h1 class="page-header-title">
                            <div class="page-header-icon"><i data-feather="edit-3"></i></div>
                            게시물 
                        </h1>
                        <div class="page-header-subtitle"></div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Main page content-->
    <div class="container mt-n10">
        <div class="row">
            <div class="col-lg-12">
                <!-- Default Bootstrap Form Controls-->
    
    			<div id="default">
                    <div class="card mb-4">
                        <div class="card-header">
                        	<span style="display: inline-block; width: 95%; text-align: left;"><button class="btn btn-sm btn-primary" type="button" onclick="location.href='/team/teamBoard?teamCd=${teamBoardDTO.teamCd}';">목록으로</button></span>
                        	
                        	<c:if test="${sessionScope.memberId eq teamBoardDTO.memberId }">
                        	<form action="/team/deleteTeamBoard" name="deleteBoard" method="post">
	                        	<span style="display: inline-block; width: 95%; text-align: right;"><button class="btn btn-sm btn-primary" type="button" onclick="location.href='/team/modifyTeamBoard?id=${teamBoardDTO.id}';">수정</button>
	                        	<button class="btn btn-sm btn-primary" type="button" onclick="deleteTeamBoard();">삭제</button>
	                        	</span>
                        	<input type="hidden" name="id" value="${teamBoardDTO.id }">
                        	<input type="hidden" name="teamCd" value="${teamBoardDTO.teamCd }">
                        	</form>
                        	</c:if>
                        </div>
                        <div class="card-body">
                            <!-- Component Preview-->
                            <div class="sbp-preview">
                                <div class="sbp-preview-content">
                                        <div class="form-group">
                                            <div>${teamBoardDTO.title }</div>
                                            <p align="right">작성자 : ${teamBoardDTO.memberId } / 조회수 : ${teamBoardDTO.readCnt } / 작성일 : <fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd HH:mm" /></p>
                                        </div>
                                        <hr>
                                        <div class="form-group">
                                            <div>${teamBoardDTO.content }</div>
                                        </div>
                                        <input type="hidden" name="teamCd" value="${teamDTO.teamCd }">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

</body>
</html>
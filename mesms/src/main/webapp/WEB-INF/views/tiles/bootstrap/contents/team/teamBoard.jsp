<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
                            ${teamDTO.teamNm } - 게시판
                        </h1>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Main page content-->
    <div class="container mt-4">
        <!-- Account page navigation-->
        <nav class="nav nav-borders">
            <a class="nav-link ml-0" href="${contextPath }/team/teamMain?teamCd=${teamDTO.teamCd }">메인</a>
            <a class="nav-link active" href="${contextPath }/team/teamBoard?teamCd=${teamDTO.teamCd }">게시판</a>
        </nav>
        <hr class="mt-0 mb-4" />
        
        <!-- Billing history card-->
        <div class="card mb-4">
            <div class="card-header">
            	게시물 개수 - ${boardCnt }
            	<button class="btn btn-sm btn-primary" type="button" onclick="location.href='/team/createTeamBoard?teamCd=${teamDTO.teamCd}';">새 글 쓰기</button>
            </div>
            <div class="card-body">
                <!-- Billing history table-->
                <div class="datatable">
                    <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">조회수</th>
                                <th scope="col">작성일</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th scope="col">제목</th>
                                <th scope="col">작성자</th>
                                <th scope="col">조회수</th>
                                <th scope="col">작성일</th>
                            </tr>
                        </tfoot>
                        <tbody>
                        	<c:forEach var="teamBoardDTO" items="${teamBoardList }">
                        		<c:set var="ymd" value="${teamBoardDTO.createDt }" />
	                        	<tr>
	                                <td><a href="${contextPath }/team/teamBoardDetail?id=${teamBoardDTO.id }">${teamBoardDTO.title }</a></td>
	                                <td>${teamBoardDTO.memberId }</td>
	                                <td>${teamBoardDTO.readCnt }</td>
	                                <td><fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" /></td>
	                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        <!-- Payment methods card-->
        
    </div>
</main>
</body>
</html>
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

	function showParticipationCd(teamCd) {
		var popOption = "width = 650px, height=220px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/team/showParticipationCd?teamCd=" + teamCd;
		window.open(openUrl, 'pop', popOption);
	}
	
	function modifyTeamAnnouncement(teamCd) {
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/team/modifyTeamAnnouncement?teamCd=" + teamCd;
		window.open(openUrl, 'pop', popOption);
	}
	
	function modifyTeamTarget(teamCd) {
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/team/modifyTeamTarget?teamCd=" + teamCd;
		window.open(openUrl, 'pop', popOption);
	}
	
	function createTeamLink(teamCd){
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/team/createTeamLink?teamCd=" + teamCd;
		window.open(openUrl, 'pop', popOption);
	}
	
	function leaveTeam(){
		
		if (confirm("정말 탈퇴하시겠습니까??") == true){    //확인

		     document.leaveform.submit();

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
                            ${teamDTO.teamNm }
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
            <a class="nav-link ml-0 active" href="${contextPath }/team/teamMain?teamCd=${teamDTO.teamCd }">메인</a>
            <a class="nav-link" href="${contextPath }/team/teamBoard?teamCd=${teamDTO.teamCd }">게시판</a>
        </nav>
        <hr class="mt-0 mb-4" />
        <div class="row">
            <div class="col-lg-4 mb-4">
                <!-- Billing card 1-->
                <div class="card h-100 border-left-lg border-left-primary">
                    <div class="card-body">
                        <div class="small text-muted">팀 공지사항</div>
                        <div class="h3">
                        <c:choose>
                        	<c:when test="${teamDTO.teamAnnouncement eq '' }">
                        		<span class="small text-muted">공지사항을 입력하세요.</span>
                        	</c:when>
                        	<c:otherwise>
                        		${teamDTO.teamAnnouncement }
                        	</c:otherwise>
                        </c:choose>
                        </div>
                        <a class="text-arrow-icon small text-secondary" href="#!" onclick="modifyTeamAnnouncement(${teamDTO.teamCd})">
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
                        <div class="small text-muted">팀 목표</div>
                        <div class="h3">
                        	<c:choose>
	                        	<c:when test="${teamDTO.teamTarget eq '' }">
	                        		<span class="small text-muted">목표를 입력하세요.</span>
	                        	</c:when>
	                        	<c:otherwise>
	                        		${teamDTO.teamTarget }
	                        	</c:otherwise>
	                        </c:choose>
                        </div>
                        <a class="text-arrow-icon small text-secondary" href="#!" onclick="modifyTeamTarget(${teamDTO.teamCd})">
                            수정
                            <i data-feather="arrow-right"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 mb-4">
                <!-- Billing card 3-->
                <div class="card h-100 border-left-lg border-left-success">
                    <div class="card-body">
                    	<form action="/team/leaveTeam?teamCd=${teamDTO.teamCd }" name="leaveform" method="post">
                        <div class="h3 d-flex align-items-center">팀 탈퇴</div>
                        <button type="button" class="text-arrow-icon small text-success" href="#" onclick="leaveTeam()">
                            탈퇴하기
                            <i data-feather="arrow-right"></i>
                        </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Billing history card-->
        <div class="card card-header-actions mb-4">
            <div class="card-header">
            	${teamDTO.teamNm } - 팀원
            	<button class="btn btn-sm btn-primary" type="button" onclick="showParticipationCd(${teamDTO.teamCd })">초대코드 확인</button>
            </div>
            <div class="card-body p-0">
                <!-- Billing history table-->
                <div class="table-responsive table-billing-history">
                    <table class="table mb-0">
                        <thead>
                            <tr>
                                <th scope="col">회원 ID</th>
                                <th scope="col">가입일자</th>
                                <th scope="col"></th>
                                <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="teamMemberDTO" items="${teamMemberList }">
                        		<c:set var="ymd" value="${teamMemberDTO.joinDt }" />
	                        	<tr>
	                                <td><img class="img-thumbnails" src="${contextPath }/thumbnailsSmall?profileImage=${teamMemberDTO.profileImage }" /> ${teamMemberDTO.memberNm } (${teamMemberDTO.memberId })</td>
	                                <td><fmt:formatDate value="${ymd}" pattern="yyyy-MM-dd" /></td>
	                                <td></td>
	                                <td><span class="badge badge-light">Pending</span></td>
	                            </tr>
                        	</c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        
        <!-- Payment methods card-->
        <div class="card card-header-actions mb-4">
            <div class="card-header">
                링크 공유
                <button class="btn btn-sm btn-primary" type="button" onclick="createTeamLink(${teamDTO.teamCd})">링크 추가</button>
            </div>
            <div class="card-body">
            	<c:choose>
            		<c:when test="${empty teamLinkList }">
            			<!-- Payment method 1-->
		                <div class="d-flex align-items-center justify-content-between">
		                    <div class="d-flex align-items-center">
		                        <div class="ml-4">
		                            <div class="small text-muted">등록된 링크가 없습니다.</div>
		                        </div>
		                    </div>
		                </div>
		                <hr />
            		</c:when>
            		<c:otherwise>
            			<c:forEach var="teamLinkDTO" items="${teamLinkList }">
            				<!-- Payment method 1-->
			                <div class="d-flex align-items-center justify-content-between">
			                    <div class="d-flex align-items-center">
			                        <div class="ml-4">
			                            <div class="small">${teamLinkDTO.content }</div>
			                            <div class="text-xs text-muted"><a href="${teamLinkDTO.link }" target="_blank">${teamLinkDTO.link }</a></div>
			                        </div>
			                    </div>
			                    <div class="ml-4 small">
			                        <div class="badge badge-light mr-3">${teamLinkDTO.memberId }</div>
			                        <a href="#!">수정</a>
			                        <a href="#!">삭제</a>
			                    </div>
			                </div>
			                <hr />
            			</c:forEach>
            		</c:otherwise>
            	</c:choose>
                
            </div>
        </div>
    </div>
</main>
</body>
</html>
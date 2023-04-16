<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath }/resources/jquery-3.6.1.min.js"></script>
<script>
	$().ready(function() {
		
		$("#clickPop").click(function(){
			
			var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
			var openUrl = "/project/createBacklog?projectCd=${projectDTO.projectCd }";
			window.open(openUrl, 'pop', popOption);
			
		});
		
		
	});
	
	function modifyBacklog(id){
		
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/project/modifyBacklog?id=" + id + "&projectCd=${projectDTO.projectCd }";
		window.open(openUrl, 'pop', popOption);
		
	}
	
	function modifySprint1(id) {
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/project/modifySprint?id=" + id + "&projectCd=${projectDTO.projectCd }";
		window.open(openUrl, 'pop', popOption);
	}
	
	function deleteSprint1(id) {
		
		if (confirm("스프린트를 삭제하시겠습니까?") == true){    //확인

			 $("#deleteSprint" + id).submit();

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
                                            ${projectDTO.projectNm } - 업무 생성&지정
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
                            <a class="nav-link active" href="${contextPath }/project/projectBacklog?projectCd=${projectDTO.projectCd }">업무 생성&지정</a>
                            <a class="nav-link" href="${contextPath }/project/projectMain?projectCd=${projectDTO.projectCd }">보드</a>
                           	<a class="nav-link" href="${contextPath }/project/chart?projectCd=${projectDTO.projectCd }">그래프</a>
                            <a class="nav-link ml-0" href="${contextPath }/project/projectSetting?projectCd=${projectDTO.projectCd }">프로젝트 설정</a>
                            <a class="nav-link" href="${contextPath }/project/inviteMember?projectCd=${projectDTO.projectCd }">초대하기</a>
                        </nav>
                        <hr class="mt-0 mb-4" />
                        <div class="row">
	                        <div class="container">
								<div class="card card-header-actions">
				                     <div class="card-header">
				                         업무 대기
				                         <div>
				                             <button class="btn btn-sm btn-primary" id="createSprint" type="button" onclick="location.href='${contextPath }/project/createSprint?projectCd=${projectDTO.projectCd }';">스프린트 생성</button>
				                         </div>
				                     </div>
				                     <div class="card-body">
				                     <c:choose>
	                            		<c:when test="${empty projectWorkList }">
	                            		</c:when>
	                            		<c:otherwise>
	                            			<c:forEach var="projectWorkDTO" items="${projectWorkList }">
	                            				<c:if test="${projectWorkDTO.projectSprintId eq -1 }">
	                            				<!-- Payment method 1-->
				                                <div class="d-flex align-items-center justify-content-between">
				                                    <div class="d-flex align-items-center">
				                                    	<i class="fab fa-cc-mastercard fa-2x cc-color-mastercard"></i>
				                                        <div class="ml-4">
				                                            <div class="small">${initial } - ${projectWorkDTO.projectWorkNum } &nbsp;&nbsp;<a href="#" onclick="modifyBacklog(${projectWorkDTO.id})">${projectWorkDTO.issue }</a></div>
				                                        </div>
				                                    </div>
				                                    <div class="ml-4 small">
				                                        <div class="text-xs text-muted">${projectWorkDTO.todoCondition }</div>
				                                    </div>
				                                    <div class="ml-4 small">
				                                        <div class="text-xs text-muted">담당자 - ${projectWorkDTO.responsibility }</div>
				                                    </div>
				                                    <div class="ml-4 small">
				                                        <a href="#" onclick="modifyBacklog(${projectWorkDTO.id})">수정</a>
				                                    </div>
				                                </div>
				                                <hr/>
				                                </c:if>
	                            			</c:forEach>
	                            		</c:otherwise>
	                            	</c:choose>
		                                <button class="btn btn-sm btn-primary" id="clickPop" type="button">＋ 추가</button>
									</div>
				            	</div>
	                        </div>
                        </div>
                        <c:choose>
                       		<c:when test="${empty projectSprintList }">
                       			<hr>
		                        <div class="row">
			                        <div class="container">
										<div class="card card-header-actions">
						                     <div class="card-header">
						                         스프린트
						                         <div>
						                         </div>
						                     </div>
						                     <div class="card-body"><div class="text-xs text-muted">스프린트가 없습니다</div></div>
						            	</div>
			                        </div>
		                        </div>
                       			
                       		</c:when> 
                       		<c:otherwise>
                       			<c:forEach var="projectSprintDTO" items="${projectSprintList }">
                       				<c:if test="${projectSprintDTO.done eq 'N' }">
                       				<hr>
			                        <div class="row">
				                        <div class="container">
											<div class="card card-header-actions">
							                     <div class="card-header">
							                          ${projectSprintDTO.num } ${projectSprintDTO.sprintNm }
							                         <div>
							                         	 <form action="/project/deleteSprint" id="deleteSprint${projectSprintDTO.id }" method="post">
							                         	 	<input type="hidden" name="id" value="${projectSprintDTO.id }">
							                         	 	<input type="hidden" name="projectCd" value="${projectSprintDTO.projectCd }">
							                             	<button class="btn btn-sm btn-primary" type="button" onclick="deleteSprint1(${projectSprintDTO.id });">스프린트 삭제</button>
							                             <button class="btn btn-sm btn-primary" id="modifySprint" type="button" onclick="modifySprint1(${projectSprintDTO.id })">스프린트 수정</button>
							                             </form>
							                         </div>
							                     </div>
							                     <div class="card-body">
							                     	<c:forEach var="projectWorkDTO" items="${projectWorkList }">
			                            				<c:if test="${projectWorkDTO.projectSprintId eq projectSprintDTO.id }">
			                            				<!-- Payment method 1-->
						                                <div class="d-flex align-items-center justify-content-between">
						                                    <div class="d-flex align-items-center">
						                                    	<i class="fab fa-cc-mastercard fa-2x cc-color-mastercard"></i>
						                                        <div class="ml-4">
						                                            <div class="small">${initial } - ${projectWorkDTO.projectWorkNum } &nbsp;&nbsp;<a href="#" onclick="modifyBacklog(${projectWorkDTO.id})">${projectWorkDTO.issue }</a></div>
						                                        </div>
						                                    </div>
						                                    <div class="ml-4 small">
						                                        <div class="text-xs text-muted">${projectWorkDTO.todoCondition }</div>
						                                    </div>
						                                    <div class="ml-4 small">
						                                        <div class="text-xs text-muted">담당자 - ${projectWorkDTO.responsibility }</div>
						                                    </div>
						                                    <div class="ml-4 small">
						                                        <a href="#" onclick="modifyBacklog(${projectWorkDTO.id})">수정</a>
						                                    </div>
						                                </div>
						                                <hr/>
						                                </c:if>
			                            			</c:forEach>
							                     </div>
							            	</div>
				                        </div>
			                        </div>
			                       	</c:if>
                       			</c:forEach>
                       		</c:otherwise>
                       	</c:choose>
                       	
                       	<div></div>
                       	<div></div>
                       	<div></div>
                       	<!-- Account page navigation-->
                        <nav class="nav nav-borders">
                            <a class="nav-link active" href="#">완료된 스프린트</a>
                        </nav>
                        <hr class="mt-0 mb-4" />
                        <c:forEach var="projectSprintDTO" items="${projectSprintList }">
              				<c:if test="${projectSprintDTO.done eq 'Y' }">
              				<hr>
	                        <div class="row">
		                        <div class="container">
									<div class="card card-header-actions">
					                     <div class="card-header">
					                          ${projectSprintDTO.num } ${projectSprintDTO.sprintNm }
					                         <div>
					                         			<form action="/project/deleteSprint" id="deleteSprint${projectSprintDTO.id }" method="post">
							                         	 	<input type="hidden" name="id" value="${projectSprintDTO.id }">
							                         	 	<input type="hidden" name="projectCd" value="${projectSprintDTO.projectCd }">
							                             	<button class="btn btn-sm btn-primary" type="button" onclick="deleteSprint1(${projectSprintDTO.id });">스프린트 삭제</button>
							                             <button class="btn btn-sm btn-primary" id="modifySprint" type="button" onclick="modifySprint1(${projectSprintDTO.id })">스프린트 수정</button>
							                             </form>
					                         </div>
					                     </div>
					                     <div class="card-body">
					                     	<c:forEach var="projectWorkDTO" items="${projectWorkList }">
	                            				<c:if test="${projectWorkDTO.projectSprintId eq projectSprintDTO.id }">
	                            				<!-- Payment method 1-->
				                                <div class="d-flex align-items-center justify-content-between">
				                                    <div class="d-flex align-items-center">
				                                    	<i class="fab fa-cc-mastercard fa-2x cc-color-mastercard"></i>
				                                        <div class="ml-4">
				                                            <div class="small">${initial } - ${projectWorkDTO.projectWorkNum } &nbsp;&nbsp;<a href="#" onclick="modifyBacklog(${projectWorkDTO.id})">${projectWorkDTO.issue }</a></div>
				                                        </div>
				                                    </div>
				                                    <div class="ml-4 small">
				                                        <div class="text-xs text-muted">${projectWorkDTO.todoCondition }</div>
				                                    </div>
				                                    <div class="ml-4 small">
				                                        <div class="text-xs text-muted">담당자 - ${projectWorkDTO.responsibility }</div>
				                                    </div>
				                                    <div class="ml-4 small">
				                                        <a href="#" onclick="modifyBacklog(${projectWorkDTO.id})">수정</a>
				                                    </div>
				                                </div>
				                                <hr/>
				                                </c:if>
	                            			</c:forEach>
					                     </div>
					            	</div>
		                        </div>
	                        </div>
	                       	</c:if>
                     	</c:forEach>
                        
                        
                    </div>
                    
                    
                </main>
</body>
</html>
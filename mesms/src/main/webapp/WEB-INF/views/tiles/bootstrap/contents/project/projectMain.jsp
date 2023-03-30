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
	
	function changeTodoCondition(id, todoCondition){
		
		$.ajax({
		       type : "post",
		       url  : "${contextPath}/project/changeTodoCondition",
		       data : {
		    	   "id" : id,
		    	   "todoCondition" : todoCondition,
		    	   "projectCd" : ${projectDTO.projectCd}
		       },
		       success : function (data){
		    	   if (data == "success"){
		    	   		location.href="${contextPath}/project/projectMain?projectCd=" + ${projectDTO.projectCd};
		    	   }
		       }
		    });
		
		
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
                                            ${projectDTO.projectNm } - 보드
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
                            <a class="nav-link" href="${contextPath }/project/projectBacklog?projectCd=${projectDTO.projectCd }">백로그</a>
                            <a class="nav-link active" href="${contextPath }/project/projectMain?projectCd=${projectDTO.projectCd }">보드</a>
                            <a class="nav-link" href="${contextPath }/project/chart?projectCd=${projectDTO.projectCd }">그래프</a>
                            <a class="nav-link ml-0" href="${contextPath }/project/projectSetting?projectCd=${projectDTO.projectCd }">프로젝트 설정</a>
                            <a class="nav-link" href="${contextPath }/project/inviteMember?projectCd=${projectDTO.projectCd }">초대하기</a>
                        </nav>
                        <hr class="mt-0 mb-4" />
                        <div class="row">
                            <div class="col-lg-4">
                                <!-- Two factor authentication card-->
                                <div class="card mb-4">
                                    <div class="card-header">ToDo</div>
                                    <div class="card-body">
                                    	<c:forEach var="todoDTO" items="${boardMap.todoList }">
                                        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="opacity: 1;">
										    <div class="toast-header text-primary">
										        <i data-feather="bell"></i>
										        <strong class="mr-auto">${todoDTO.issue }</strong>
										        <small class="text-muted ml-2">${todoDTO.responsibility }</small>
										        <button class="ml-2 mb-1 close" type="button" data-dismiss="toast" aria-label="Close">
										            <span aria-hidden="true">×</span>
										        </button>
										    </div>
										    <div class="toast-body"><a href="">상세보기</a><br>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${todoDTO.id}, 'TODO')">TODO</button>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${todoDTO.id}, 'inProgress')">inProgress</button>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${todoDTO.id}, 'Done')">Done</button></div>
										</div>
										</c:forEach>
										                                     
                                    </div>
                                </div>
                                
                            </div>
                            <!-- second -->
                            <div class="col-lg-4">
                                <!-- Two factor authentication card-->
                                <div class="card mb-4">
                                    <div class="card-header">inProgress</div>
                                    <div class="card-body">
                                        <c:forEach var="inprogressDTO" items="${boardMap.inprogressList }">
                                        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="opacity: 1;">
										    <div class="toast-header text-primary">
										        <i data-feather="bell"></i>
										        <strong class="mr-auto">${inprogressDTO.issue }</strong>
										        <small class="text-muted ml-2">${inprogressDTO.responsibility }</small>
										        <button class="ml-2 mb-1 close" type="button" data-dismiss="toast" aria-label="Close">
										            <span aria-hidden="true">×</span>
										        </button>
										    </div>
										    <div class="toast-body"><a href="">상세보기</a><br>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${inprogressDTO.id}, 'TODO')">TODO</button>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${inprogressDTO.id}, 'inProgress')">inProgress</button>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${inprogressDTO.id}, 'Done')">Done</button></div>
										</div>
										</c:forEach>
                                        
                                    </div>
                                </div>
                                
                            </div>
                            <!-- secondEnd -->
                            <!-- third -->
                            <div class="col-lg-4">
                                <!-- Two factor authentication card-->
                                <div class="card mb-4">
                                    <div class="card-header">Done</div>
                                    <div class="card-body">
                                        <c:forEach var="doneDTO" items="${boardMap.doneList }">
                                        <div class="toast" role="alert" aria-live="assertive" aria-atomic="true" style="opacity: 1;">
										    <div class="toast-header text-primary">
										        <i data-feather="bell"></i>
										        <strong class="mr-auto">${doneDTO.issue }</strong>
										        <small class="text-muted ml-2">${doneDTO.responsibility }</small>
										        <button class="ml-2 mb-1 close" type="button" data-dismiss="toast" aria-label="Close">
										            <span aria-hidden="true">×</span>
										        </button>
										    </div>
										    <div class="toast-body"><a href="">상세보기</a><br>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${doneDTO.id}, 'TODO')">TODO</button>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${doneDTO.id}, 'inProgress')">inProgress</button>
										     <button class="btn btn-primary" type="button" onclick="changeTodoCondition(${doneDTO.id}, 'Done')">Done</button></div>
										</div>
										</c:forEach>
                                    </div>
                                </div>
                                
                            </div>
                            <!-- thirdEnd -->
                        </div>
                        
                    </div>
                    
                    
                </main>
</body>
</html>
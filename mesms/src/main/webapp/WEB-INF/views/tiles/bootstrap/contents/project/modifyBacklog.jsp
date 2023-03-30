<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업무 수정</title>
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
	                            업무
	                        </h1>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </header>
	    <!-- Main page content-->
	    <div class="container mt-4">
	        <hr class="mt-0 mb-4" />
	        <div class="row">
	            <div class="col-xl-8">
	                <!-- Account details card-->
	                <div class="card mb-4">
	                    <div class="card-header">업무 세부사항</div>
	                    <div class="card-body">
	                        <form action="${contextPath }/project/modifyBacklog" method="post">
	                            <!-- Form Group (memberNm)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">업무 내용</label>
	                                <input class="form-control" id="issue" name="issue" type="text" value="${projectWorkDTO.issue }" />
	                            </div>
	                            <!-- Form Group (memberId)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">책임자</label>
				                    <select name="responsibility">
				                    	<c:forEach var="projectMemberDTO" items="${projectMemberList }">
				                    		<c:choose>
					                    		<c:when test="${projectMemberDTO.memberId eq projectWorkDTO.responsibility}">
		                            				<option selected>${projectMemberDTO.memberId }</option>
					                    		</c:when>
				                    			<c:otherwise>
		                            				<option>${projectMemberDTO.memberId }</option>
				                    			</c:otherwise>
				                    		</c:choose>
                            			</c:forEach>
				                    </select>
	                            </div>
	                            
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">상태</label>
				                    <select name="todoCondition">
			                    		<c:choose>
				                    		<c:when test="${projectWorkDTO.todoCondition eq 'TODO'}">
	                            				<option selected>TODO</option>
		                           				<option>inProgress</option>
		                           				<option>Done</option>
				                    		</c:when>
				                    		<c:when test="${projectWorkDTO.todoCondition eq 'inProgress'}">
	                            				<option>TODO</option>
		                           				<option selected>inProgress</option>
		                           				<option>Done</option>
				                    		</c:when>
			                    			<c:otherwise>
	                            				<option>TODO</option>
		                           				<option>inProgress</option>
		                           				<option selected>Done</option>
			                    			</c:otherwise>
			                    		</c:choose>
				                    </select>
	                            </div>
	                            
	                            <!-- Form Group (memberId)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">스프린트</label>
				                    <select name="projectSprintId">
				                    	<option value="-1">업무 대기</option>
				                    	<c:forEach var="projectSprintDTO" items="${projectSprintList }">
				                    		<c:choose>
					                    		<c:when test="${projectSprintDTO.id eq projectWorkDTO.projectSprintId}">
		                            				<option value="${projectSprintDTO.id}" selected>${projectSprintDTO.num } - ${projectSprintDTO.sprintNm }</option>
					                    		</c:when>
				                    			<c:otherwise>
		                            				<option value="${projectSprintDTO.id}">${projectSprintDTO.num } - ${projectSprintDTO.sprintNm }</option>
				                    			</c:otherwise>
				                    		</c:choose>
                            			</c:forEach>
				                    </select>
	                            </div>
	                            
                                <div class="form-group col-md-6">
                                	<input type="hidden" name="id" value="${projectWorkDTO.id }">
                                	<input type="hidden" name="projectWorkNum" value="${projectWorkDTO.projectWorkNum }">
                                	<input type="hidden" name="projectCd" value="${projectCd }">
	                              	<button class="btn btn-primary" id="create" type="submit">완료</button>
                                </div>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</main>
</body>
</html>
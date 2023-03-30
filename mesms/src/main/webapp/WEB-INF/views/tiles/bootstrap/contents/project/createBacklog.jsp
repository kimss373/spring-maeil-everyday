<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업무 만들기</title>
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
	                        <form action="${contextPath }/project/createBacklog" method="post">
	                            <!-- Form Group (memberNm)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">업무 내용</label>
	                                <input class="form-control" id="inputUsername" name="issue" type="text" />
	                            </div>
	                            <!-- Form Group (memberId)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">책임자</label>
				                    <select name="responsibility">
				                    	<c:forEach var="projectMemberDTO" items="${projectMemberList }">
                            				<option>${projectMemberDTO.memberId }</option>
                            			</c:forEach>
				                    </select>
	                            </div>
	                            
                                <div class="form-group col-md-6">
                                	<input type="hidden" name="projectCd" value="${projectCd }">
	                              	<button class="btn btn-primary" id="create" type="submit">생성하기</button>
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
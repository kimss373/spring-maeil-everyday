<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프린트수정</title>
<script>
	
	function sprintDone1(YorN) {
		document.getElementById("done").value = YorN;
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
	                            스프린트
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
	                    <div class="card-header">스프린트 이름 변경 및 완료</div>
	                    <div class="card-body">
	                        <form action="${contextPath }/project/modifySprint" method="post">
	                        <input type="hidden" id="done" name="done" value="N">
	                            <!-- Form Group (memberNm)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">스프린트 이름</label>
	                                <input class="form-control" id="sprintNm" name="sprintNm" type="text" value="${projectSprintDTO.sprintNm }" />
	                            </div>
	                            <!-- Form Group (memberId)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">상태</label>
				                    <button class="btn btn-primary" id="sprintDone" type="button" onclick="sprintDone1('Y')">스프린트 완료</button>
				                    <button class="btn btn-primary" id="cancel" type="button" onclick="sprintDone1('N')">취소</button>
	                            </div>
	                            
	                            
                                <div class="form-group col-md-6">
                                	<input type="hidden" name="id" value="${projectSprintDTO.id }">
                                	<input type="hidden" name="num" value="${projectSprintDTO.num }">
                                	<input type="hidden" name="projectCd" value="${projectCd }">
	                              	<button class="btn btn-primary" id="create" type="submit">설정</button>
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
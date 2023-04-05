<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>링크 만들기</title>
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
	                            링크 공유
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
	                    <div class="card-header">링크 정보</div>
	                    <div class="card-body">
	                        <form action="${contextPath }/team/createTeamLink" method="post">
	                            <!-- Form Group (memberNm)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">링크 주소</label>
	                                <input class="form-control" id="link" name="link" type="text" placeholder="https:// 포함해서 입력하세요"/>
	                            </div>
	                            <!-- Form Group (memberId)-->
	                            <div class="form-group">
	                                <label class="small mb-1" for="inputUsername">설명</label>
				                    <textarea class="form-control" id="content" name="content" rows="15" cols="15" placeholder="200자 까지 입력 가능합니다."></textarea>
	                            </div>
	                            
                                <div class="form-group col-md-6">
                                	<input type="hidden" name="teamCd" value="${teamCd }">
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
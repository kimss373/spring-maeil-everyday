<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
<html>
<head>
<meta  charset="utf-8">
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
                                            프로젝트 목록
                                        </h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </header>
                    <!-- Main page content-->
                    <div class="container mt-4">
                        
                        <hr class="mt-0 mb-4" />
                        
                        <!-- Payment methods card-->
                        <div class="card card-header-actions mb-4">
                            <div class="card-header">
                                내 프로젝트
                                <button class="btn btn-sm btn-primary" type="button" onclick="location.href='${contextPath }/project/createProject'">새 프로젝트 만들기</button>
                            </div>
                            <div class="card-body">
                            	<c:choose>
                            		<c:when test="${empty projectList }">
                            			<div class="text-xs text-muted">참여한 프로젝트가 없습니다.</div>
                            		</c:when>
                            		<c:otherwise>
                            			<c:forEach var="projectDTO" items="${projectList }">
                            				<!-- Payment method 1-->
			                                <div class="d-flex align-items-center justify-content-between">
			                                    <div class="d-flex align-items-center">
			                                    	<i class="fab fa-cc-mastercard fa-2x cc-color-mastercard"></i>
			                                        <div class="ml-4">
			                                            <div class="small"><a href="${contextPath }/project/projectMain?projectCd=${projectDTO.projectCd }">${projectDTO.projectNm }</a></div>
			                                        </div>
			                                    </div>
			                                    <div class="ml-4 small">
			                                        <a href="${contextPath }/project/projectMain?projectCd=${projectDTO.projectCd }">이동</a>
			                                    </div>
			                                </div>
			                                <hr/>
                            			</c:forEach>
                            		</c:otherwise>
                            	</c:choose>
                            </div>
                        </div>
                        
                    </div>
                </main>
</body>
</html>
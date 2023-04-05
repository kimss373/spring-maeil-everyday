<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath }/resources/ckeditor/ckeditor.js"></script>
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
                            게시물 작성
                        </h1>
                        <div class="page-header-subtitle">작성</div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Main page content-->
    <div class="container mt-n10">
        <div class="row">
            <div class="col-lg-9">
                <!-- Default Bootstrap Form Controls-->
    
    			<div id="default">
                    <div class="card mb-4">
                        <div class="card-header"></div>
                        <div class="card-body">
                            <!-- Component Preview-->
                            <div class="sbp-preview">
                                <div class="sbp-preview-content">
                                    <form action="/team/createTeamBoard" method="post">
                                        <div class="form-group">
                                            <label for="exampleFormControlInput1">제목</label>
                                            <input class="form-control" name="title" id="exampleFormControlInput1" type="text" />
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleFormControlTextarea1">내용</label>
                                            <textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="3"></textarea>
                                            <script>CKEDITOR.replace("content");</script>
                                        </div>
                                        <button class="btn btn-sm btn-primary">작성</button>
                                        <input type="hidden" name="teamCd" value="${teamDTO.teamCd }">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-3">
	            <div class="nav-sticky">
	                <div class="card">
	                    <div class="card-body">
	                        <ul class="nav flex-column" id="stickyNav">
	                            <li class="nav-item"><a class="nav-link" href="#default">Default Form Controls</a></li>
	                            <li class="nav-item"><a class="nav-link" href="#solid">Solid Form Controls</a></li>
	                            <li class="nav-item"><a class="nav-link" href="#checkbox">Default Checkboxes &amp; Radio</a></li>
	                            <li class="nav-item"><a class="nav-link" href="#checkboxSolid">Solid Checkboxes &amp; Radio</a></li>
	                            <li class="nav-item">
	                                <a class="nav-link" href="#inputGroupJoined">
	                                    <div class="d-flex align-items-center justify-content-between">
	                                        Input Groups
	                                        <span class="badge badge-primary-soft text-primary">New</span>
	                                    </div>
	                                </a>
	                            </li>
	                            <li class="nav-item">
	                                <a class="nav-link" href="#dateRangePicker">
	                                    <div class="d-flex align-items-center justify-content-between">
	                                        Date Range Picker
	                                        <span class="badge badge-primary-soft text-primary">New</span>
	                                    </div>
	                                </a>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	        </div>
        </div>
    </div>
</main>

</body>
</html>
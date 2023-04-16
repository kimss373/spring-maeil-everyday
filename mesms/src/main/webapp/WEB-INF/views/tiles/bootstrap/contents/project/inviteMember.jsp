<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                                            ${projectDTO.projectNm } - 초대하기
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
                            <a class="nav-link" href="${contextPath }/project/projectBacklog?projectCd=${projectDTO.projectCd }">업무 생성&지정</a>
                            <a class="nav-link" href="${contextPath }/project/projectMain?projectCd=${projectDTO.projectCd }">보드</a>
                            <a class="nav-link" href="${contextPath }/project/chart?projectCd=${projectDTO.projectCd }">그래프</a>
                            <a class="nav-link ml-0" href="${contextPath }/project/projectSetting?projectCd=${projectDTO.projectCd }">프로젝트 설정</a>
                            <a class="nav-link active" href="${contextPath }/project/inviteMember?projectCd=${projectDTO.projectCd }">초대하기</a>
                        </nav>
                        <hr class="mt-0 mb-4" />
                        <div class="row">
                            <div class="col-lg-8">
                                <!-- Change password card-->
                                <div class="card mb-4">
                                    <div class="card-header">코드확인</div>
                                    <div class="card-body">
                                            <!-- Form Group (current password)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="currentPassword">초대코드</label><br>
                                                ${projectDTO.participationCd }
                                            </div>
                                    </div>
                                </div>
                                
                            </div>
                            
                        </div>
                    </div>
                </main>
</body>
</html>
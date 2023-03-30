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
                                            참가하기
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
                            <div class="col-lg-8">
                                <!-- Change password card-->
                                <div class="card mb-4">
                                    <div class="card-header">코드입력</div>
                                    <div class="card-body">
                                        <form action="${contextPath }/project/joinProjectOrTeam" method="post">
                                            <!-- Form Group (current password)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="currentPassword">초대코드</label>
                                                <input class="form-control" id="projectNm" name="participationCd" type="text" placeholder="초대받은 코드를 입력하세요." />
                                            </div>
                                            
                                            <button class="btn btn-primary" type="submit">참가하기</button>
                                        </form>
                                    </div>
                                </div>
                                
                            </div>
                            
                        </div>
                    </div>
                </main>
</body>
</html>
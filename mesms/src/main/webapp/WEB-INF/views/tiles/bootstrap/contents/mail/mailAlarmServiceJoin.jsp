<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<script>

	$().ready(function() {
	
		$("form").submit(function(){
			
		    var projectNm = $("#projectNm").val();
		   
		    if (projectNm == ''){
		   		alert("팀 이름을 입력하세요");
		   		return false;
		    }
		    
		 });
		
	});
</script>

                <main>
                    <header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
                        <div class="container-fluid">
                            <div class="page-header-content">
                                <div class="row align-items-center justify-content-between pt-3">
                                    <div class="col-auto mb-3">
                                        <h1 class="page-header-title">
                                            <div class="page-header-icon"><i data-feather="user"></i></div>
                                            메일 알람 서비스
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
                                    <div class="card-header">구글이메일 접근 동의</div>
                                    <div class="card-body">
                                            <button class="btn btn-primary" type="submit" onclick="location.href='${contextPath}/mailAlarmService/join';">시작</button>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="col-lg-4">
                                <!-- Two factor authentication card-->
                                <div class="card mb-4">
                                    <div class="card-header">메일 알람 서비스</div>
                                    <div class="card-body">
                                        <p>이메일을 등록하고 키워드를 설정하면 알람을 받을 수 있습니다.</p>
                                        <p>현재 구글 이메일만 가능합니다.<br>
                                        </p>
                                    </div>
                                </div>
                                <!-- Delete account card-->
                                <div class="card mb-4">
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                
</html>

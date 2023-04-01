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
                                            팀 생성
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
                                    <div class="card-header">팀 이름</div>
                                    <div class="card-body">
                                        <form action="${contextPath }/team/createTeam" method="post">
                                            <!-- Form Group (current password)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="currentPassword">이름</label>
                                                <input class="form-control" id="teamNm" name="teamNm" type="text" placeholder="팀 이름" />
                                            </div>
                                            
                                            <button class="btn btn-primary" type="submit">생성</button>
                                        </form>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="col-lg-4">
                                <!-- Two factor authentication card-->
                                <div class="card mb-4">
                                    <div class="card-header">팀 세부사항 설정</div>
                                    <div class="card-body">
                                        <p>팀 설정에서 언제든지 이름 및 세부사항을 변경할 수 있습니다.</p>
                                        <p>초대하기 탭에서 입장코드를 확인할 수 있습니다. 초대 하고싶은 팀원에게 알려주세요.<br>
                                        &nbsp; - 참가하기에 입장코드를 입력하면 팀에 참여할 수 있습니다.
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

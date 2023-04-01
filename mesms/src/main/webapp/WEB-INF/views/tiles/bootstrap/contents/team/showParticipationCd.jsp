<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	function closeWindow(){
		window.close();
	}

</script>
</head>
<body>
						<div class="row">
                            <div class="col-lg-8">
                                <!-- Change password card-->
                                <div class="card mb-4">
                                    <div class="card-header">코드확인</div>
                                    <div class="card-body">
                                            <!-- Form Group (current password)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="currentPassword">초대코드</label><br>
                                                ${teamDTO.participationCd }
                                            </div>
                                    </div>
                                    <div class="card-body">
                                            <!-- Form Group (current password)-->
                                            <div class="form-group">
                                                <button class="btn btn-sm btn-primary" type="button" onclick="closeWindow()">닫기</button>
                                            </div>
                                    </div>
                                </div>
                                
                            </div>
                            
                        </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>

	function fileUploadCheck(fileVal){
		
		if (fileVal.files && fileVal.files[0]) {

			var maxSize = 5 * 1024 * 1024;
			var fileSize = fileVal.files[0].size;

			if(fileSize > maxSize){
				alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
				fileVal.value = null;
			}
			
			var fileName = fileVal.files[0].name;
			//파일명 길이
			var fileLength = fileName.length;
			//파일의 확장자 추출
			var fileDot = fileName.lastIndexOf(".");
			//파일의 확장자 소문자로 변경
			var fileType = fileName.substring(fileDot+1,fileLength).toLowerCase();
			
			if (!(fileType == 'jpg' || fileType == 'png')){
				alert("첨부파일 확장자는 jpg나 png만 등록 가능합니다");
				fileVal.value = null;
			}
			
		}
	}
	
	$().ready(function() {
		
		$("form").submit(function(){
			
			var num = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
			
			var hp = $("#hp").val();
			
			
			for (var i=0 ; i<hp.length ; i++) {
				if (!(hp.charAt(i) in num)){
					alert("연락처를 양식에 맞게 입력하세요");
					$("#hp").focus();
					return false;
				}
			}
			
		});
			
		
	});
	
	
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
                                            회원 정보 변경
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
                            <div class="col-xl-4">
                                <!-- Profile picture card-->
                                <div class="card">
                                    <div class="card-header">Profile Picture</div>
                                    <div class="card-body text-center">
                                        <!-- Profile picture image-->
                                        <img class="img-account-profile rounded-circle mb-2" src="${contextPath }/thumbnails?profileImage=${sessionScope.profileImage}" alt="" />
                                        <!-- Profile picture help block-->
                                        <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                                        <!-- Profile picture upload button-->
                                        <form action="${contextPath }/member/myInfo/changeImage" method="post" enctype="multipart/form-data">
                                        	<input type="file" name="profileImage" class="btn btn-primary" onchange="fileUploadCheck(this)">
                                        	<button class="btn btn-primary" id="newProfileImage" type="submit">이미지 저장</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-8">
                                <!-- Account details card-->
                                <div class="card mb-4">
                                    <div class="card-header">Account Details</div>
                                    <div class="card-body">
                                        <form action="${contextPath }/member/myInfo" method="post">
                                            <!-- Form Group (memberNm)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputUsername">이름 (how your name will appear to other users on the site)</label>
                                                <input class="form-control" id="inputUsername" name="memberNm" type="text" placeholder="Enter your username" value="${memberDTO.memberNm }" />
                                            </div>
                                            <!-- Form Group (memberId)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputUsername">아이디</label>
                                                <input class="form-control" id="inputUsername" name="memberId" type="text" placeholder="Enter your username" value="${memberDTO.memberId }" readonly />
                                            </div>
                                            <!-- Form Row        -->
                                            <div class="form-row">
                                                <!-- Form Group (organization name)-->
                                                <div class="form-group col-md-6">
                                                    <label class="small mb-1" for="inputOrgName">생년월일</label>
                                                    <input class="form-control" id="inputOrgName" name="dateBirth" type="text" placeholder="Enter your organization name" value="${memberDTO.dateBirth }" readonly />
                                                </div>
                                                <!-- Form Group (location)-->
                                                <div class="form-group col-md-6">
                                                    <label class="small mb-1" for="inputLocation">연락처</label>
                                                    <input class="form-control" id="inputLocation" name="hp" type="text" placeholder="Enter your location" value="${memberDTO.hp }"/>
                                                </div>
                                            </div>
                                            <!-- Form Group (email address)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">이메일</label>
                                                <input class="form-control" id="inputEmailAddress" name="email" type="email" placeholder="Enter your email address" value="${memberDTO.email }" />
                                            </div>
                                            <!-- Form Row-->
                                            <div class="form-row">
                                                <!-- Form Group (phone number)-->
                                                <div class="form-group col-md-6">
                                                    <label class="small mb-1" for="inputPhone">mail everyday 가입 여부(메일 알람 서비스)</label>
                                                    <input class="form-control" id="inputPhone" name="meSubscriptionYn" type="tel" placeholder="Enter your phone number" value="${memberDTO.meSubscriptionYn }" readonly/>
                                                </div>
                                            </div>
                                            <!-- Save changes button-->
                                            <div class="form-row">
                                                <!-- Form Group (phone number)-->
                                                <div class="form-group col-md-6">
		                                            <a href="${contextPath}/setNewPasswd"><button type="button" class="btn btn-primary">비밀번호 변경하기</button></a>
                                                </div>
                                                <div class="form-group col-md-6">
		                                            <button class="btn btn-primary" type="submit">저장하기</button>
                                                </div>
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
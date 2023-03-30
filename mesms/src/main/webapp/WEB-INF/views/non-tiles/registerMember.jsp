<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>ME 회원가입</title>
    <link href="${contextPath }/resources/bootstrap/css/styles.css" rel="stylesheet" />
    <link rel="icon" type="image/x-icon" href="${contextPath }/resources/bootstrap/assets/img/favicon.png" />
    <script data-search-pseudo-elements defer src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js" crossorigin="anonymous"></script>
    <script src="${contextPath }/resources/jquery-3.6.1.min.js"></script>
    
    <script>

	$().ready(function() {
	
		var memberIdPermission = false;
		
		$("#memberId").keyup(function(){
			
			memberIdPermission = false;
			
		});
		
		$("#btnOverlapped").click(function(){
			
		    var memberId = $("#memberId").val();
		   
		    if (memberId == ''){
		   		alert("ID를 입력하세요");
		   		return;
		    }
		   
		    $.ajax({
		       type : "get",
		       url  : "${contextPath}/checkDuplicatedId?memberId=" + memberId,
		       success : function (data){
		          if (data == "duplicate"){
					  alert("사용할 수 있는 ID입니다.");
					  memberIdPermission = true;
		          }
		          else {
		          	  alert("이미 존재하는 ID입니다.");
		          	  memberIdPermission = false;
		          }
		       }
		    });
		    
		 });
		
		
		$("form").submit(function(){
			
			var num = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
			
			var dateBirth = $("#dateBirth").val();
			
			if (dateBirth.length != 8) {
				alert("생년월일을 양식에 맞게 입력하세요");
				$("#dateBirth").focus();
				return false;
			}
			else{
				for (var i=0 ; i<8 ; i++) {
					if (!(dateBirth.charAt(i) in num)){
						alert("생년월일을 양식에 맞게 입력하세요");
						$("#dateBirth").focus();
						return false;
					}
				}
			}
			
			var hp = $("#hp").val();
			
			
			for (var i=0 ; i<hp.length ; i++) {
				if (!(hp.charAt(i) in num)){
					alert("연락처를 양식에 맞게 입력하세요");
					$("#hp").focus();
					return false;
				}
			}
			
			
			if ($("#passwd").val() != $("#confirmPasswd").val()) {
				alert("비밀번호를 확인하세요.");
				$("#passwd").focus();
				return false;
			}
			
			if (!memberIdPermission) {
				alert("아이디 중복확인 하세요");
				$("#memberId").focus();
				return false;
			}
			
			
		});
			
		
	});
</script>
    
</head>
<body class="bg-primary">
    <div id="layoutAuthentication">
        <div id="layoutAuthentication_content">
            <main>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-lg-7">
                            <!-- Basic registration form-->
                            <div class="card shadow-lg border-0 rounded-lg mt-5">
                                <div class="card-header justify-content-center"><h3 class="font-weight-light my-4">회원가입</h3></div>
                                <div class="card-body">
                                    <!-- Registration form-->
                                    <form action="${contextPath }/postRegister" method="post">
                                        <!-- Form Row-->
                                        <div class="form-row">
                                            <div class="col-md-6">
                                                <!-- Form Group (name)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputFirstName">이름</label>
                                                    <input class="form-control" name="memberNm" type="text" />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Form Row-->
                                        <div class="form-row">
                                            <div class="col-md-6">
                                                <!-- Form Group (memberId)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputFirstName">아이디</label>
                                                    <input class="form-control" name="memberId" id="memberId" type="text" />
                                                    <button class="btn btn-outline-blue" type="button" id="btnOverlapped">중복확인</button>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Form Row    -->
                                        <div class="form-row">
                                            <div class="col-md-6">
                                                <!-- Form Group (passwd)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputPassword">비밀번호</label>
                                                    <input class="form-control" id="passwd" name="passwd" type="password"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <!-- Form Group (confirm passwd)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputConfirmPassword">비밀번호 확인</label>
                                                    <input class="form-control" id="confirmPasswd" type="password"  />
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Form Row-->
                                        <div class="form-row">
                                            <div class="col-md-6">
                                                <!-- Form Group (dateBirth)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputFirstName">생년월일</label>
                                                    <input class="form-control" name="dateBirth" id="dateBirth" type="text" placeholder="- 없이 8자 입력하세요 ex)19951214" maxlength="8"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <!-- Form Group (dateBirth)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputFirstName">주민등록번호 뒷자리</label>
                                                    <br>
                                                    <input type="text" id="sex" name="sex" style="width:25px" maxlength="1"/>
                                                    ● ● ● ● ● ●
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Form Row-->
                                        <div class="form-row">
                                            <div class="col-md-6">
                                                <!-- Form Group (hp)-->
                                                <div class="form-group">
                                                    <label class="small mb-1" for="inputFirstName">연락처</label>
                                                    <input class="form-control" name="hp" id="hp" type="text" placeholder="- 없이 입력하세요"/>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- Form Group (email address)            -->
                                        <div class="form-group">
                                            <label class="small mb-1" for="inputEmailAddress">이메일</label>
                                            <input class="form-control" id="inputEmailAddress" name="email" type="email" aria-describedby="emailHelp" />
                                        </div>
                                        <!-- Form Group (create account submit)-->
                                        <div class="form-group mt-4 mb-0"><button class="btn btn-primary btn-block" type="submit" id="submitBtn">회원가입</button></div>
                                    </form>
                                </div>
                                <div class="card-footer text-center">
                                    <div class="small"><a href="/">이미 아이디가 있다면 로그인 하러가기</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <div id="layoutAuthentication_footer">
            <footer class="footer mt-auto footer-dark">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6 small">Copyright &copy; Maeil Everyday 2023</div>
                        <div class="col-md-6 text-md-right small">
                            <a href="#!">Privacy Policy</a>
                            &middot;
                            <a href="#!">Terms &amp; Conditions</a>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="${contextPath }/resources/bootstrap/js/scripts.js"></script>
</body>
</html>

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
        <title>ME - 비밀번호 찾기</title>
        <link href="${contextPath }/resources/bootstrap/css/styles.css" rel="stylesheet" />
        <link rel="icon" type="image/x-icon" href="${contextPath }/resources/bootstrap/assets/img/favicon.png" />
        <script data-search-pseudo-elements defer src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath }/resources/jquery-3.6.1.min.js"></script>
    
    <script>
    
    function checkMember(){
    	
        var flag = false;
        
        $.ajax({
		       type : "post",
		       url  : "${contextPath}/checkForFindPasswd",
		       dataType : "json", 
		       data : {
		    	   "memberId" : $("#memberId").val(),
		    	   "hp" : $("#hp").val(),
		    	   "email" :$("#email").val() 
		       },
		       async: false,
		       success : function (data){
		          if (data == null){
					  alert("존재하지 않는 아이디 입니다.");
					  flag = false;
					  console.log(flag);
		          }
		          else {
		          	  flag = true;
		          }
		       }
		    });
        
        return flag;
    	
    }

	//$().ready(function() {
	
			
		
	//});
</script> 
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <!-- Basic forgot password form-->
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header justify-content-center"><h3 class="font-weight-light my-4">Password Recovery</h3></div>
                                    <div class="card-body">
                                        <div class="small mb-3 text-muted">필요한 정보들을 입력하세요.</div>
                                        <!-- Forgot password form-->
                                        <form id="form1" action="${contextPath }/findPasswd" method="post" onsubmit="return checkMember();">
                                            <!-- Form Group (memberId)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">아이디</label>
                                                <input class="form-control" id="memberId" name="memberId" type="text" aria-describedby="emailHelp" />
                                            </div>
                                            <!-- Form Group (hp)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">휴대폰 번호</label>
                                                <input class="form-control" id="hp" name="hp" type="text" aria-describedby="emailHelp" placeholder="-없이 입력해주세요 ex)01012345678" />
                                            </div>
                                            <!-- Form Group (email)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="inputEmailAddress">이메일</label>
                                                <input class="form-control" id="email" name="email" type="email" aria-describedby="emailHelp" placeholder="이메일을 입력하세요" />
                                            </div>
                                            <!-- Form Group (submit options)-->
                                            <div class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <a class="small" href="${contextPath }/">Return to login</a>
                                                <input class="btn btn-primary" type="submit" value="비밀번호 재설정">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small"><a href="${contextPath }/registerMember">Need an account? Sign up!</a></div>
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

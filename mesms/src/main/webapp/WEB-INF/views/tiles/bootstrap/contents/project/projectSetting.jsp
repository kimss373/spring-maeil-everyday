<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html lang="en">
	<script>

	$().ready(function() {
	
		$("form").submit(function(){
			
		    var projectNm = $("#projectNm").val();
		   
		    if (projectNm == ''){
		   		alert("프로젝트 이름을 입력하세요");
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
                                            ${projectDTO.projectNm } - 프로젝트 설정
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
                            <a class="nav-link ml-0 active" href="${contextPath }/project/projectSetting?projectCd=${projectDTO.projectCd }">프로젝트 설정</a>
                            <a class="nav-link" href="${contextPath }/project/inviteMember?projectCd=${projectDTO.projectCd }">초대하기</a>
                        </nav>
                        <hr class="mt-0 mb-4" />
                        <div class="row">
                            <div class="col-lg-8">
                                <!-- Change password card-->
                                <div class="card mb-4">
                                    <div class="card-header">프로젝트 이름</div>
                                    <div class="card-body">
                                        <form action="${contextPath }/project/projectSetting" method="post">
                                            <!-- Form Group (current password)-->
                                            <div class="form-group">
                                                <label class="small mb-1" for="currentPassword">이름</label>
                                                <input class="form-control" id="projectNm" name="projectNm" value="${projectDTO.projectNm }" type="text" placeholder="프로젝트 이름" />
                                            </div>
                                            <!-- Form Group (memberId)-->
				                           	<div class="form-group">
				                               	<label class="small mb-1" for="inputUsername">프로젝트 리더</label>
				                               	<c:choose>
					                               	<c:when test="${memberId eq projectDTO.memberId }">
							                    		<select name="memberId">
							                    	</c:when>
							                    	<c:otherwise>
							                    		<select name="memberId" disabled>
							                    	</c:otherwise>
						                    	</c:choose>
						                    		<c:forEach var="projectMemberDTO" items="${projectMemberList }">
						                    			<c:choose>
							                    			<c:when test="${projectMemberDTO.memberId eq projectDTO.memberId}">
				                            					<option selected>${projectMemberDTO.memberId }</option>
							                    			</c:when>
						                    				<c:otherwise>
				                            					<option>${projectMemberDTO.memberId }</option>
						                    				</c:otherwise>
						                    			</c:choose>
				                          			</c:forEach>
						                    	</select>
				                           </div>
                                            <input type="hidden" name="projectCd" value="${projectDTO.projectCd }">
                                            <button class="btn btn-primary" type="submit">저장</button>
                                        </form>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="col-lg-4">
                                <!-- Two factor authentication card-->
                                <div class="card mb-4">
                                    <div class="card-header">프로젝트 세부사항 설정</div>
                                    <div class="card-body">
                                        <p>프로젝트 설정에서 언제든지 이름 및 세부사항을 변경할 수 있습니다.</p>
                                        <p>초대하기 탭에서 입장코드를 확인할 수 있습니다. 프로젝트 팀원에게 알려주세요.<br>
                                        &nbsp; - 참가하기에 입장코드를 입력하면 프로젝트에 참여할 수 있습니다.
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath }/resources/jquery-3.6.1.min.js"></script>
<script>
	$().ready(function() {
		
			
		opener.parent.location.reload();
			
		
	});
</script>
</head>
<body onLoad="setTimeout(window.close, 1000)">
	성공, 1초뒤 닫힘
</body>
</html>
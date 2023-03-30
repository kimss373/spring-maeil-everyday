<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title><tiles:insertAttribute name="title" /></title>
        <link href="${contextPath }/resources/bootstrap/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.datatables.net/1.10.22/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <link href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" rel="stylesheet" crossorigin="anonymous" />
        <link rel="icon" type="image/x-icon" href="${contextPath }/resources/bootstrap/assets/img/favicon.png" />
        <script data-search-pseudo-elements defer src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/feather-icons/4.28.0/feather.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath }/resources/jquery-3.6.1.min.js"></script>
        <script>
        	$(document).ready(function(){
        		
        		
        		var memberId = '<%=(String)session.getAttribute("memberId")%>';
        		
        		if(memberId == 'null') {
        			alert("로그인을 진행해주세요.");
        			location.href="${contextPath}/";
        			
        		}
        		
        	});
        </script>
    </head>
    <body class="nav-fixed">
        <!-- header Begin -->
        <tiles:insertAttribute name="header" />
        <!-- header End -->
        <div id="layoutSidenav">
        	<div id="layoutSidenav_nav">
            <!-- sidebar Begin -->
            <tiles:insertAttribute name="side" />
            <!-- sidebar End -->
            </div>
            <div id="layoutSidenav_content">
            <!-- content Begin -->
            <tiles:insertAttribute name="content" />
            <!-- content End -->
            <!-- footer Begin -->
            <tiles:insertAttribute name="footer" />
            <!-- footer End -->
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath }/resources/bootstrap/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath }/resources/bootstrap/assets/demo/chart-area-demo.js"></script>
        <script src="${contextPath }/resources/bootstrap/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.22/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath }/resources/bootstrap/assets/demo/datatables-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js" crossorigin="anonymous"></script>
        <script src="${contextPath }/resources/bootstrap/assets/demo/date-range-picker-demo.js"></script>
    </body>
</html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.application.mesms.schedule.dto.ScheduleDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
	List<ScheduleDTO> list = (ArrayList<ScheduleDTO>)request.getAttribute("scheduleList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src="${contextPath }/resources/jquery-3.6.1.min.js"></script>
<script src='${contextPath }/resources/fullcalendar-6.1.5/dist/index.global.js'></script>
<script>

	function onSelectEvent(event) {
		var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		var openUrl = "/mySchedule/schedulePopup";
		window.open(openUrl, 'pop', popOption);
	}

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
   	customButtons: { 

	    myCustomButton: { 
	
	        text: '일정입력', 
	
	        click: function(event) { 
				
	          onSelectEvent(event); 
	
	        } 
	
	     } 

      }, 
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'myCustomButton,dayGridMonth,timeGridWeek,timeGridDay,listMonth'
      },
      initialDate: '2023-04-02',
      locale : "ko",
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      selectable: true,
      events: [
    	  <%
    	  	for (int i = 0 ; i < list.size() ; i++) {
    	  		ScheduleDTO dto = (ScheduleDTO)list.get(i);
    	  %>
	    	  {
	    		  title : '<%= dto.getTitle() %>',
	    		  start : '<%= dto.getStartDt() %>',
	    		  end : '<%= dto.getEndDt() %>',
	    		  scheduleCd : '<%= dto.getScheduleCd() %>',
	    		  memberId : '<%= dto.getMemberId() %>',
	    		  memo : '<%= dto.getMemo() %>'
	    	  },
    	  <%
    	  	}
    	  %>
        {
          title: 'Business Lunch',
          start: '2023-01-03T13:00:00',
          constraint: 'businessHours'
        },
        {
          title: 'Meeting',
          start: '2023-01-13T11:00:00',
          constraint: 'availableForMeeting', // defined below
          color: '#257e4a'
        },
        {
          title: 'Conference',
          start: '2023-01-18',
          end: '2023-01-20'
        },
        {
			id: 1,
			memberId: 'kimss373',
            title: 'Conferencez',
            start: '2023-01-18',
            end: '2023-01-20'
          },
        {
          title: 'Party',
          start: '2023-01-29T20:00:00'
        },

        // areas where "Meeting" must be dropped
        {
          groupId: 'availableForMeeting',
          start: '2023-01-11T10:00:00',
          end: '2023-01-11T16:00:00',
          display: 'background'
        },
        {
          groupId: 'availableForMeeting',
          start: '2023-01-13T10:00:00',
          end: '2023-01-13T16:00:00',
          display: 'background'
        },

        // red areas where no events can be dropped
        {
          start: '2023-01-24',
          end: '2023-01-28',
          overlap: false,
          display: 'background',
          color: '#ff9f89'
        },
        {
          start: '2023-01-06',
          end: '2023-01-08',
          overlap: false,
          display: 'background',
          color: '#ff9f89'
        }
      ],
      eventClick:function(event) {
    	  
      	 // var popOption = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
  		 // var openUrl = "/myCalendar/scheduleDetailPopup?";
  		 // window.open(openUrl, 'pop', popOption);
    	  console.log(event.event.extendedProps.memberId);
    	  console.log(event.event.extendedProps.scheduleCd);
    	  console.log(event.event.start);
    	  var startDay = event.event.start;
    	  var day = startDay.getDate();
    	  if (day < 10) day = '0' + day;
    	  var endDay = event.event.end;
    	  var sd = startDay.getFullYear() + '-' + (Number(startDay.getMonth())+1) + '-' + day;
          console.log(sd);
      }
    });

    calendar.render();
  });

</script>
<style>

  body {
    margin: 40px 10px;
    padding: 0;
    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
    font-size: 14px;
  }

  #calendar {
    max-width: 1100px;
    margin: 0 auto;
  }
  
  .add-button{
  	position: absolute;
  	top : 1px;
  	right: 230px;
  	background: #2C3E50;
  	border: 0;
  	color: white;
  	height: 35px;
  	border-radius: 3px;
  	width: 157px;
  }

</style>
</head>
<body>

<header class="page-header page-header-compact page-header-light border-bottom bg-white mb-4">
                        <div class="container-fluid">
                            <div class="page-header-content">
                                <div class="row align-items-center justify-content-between pt-3">
                                    <div class="col-auto mb-3">
                                        <h1 class="page-header-title">
                                            <div class="page-header-icon"><i data-feather="user"></i></div>
                                            스케쥴
                                        </h1>
                                    </div>
                                    <div class="container mt-4">
									  <div id='calendar' style="position : relative;">
									  	<div>
									  		<button class="add-button" type="button" onclick="click_add();">일정 추가</button>
									  	</div>
									  </div>
									</div>
                                </div>
                            </div>
                        </div>
                    </header>



</body>
</html>
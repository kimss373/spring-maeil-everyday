package com.application.mesms.schedule.service;

import java.util.List;

import com.application.mesms.schedule.dto.ScheduleDTO;

public interface ScheduleService {
	
	public boolean addSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public List<ScheduleDTO> getScheduleListByMemberId(String memberId) throws Exception;

}

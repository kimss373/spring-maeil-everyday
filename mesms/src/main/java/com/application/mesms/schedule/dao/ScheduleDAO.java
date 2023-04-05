package com.application.mesms.schedule.dao;

import java.util.List;

import com.application.mesms.schedule.dto.ScheduleDTO;

public interface ScheduleDAO {
	
	public void insertNewSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public List<ScheduleDTO> selectListScheduleByMemberId(String memberId) throws Exception;

}

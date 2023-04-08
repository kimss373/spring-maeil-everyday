package com.application.mesms.schedule.dao;

import java.util.List;

import com.application.mesms.schedule.dto.ScheduleDTO;

public interface ScheduleDAO {
	
	public void insertNewSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public List<ScheduleDTO> selectListScheduleByMemberId(String memberId) throws Exception;
	public ScheduleDTO selectOneScheduleDTOByScheduleCd(long scheduleCd) throws Exception;
	public void updateSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public void deleteSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public List<ScheduleDTO> selectListTodaySchedule(String memberId) throws Exception;
	public List<ScheduleDTO> selectListTomorrowSchedule(String memberId) throws Exception;
	
}

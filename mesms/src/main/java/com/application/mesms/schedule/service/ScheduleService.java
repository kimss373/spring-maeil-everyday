package com.application.mesms.schedule.service;

import java.util.List;

import com.application.mesms.schedule.dto.ScheduleDTO;

public interface ScheduleService {
	
	public boolean addSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public List<ScheduleDTO> getScheduleListByMemberId(String memberId) throws Exception;
	public ScheduleDTO getScheduleDTOByScheduleCd(long scheduleCd) throws Exception;
	public boolean modifySchedule(ScheduleDTO scheduleDTO) throws Exception;
	public void deleteSchedule(ScheduleDTO scheduleDTO) throws Exception;
	public List<ScheduleDTO> getTodayScheduleList(String memberId) throws Exception;
	public List<ScheduleDTO> getTomorrowScheduleList(String memberId) throws Exception;

}

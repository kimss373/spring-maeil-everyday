package com.application.mesms.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.mesms.schedule.dao.ScheduleDAO;
import com.application.mesms.schedule.dto.ScheduleDTO;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDAO scheduleDAO;

	@Override
	public boolean addSchedule(ScheduleDTO scheduleDTO) throws Exception {
		
		if (scheduleDTO.getEndDt().compareTo(scheduleDTO.getStartDt()) < 0) {
			return false;
		} else {
			scheduleDAO.insertNewSchedule(scheduleDTO);
			return true;
		}
		
		
	}

	@Override
	public List<ScheduleDTO> getScheduleListByMemberId(String memberId) throws Exception {
		return scheduleDAO.selectListScheduleByMemberId(memberId);
	}

	@Override
	public ScheduleDTO getScheduleDTOByScheduleCd(long scheduleCd) throws Exception {
		return scheduleDAO.selectOneScheduleDTOByScheduleCd(scheduleCd);
	}
	
	@Override
	public boolean modifySchedule(ScheduleDTO scheduleDTO) throws Exception {
		
		if (scheduleDTO.getEndDt().compareTo(scheduleDTO.getStartDt()) < 0) {
			return false;
		} else {
			scheduleDAO.updateSchedule(scheduleDTO);
			return true;
		}
		
		
	}

	@Override
	public void deleteSchedule(ScheduleDTO scheduleDTO) throws Exception {
		scheduleDAO.deleteSchedule(scheduleDTO);
	}

	@Override
	public List<ScheduleDTO> getTodayScheduleList(String memberId) throws Exception {
		return scheduleDAO.selectListTodaySchedule(memberId);
	}

	@Override
	public List<ScheduleDTO> getTomorrowScheduleList(String memberId) throws Exception {
		return scheduleDAO.selectListTomorrowSchedule(memberId);
	}
	
}

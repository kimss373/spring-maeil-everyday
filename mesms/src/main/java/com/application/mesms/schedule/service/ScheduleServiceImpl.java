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
	
}

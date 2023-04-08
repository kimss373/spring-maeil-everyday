package com.application.mesms.schedule.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.mesms.schedule.dto.ScheduleDTO;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertNewSchedule(ScheduleDTO scheduleDTO) throws Exception {
		sqlSession.insert("schedule.insertNewSchedule", scheduleDTO);
	}

	@Override
	public List<ScheduleDTO> selectListScheduleByMemberId(String memberId) throws Exception {
		return sqlSession.selectList("schedule.selectListScheduleByMemberId", memberId);
	}

	@Override
	public ScheduleDTO selectOneScheduleDTOByScheduleCd(long scheduleCd) throws Exception {
		return sqlSession.selectOne("schedule.ScheduleDTOByScheduleCd", scheduleCd);
	}

	@Override
	public void updateSchedule(ScheduleDTO scheduleDTO) throws Exception {
		sqlSession.update("schedule.updateSchedule", scheduleDTO);
	}

	@Override
	public void deleteSchedule(ScheduleDTO scheduleDTO) throws Exception {
		sqlSession.delete("schedule.deleteSchedule", scheduleDTO);
	}

	@Override
	public List<ScheduleDTO> selectListTodaySchedule(String memberId) throws Exception {
		return sqlSession.selectList("schedule.selectListTodaySchedule", memberId);
	}

	@Override
	public List<ScheduleDTO> selectListTomorrowSchedule(String memberId) throws Exception {
		return sqlSession.selectList("schedule.selectListTomorrowSchedule", memberId);
	}
	
}

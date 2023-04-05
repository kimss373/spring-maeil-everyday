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
	
}

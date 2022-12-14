package com.movie.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.movie.vo.ScheduleVo;

public interface ScheduleService {
	public boolean findOverlapSchedule(ScheduleVo scheduleVo);
	public List<ScheduleVo> findScheduleByDetail(ScheduleVo scheduleVo);
	public List<String> findScheduleDate(ScheduleVo scheduleVo);
	public int insertSchedule(ScheduleVo scheduleVo);
	public int deleteSchedule(ScheduleVo scheduleVo);
	public List<String> getCinemaLocationByMovieId(int movie_id);
	public List<String> getCinemaNameByInfo(Map info);
	public List<String> getScheduleDateByInfo(Map info);
	public List<ScheduleVo> getScheduleTimeAndTheater(Map info);
}

package com.movie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.dao.MovieDao;
import com.movie.dao.PickDao;
import com.movie.dao.ScheduleDao;
import com.movie.dao.SeatDao;
import com.movie.vo.MovieVo;
import com.movie.vo.ScheduleVo;
import com.movie.vo.SeatVo;

import io.jsonwebtoken.lang.Collections;

@Service
public class TicketingService {
	@Autowired
	MovieDao movieDao;
	@Autowired
	ScheduleDao scheduleDao;
	@Autowired
	SeatDao seatDao;
	@Autowired
	PickDao pickDao;
	
	public List<MovieVo> getMovieList(){
		return movieDao.getMovieInfo();
	}
	
	public List<Map<String,String>> getCinemaLocationList(int movie_id){
		List<String> locationStrList = scheduleDao.getCinemaLocationByMovieId(movie_id);
		List<Map<String, String>> output = new ArrayList<Map<String, String>>();
		for(int i = 0; i < locationStrList.size(); i++) {
			HashMap<String, String> cinemaLocationData = new HashMap<>();
			cinemaLocationData.put("cinema_location", locationStrList.get(i));
			output.add(cinemaLocationData);
		}
		
		return output;
	}
	
	public List<Map<String, String>> getCinemaNameList(int movie_id, String cinema_location){
		Map input = new HashMap<>();
		input.put("movie_id", movie_id);
		input.put("cinema_location", cinema_location);
		
		List<String> cinemaNameStrList = scheduleDao.getCinemaNameByInfo(input);
		List<Map<String, String>> output = new ArrayList<Map<String, String>>();
		for(int i = 0; i < cinemaNameStrList.size(); i++) {
			HashMap<String, String> cinemaNameData = new HashMap<>();
			cinemaNameData.put("cinema_name", cinemaNameStrList.get(i));
			output.add(cinemaNameData);
		}
		
		return output;
	}
	
	public List<Map<String, String>> getScheduleDateList(int movie_id, String cinema_name){
		Map<String, Object> input = new HashMap<>();
		input.put("movie_id", movie_id);
		input.put("cinema_name", cinema_name);
		
		List<String> scheduleDateStrList = scheduleDao.getScheduleDateByInfo(input);
		List<Map<String, String>> output = new ArrayList<Map<String, String>>();
		for(int i = 0; i < scheduleDateStrList.size(); i++) {
			HashMap<String, String> scheduleDateData = new HashMap<>();
			scheduleDateData.put("cinema_name", scheduleDateStrList.get(i));
			output.add(scheduleDateData);
		}
		
		return output;
	}
	
	public List<ScheduleVo> getScheduleTimeAndTheater(int movie_id, String cinema_name, String schedule_date) {
		Map input = new HashMap<>();
		input.put("movie_id", movie_id);
		input.put("cinema_name", cinema_name);
		input.put("schedule_date", schedule_date);
		
		return scheduleDao.getScheduleTimeAndTheater(input);
	}
	
	public List<String> getSeatInfo(Long schedule_id){
		List<SeatVo> seatList = seatDao.getSeletedSeat(schedule_id);
		List<String> blockSeatList = new ArrayList();
		
		for(int i = 0; i < seatList.size(); i++) {
			String[] seatStrArr = (seatList.get(i).getSeat_name()).split(",");
			for(int j = 0; j < seatStrArr.length; j++) {
				blockSeatList.add(seatStrArr[j]);
			}
		}
		return blockSeatList;
	}
	
	public Long insertSeat(Map insert){
		Long schedule_id = Long.parseLong(insert.get("schedule_id").toString());
		String seat_name = insert.get("seat_name").toString();
		int result = seatDao.insertSeat(schedule_id, seat_name);
		
		if(result == 1) {
			List<Long> seat_id_list = seatDao.getSeatIdBySchedule(schedule_id);
			Long seat_id = seat_id_list.get(seat_id_list.size() - 1);
			return seat_id;
		}else {
			return -1L;
		}
	}
	
	public int insertPick(Map insert) {
		Long seat_id = Long.parseLong(insert.get("seat_id").toString());
		Long member_id = Long.parseLong(insert.get("member_id").toString());
		Long schedule_id = Long.parseLong(insert.get("schedule_id").toString());
		return pickDao.insertPick(seat_id, member_id, schedule_id);
	}
}
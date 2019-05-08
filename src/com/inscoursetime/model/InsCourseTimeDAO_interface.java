package com.inscoursetime.model;

import java.sql.Connection;
import java.util.List;

public interface InsCourseTimeDAO_interface {
	
	public void insert(InsCourseTimeVO insCourseTimeVO);
	public void update(InsCourseTimeVO insCourseTimeVO);
	public void delete(String inscTimeId);
	public void delete(String inscTimeId,Connection con);
	public InsCourseTimeVO findByPrimaryKey(String inscTimeId);
	public List<InsCourseTimeVO> findByKey(String xxxId); 
	public List<InsCourseTimeVO> findDate(java.sql.Date startTime,java.sql.Date endTime,String inscId); 
	public List<InsCourseTimeVO> findDate(String startTime,String endTime,String inscId); 
	public InsCourseTimeVO findDateMinute(String startTime,String endTime,String inscId); 
	public List<InsCourseTimeVO> getAll();

}

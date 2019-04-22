package com.inscourse.model;

import java.util.List;

import com.coursetransfer.model.CourseTransferVO;

public interface InsCourseDAO_interface {
	public void insert(InsCourseVO insCourseVO);
	public void update(InsCourseVO insCourseVO);
	public void delete(String inscId);
	public InsCourseVO findByPrimaryKey(String inscId);
	
	public void updateStatus(InsCourseVO insCourseVO);
	public List<InsCourseVO> findByCourse(String courseId);
	
	public List<InsCourseVO> findClassName(String str);
	public List<InsCourseVO> getAll(String i);
	
}

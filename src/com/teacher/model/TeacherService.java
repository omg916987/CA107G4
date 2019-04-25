package com.teacher.model;

import java.util.List;

public class TeacherService {
	private TeacherDAO_interface dao;
	
	public TeacherService(){
		dao = new TeacherJDBCDAO();
	}
	
	public void addTeacher(String memId, Integer teacherStatus, String teacherCity, String teacherEdu,byte[] diplomaImg, String teacherText) {
		TeacherVO teacherVO = new TeacherVO();
		teacherVO.setMemId(memId);
		teacherVO.setTeacherStatus(teacherStatus);
		teacherVO.setTeacherCity(teacherCity);
		teacherVO.setTeacherEdu(teacherEdu);
		teacherVO.setDiplomaImg(diplomaImg);
		teacherVO.setTeacherText(teacherText);
		dao.insert(teacherVO);
	}
	
	public TeacherVO findOneById(String teacherId) {
		return dao.findOneById(teacherId);
	}
	public List<TeacherVO> getAll(){
		return dao.getAll();
	}
//	public TeacherVO findByStatus(String memId) {
//		return dao.findByStatus(memId);
//	}
}

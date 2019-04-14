package com.joingroup.model;

import java.util.List;




public interface JoinGroupDAO_interface {
//	 public void insert(JoinGroupVO joinGroupVO);//�s�W
//	 public void update(JoinGroupVO joinGroupVO);
	 public List<JoinGroupVO> getAll();
//	 public void delete(String memId);
     public JoinGroupVO findByPrimaryKey(String memId);
}

package com.joingroup.model;

import java.util.List;




public interface JoinGroupDAO_interface {
	


	 public List<JoinGroupVO> getAll();
//	 public void delete(String memId);
     public JoinGroupVO findByPrimaryKey(String memId);
	public void insert(JoinGroupVO joinGroupVO);
//	public void update(JoinGroupVO joinGroupVO);
	
	public void delete(String memId, String teamId);
}

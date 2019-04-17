package com.team.model;

import java.util.List;
import java.util.Map;





public interface TeamDAO_interface {
	public void insert(TeamVO teamVO);
	public void update(TeamVO teamVO);

//	public void delete(String teamId);
	public TeamVO findByPrimaryKey(String teamId);
	public List<TeamVO> getAll();
	
//	public List<TeamVO> getAll(Map<String, String[]> map);
	
	
}

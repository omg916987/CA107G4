package com.team.model;

import java.util.List;



public interface TeamDAO_interface {
	public void insert(TeamVO teamVO);
	public void update(TeamVO teamVO);

//	public void delete(String teamId);
	public TeamVO findByPrimaryKey(String teamId);
//	public List<TeamVO> getAll();
	
}

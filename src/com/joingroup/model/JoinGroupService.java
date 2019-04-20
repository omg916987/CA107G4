package com.joingroup.model;

import java.util.List;





public class JoinGroupService {

	private JoinGroupDAO_interface dao;

	public JoinGroupService() {
		dao = new JoinGroupJDBCDAO();
	}

	public JoinGroupVO addJoinGroup(String memId, String teamId) {

		JoinGroupVO joinGroupVO = new JoinGroupVO();

		joinGroupVO.setMemId(memId);
		joinGroupVO.setTeamId(teamId);
		dao.insert(joinGroupVO);
		System.out.println("8857");

		return joinGroupVO;
	}

//	public JoinGroupVO updateJoinGroup(String memId, String teamId) {

//		JoinGroupVO joinGroupVO = new JoinGroupVO();
//
//		joinGroupVO.setMemId(memId);
//		joinGroupVO.setTeamId(teamId);
//		dao.update(joinGroupVO);
//
//		return joinGroupVO;
//
//	}

	public void deleteJoinGroup(String memId,String teamId) {
		dao.delete(memId, teamId);
		
	}

	public JoinGroupVO getOneJoinGroup(String memId) {
		return dao.findByPrimaryKey(memId);
	}

	public List<JoinGroupVO> getAll() {
		return dao.getAll();
	}

}

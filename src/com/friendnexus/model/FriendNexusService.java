package com.friendnexus.model;

import java.util.List;

public class FriendNexusService {

	private FriendNexusDAO_interface dao;

	public FriendNexusService() {
		dao = new FriendNexusJDBCDAO();
	}

	public FriendNexusVO addfriendNexus(String memId, String friendAcc, Integer friendstatus) {

		FriendNexusVO friendNexusVO = new FriendNexusVO();

		friendNexusVO.setMemId(memId);
		friendNexusVO.setFriendAcc(friendAcc);
		friendNexusVO.setFriendstatus(friendstatus);
		dao.insert(friendNexusVO);

		return friendNexusVO;
	}

	public FriendNexusVO updatefriendNexus(String memId, String friendAcc, Integer friendstatus) {

		FriendNexusVO friendNexusVO = new FriendNexusVO();

		friendNexusVO.setMemId(memId);
		friendNexusVO.setFriendAcc(friendAcc);
		friendNexusVO.setFriendstatus(friendstatus);
		dao.update(friendNexusVO);

		return friendNexusVO;
	}

//	public void deletefriendNexusp(String memId) {
//		dao.delete(memId);
//	}

	public FriendNexusVO getOneFriendNexus(String memId) {
		return dao.findByPrimaryKey(memId);
	}

	public List<FriendNexusVO> getAll() {
		return dao.getAll();
	}
}

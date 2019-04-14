package com.friendchathistory.model;

import java.util.List;



public class FriendChatHistoryService {
	private FriendChatHistoryDAO_interface dao;

	public FriendChatHistoryService() {
		dao = new FriendChatHistoryJDBCDAO();
	}

//	public FriendChatHistoryVO addFriendChatHistory(String friendAcc, String memId, String chatrecord) {
//
//		FriendChatHistoryVO friendChatHistoryVO = new FriendChatHistoryVO();
//
//		friendChatHistoryVO.setFriendAcc(friendAcc);
//		friendChatHistoryVO.setMemId(memId);
//		friendChatHistoryVO.setChatrecord(chatrecord);
//		dao.insert(friendChatHistoryVO);
//
//		return friendChatHistoryVO;
//	}

	
//	public FriendChatHistoryVO updateFriendChatHistory(String friendAcc, 
//			String memId, String chatrecord) {
//
//		FriendChatHistoryVO friendChatHistoryVO = new FriendChatHistoryVO();
//
//		friendChatHistoryVO.setFriendAcc(friendAcc);
//		friendChatHistoryVO.setMemId(memId);
//		friendChatHistoryVO.setChatrecord(chatrecord);
//		dao.update(friendChatHistoryVO);
//
//		return friendChatHistoryVO;
//	}

//	public void deleteFriendChatHistory(String friendAcc) {
//		dao.delete(friendAcc);
//	}

	public FriendChatHistoryVO getOneFriendChatHistory(String friendAcc) {
		return dao.findByPrimaryKey(friendAcc);
	}

	public List<FriendChatHistoryVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
}

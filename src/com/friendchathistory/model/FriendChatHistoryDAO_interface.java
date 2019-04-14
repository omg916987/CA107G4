package com.friendchathistory.model;

import java.util.List;



public interface FriendChatHistoryDAO_interface {
//	public void insert(FriendChatHistoryVO friendChatHistoryVO);
//	public void update(FriendChatHistoryVO friendChatHistoryVO);
	public List< FriendChatHistoryVO> getAll();
//	public void delete(String friendChatHistoryVO);
    public FriendChatHistoryVO findByPrimaryKey(String friendAcc);
}

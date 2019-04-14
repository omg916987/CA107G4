package com.friendchathistory.model;

import java.sql.Date;

public class FriendChatHistoryVO implements java.io.Serializable{
	private String friendAcc;
	private String memId;
	private String chatrecord;
	
	public String getFriendAcc() {
		return friendAcc;
	}
	public void setFriendAcc(String friendAcc) {
		this.friendAcc = friendAcc;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getChatrecord() {
		return chatrecord;
	}
	public void setChatrecord(String chatrecord) {
		this.chatrecord = chatrecord;
	}
	
}



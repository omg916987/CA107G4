package com.scorpionchatrecord.model;

import java.util.List;



public interface ScorpionChatRecordDAO_interface {

	
	public void insert(ScorpionChatRecordVO scorpionChatRecordVO);
//	public void update(ScorpionChatRecordVO scorpionChatRecordVO);
//	public void delete(String teamId);
	public List<ScorpionChatRecordVO> getAll();
	ScorpionChatRecordVO findByPrimaryKey(String teamId);
    
    
	
}

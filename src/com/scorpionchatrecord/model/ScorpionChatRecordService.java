package com.scorpionchatrecord.model;

import java.util.List;



public class ScorpionChatRecordService {

	private ScorpionChatRecordDAO_interface dao;

	public ScorpionChatRecordService() {
		dao = new ScorpionChatRecordJDBCDAO();
	}

	public ScorpionChatRecordVO addScorpionChatRecord(String teamId,
			String groChatRecord) {
		
		ScorpionChatRecordVO scorpionChatRecordVO = new ScorpionChatRecordVO();
		
		
		scorpionChatRecordVO.setTeamId(teamId);
		scorpionChatRecordVO.setGroChatRecord(groChatRecord);
		dao.insert(scorpionChatRecordVO);
		
		return scorpionChatRecordVO;
		
		
	}

	
//	public ScorpionChatRecordVO updateScorpionChatRecord(String teamcNo, String teamId,
//			String groChatRecord) {
//		
//		ScorpionChatRecordVO scorpionChatRecordVO = new ScorpionChatRecordVO();
//		
//		scorpionChatRecordVO.setTeamcNo(teamcNo);
//		scorpionChatRecordVO.setTeamId(teamId);
//		scorpionChatRecordVO.setGroChatRecord(groChatRecord);
//		dao.update(scorpionChatRecordVO);
//		
//		return scorpionChatRecordVO;
//		
//	
//	}
//	public void deleteScorpionChatRecord(String teamId) {
//		dao.delete(teamId);
//	}
	
	
	public ScorpionChatRecordVO getOneScorpionChatRecord(String teamcNo) {
		return dao.findByPrimaryKey(teamcNo);
	}

	public List<ScorpionChatRecordVO> getAll() {
		return dao.getAll();
	}
	
	
	
	
	
	
	
}

package com.friendchathistory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class FriendChatHistoryJDBCDAO implements FriendChatHistoryDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WESHARE";
	String passwd = "123456";

	private static final String GET_ONE_STMT = "SELECT * FROM FriendChatHistory where friendAcc=?";
	private static final String GET_ALL_STMT = "SELECT friendAcc,memId,chatrecord FROM FriendChatHistory order by friendAcc";

	
		

	@Override
	public FriendChatHistoryVO findByPrimaryKey(String friendAcc) {

		FriendChatHistoryVO FriendChatHistoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, friendAcc);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				FriendChatHistoryVO = new FriendChatHistoryVO();
				FriendChatHistoryVO.setFriendAcc(rs.getString("friendAcc"));
				FriendChatHistoryVO.setMemId(rs.getString("memId"));
				FriendChatHistoryVO.setChatrecord(rs.getString("chatrecord"));

			}

			// Handle any driver errors
		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return FriendChatHistoryVO;
	}

	@Override
	public List<FriendChatHistoryVO> getAll() {
		List<FriendChatHistoryVO> list = new ArrayList<FriendChatHistoryVO>();
		FriendChatHistoryVO fiendChatHistoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// scorpionChatRecordVO �]�٬� Domain objects
				fiendChatHistoryVO = new FriendChatHistoryVO();
				fiendChatHistoryVO.setFriendAcc(rs.getString("friendAcc"));
				fiendChatHistoryVO.setMemId(rs.getString("memId"));
				fiendChatHistoryVO.setChatrecord(rs.getString("friendstatus"));
				list.add(fiendChatHistoryVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		FriendChatHistoryJDBCDAO dao = new FriendChatHistoryJDBCDAO();

		// 新增
//		FriendChatHistoryVO FriendChatHistoryVO1 = new FriendChatHistoryVO();
//		FriendChatHistoryVO1.setMemId("weshare01");
//		FriendChatHistoryVO1.setFriendAcc("weshare02");
//		FriendChatHistoryVO1.setFriendstatus(1);
//
//		dao.insert(FriendChatHistoryVO1);

		// 查詢
		FriendChatHistoryVO FriendChatHistoryVO2 = dao.findByPrimaryKey("weshare02");
		System.out.print(FriendChatHistoryVO2.getFriendAcc() + ",");
		System.out.println(FriendChatHistoryVO2.getMemId() + ",");
		System.out.println(FriendChatHistoryVO2.getChatrecord());

		System.out.println("---------------------");

		// 查全部
		List<FriendChatHistoryVO> list = dao.getAll();
		for (FriendChatHistoryVO FriendChatHistoryVO3 : list) {
			System.out.print(FriendChatHistoryVO3.getFriendAcc() + ",");
			System.out.print(FriendChatHistoryVO3.getMemId() + ",");
			System.out.println(FriendChatHistoryVO3.getChatrecord());
			System.out.println();

		}

	}

	}
	
	
	



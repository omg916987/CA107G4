package com.scorpionchatrecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScorpionChatRecordJDBCDAO implements ScorpionChatRecordDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WESHARE";
	String passwd = "123456";

	private static final String INSERT_ScorpionChatRecord = "INSERT INTO ScorpionChatRecord (teamcNo,teamId,groChatRecord) VALUES (('SC'||LPAD(to_char(SCORPIONCHATRECORD_SEQ.NEXTVAL), 5, '0')), ?, ?)";
	
	private static final String GET_ONE_STMT = "SELECT * FROM ScorpionChatRecord where teamcNo=?";
	private static final String GET_ALL_STMT = "SELECT teamcNo,teamId,groChatRecord FROM ScorpionChatRecord order by teamcNo";
//	private static final String UPDATE = 
//			"UPDATE ScorpionChatRecord set TeamId=? where teamcNo = ?";
	@Override
	public void insert(ScorpionChatRecordVO scorpionChatRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_ScorpionChatRecord);

			pstmt.setString(1, scorpionChatRecordVO.getTeamId());
			pstmt.setString(2, scorpionChatRecordVO.getGroChatRecord());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public ScorpionChatRecordVO findByPrimaryKey(String TemaID) {

		ScorpionChatRecordVO ScorpionChatRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, TemaID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				ScorpionChatRecordVO = new ScorpionChatRecordVO();
				ScorpionChatRecordVO.setTeamcNo(rs.getString("teamcNO"));
				ScorpionChatRecordVO.setTeamId(rs.getString("teamId"));
				ScorpionChatRecordVO.setGroChatRecord(rs.getString("groChatRecord"));

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
		return ScorpionChatRecordVO;
	}

	@Override
	public List<ScorpionChatRecordVO> getAll() {
		List<ScorpionChatRecordVO> list = new ArrayList<ScorpionChatRecordVO>();
		ScorpionChatRecordVO scorpionChatRecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// scorpionChatRecordVO 也稱為 Domain objects
				scorpionChatRecordVO = new ScorpionChatRecordVO();
				scorpionChatRecordVO.setTeamcNo(rs.getString("teamcNo"));
				scorpionChatRecordVO.setTeamId(rs.getString("teamId"));
				scorpionChatRecordVO.setGroChatRecord(rs.getString("groChatRecord"));
				list.add(scorpionChatRecordVO); // Store the row in the list
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
		ScorpionChatRecordJDBCDAO dao = new ScorpionChatRecordJDBCDAO();

		// 新增
		ScorpionChatRecordVO ScorpionChatRecordVO1 = new ScorpionChatRecordVO();
		ScorpionChatRecordVO1.setTeamcNo("SC00001");
		ScorpionChatRecordVO1.setTeamId("TM00001");
		ScorpionChatRecordVO1.setGroChatRecord("HELLO");

		dao.insert(ScorpionChatRecordVO1);

		// 查詢
		ScorpionChatRecordVO ScorpionChatRecordVO2 = dao.findByPrimaryKey("SC00003");
		System.out.print(ScorpionChatRecordVO2.getTeamcNo() + ",");
		System.out.print(ScorpionChatRecordVO2.getTeamId() + ",");
		System.out.println(ScorpionChatRecordVO2.getGroChatRecord());

		System.out.println("---------------------");

		// 查詢
		List<ScorpionChatRecordVO> list = dao.getAll();
		for (ScorpionChatRecordVO ScorpionChatRecordVO3 : list) {
			System.out.print(ScorpionChatRecordVO3.getTeamcNo() + ",");
			System.out.println(ScorpionChatRecordVO3.getTeamId() + ",");
			System.out.print(ScorpionChatRecordVO3.getGroChatRecord());
			System.out.println();

		}

	}

}

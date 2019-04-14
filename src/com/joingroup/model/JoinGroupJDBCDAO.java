package com.joingroup.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinGroupJDBCDAO implements JoinGroupDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WESHARE";
	String passwd = "123456";

	private static final String GET_ONE_STMT = "SELECT * FROM JOINGROUP where MEMID=?";
	private static final String GET_ALL_STMT = "SELECT memId,teamId FROM joinGroup order by memId";

	@Override
	public JoinGroupVO findByPrimaryKey(String memId) {

		JoinGroupVO joinGroupVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, memId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				joinGroupVO = new JoinGroupVO();
				joinGroupVO.setMemId(rs.getString("memId"));
				joinGroupVO.setTeamId(rs.getString("teamId"));

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
		return joinGroupVO;
	}

	@Override
	public List<JoinGroupVO> getAll() {
		List<JoinGroupVO> list = new ArrayList<JoinGroupVO>();
		JoinGroupVO joinGroupVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				joinGroupVO = new JoinGroupVO();
				joinGroupVO.setMemId(rs.getString("memId"));
				joinGroupVO.setTeamId(rs.getString("teamId"));

				list.add(joinGroupVO); // Store the row in the list
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
		JoinGroupJDBCDAO dao = new JoinGroupJDBCDAO();

		JoinGroupVO JoinGroupVO1 = dao.findByPrimaryKey("weshare01");
		System.out.print(JoinGroupVO1.getMemId() + ",");
		System.out.println(JoinGroupVO1.getTeamId() + ",");

		System.out.println("---------------------");

		List<JoinGroupVO> list = dao.getAll();
		for (JoinGroupVO JoinGroupV02 : list) {
			System.out.print(JoinGroupV02.getMemId() + ",");
			System.out.println(JoinGroupV02.getTeamId());
			System.out.println();

		}
	}
}
package com.withdrawalrecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WithdrawalRecordJDBCDAO implements WithdrawalRecordDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WESHARE";
	String passwd = "123456";

	private static final String INSERT_WITHDRAWALRECORD = "INSERT INTO WithdrawalRecord (wrnum,memid,wrmoney,wrtime) VALUES (('WI'||LPAD(to_char(WITHDRAWALRECORD_seq.NEXTVAL), 5, '0')), ?, ?, ?)";

	private static final String GET_ONE_STMT = "SELECT * FROM WITHDRAWALRECORD where WRNUM=?";

	private static final String UPDATE = "UPDATE WITHDRAWALRECORD set memid=?, wrmoney=?, wrtime=? where wrnum = ?";

	@Override
	public void insert(WithdrawalRecordVO withdrawalRecordVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_WITHDRAWALRECORD);

			pstmt.setString(1, withdrawalRecordVO.getMemid());
			pstmt.setInt(2, withdrawalRecordVO.getWrmoney());
			pstmt.setDate(3, withdrawalRecordVO.getWrtime());

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
	public void update(WithdrawalRecordVO withdrawalRecordVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, withdrawalRecordVO.getMemid());
			pstmt.setInt(2, withdrawalRecordVO.getWrmoney());
			pstmt.setDate(3, withdrawalRecordVO.getWrtime());
			pstmt.setString(4, withdrawalRecordVO.getWrnum());
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
	public WithdrawalRecordVO findByPrimaryKey(String Wrnum) {

		WithdrawalRecordVO WithdrawalRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, Wrnum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// WithdrawalRecordVO 涔熹ū鐐?Domain objects
				WithdrawalRecordVO = new WithdrawalRecordVO();
				WithdrawalRecordVO.setWrnum(rs.getString("wrnum"));
				WithdrawalRecordVO.setMemid(rs.getString("memid"));
				WithdrawalRecordVO.setWrmoney(rs.getInt("wrmoney"));
				WithdrawalRecordVO.setWrtime(rs.getDate("wrtime"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return WithdrawalRecordVO;
	}



	public static void main(String[] args) {

		WithdrawalRecordJDBCDAO dao = new WithdrawalRecordJDBCDAO();

//		// 鏂板
//		WithdrawalRecordVO WithdrawalRecordVO1 = new WithdrawalRecordVO();
//		
//		WithdrawalRecordVO1.setMemid("weshare01");
//		WithdrawalRecordVO1.setwrmoney(1000);
//		WithdrawalRecordVO1.setWrtime(java.sql.Date.valueOf("2019-03-25"));
//		dao.insert(WithdrawalRecordVO1);
//
//		// 淇敼
		WithdrawalRecordVO WithdrawalRecordVO2 = new WithdrawalRecordVO();

		WithdrawalRecordVO2.setMemid("weshare04");
		WithdrawalRecordVO2.setWrmoney(16000);
		WithdrawalRecordVO2.setWrtime(java.sql.Date.valueOf("2019-03-25"));
		WithdrawalRecordVO2.setWrnum("WI00004");
		dao.update(WithdrawalRecordVO2);


//		// 鏌
		WithdrawalRecordVO WithdrawalRecordVO3 = dao.findByPrimaryKey("WI00001");
		System.out.print(WithdrawalRecordVO3.getWrnum() + ",");
		System.out.print(WithdrawalRecordVO3.getMemid() + ",");
		System.out.print(WithdrawalRecordVO3.getWrmoney() + ",");
		System.out.println(WithdrawalRecordVO3.getWrtime());
//
//		System.out.println("---------------------");

	}

	

}

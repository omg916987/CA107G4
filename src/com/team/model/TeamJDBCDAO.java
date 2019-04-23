package com.team.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamJDBCDAO implements TeamDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "WESHARE";
	String passwd = "123456";

	private static final String INSERT_TEAM = "INSERT INTO TEAM (teamId,leaderId,inscId,teamMFD,teamEXP,teamStatus) VALUES (('TM'||LPAD(to_char(Team_seq.NEXTVAL), 5, '0')), ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE TEAM set  leaderId=?, inscId=?, teamMFD=?, teamEXP=?, teamStatus=? where teamId = ?";
	private static final String GET_ONE_STMT = "SELECT * FROM TEAM where inscId=?";
	private static final String GET_ONE_STMT1 = "SELECT * FROM TEAM where teamId=?";
	private static final String GET_ALL_STMT = "SELECT teamId,leaderId,inscId,teamMFD,teamEXP,teamStatus FROM Team order by teamId";

	@Override
	public void insert(TeamVO teamVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_TEAM);

			pstmt.setString(1, teamVO.getLeaderID());
			pstmt.setString(2, teamVO.getInscID());
			pstmt.setDate(3, teamVO.getTemaMFD());
			pstmt.setDate(4, teamVO.getTeamEXP());
			pstmt.setInt(5, teamVO.getTeamStatus());

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
	public void update(TeamVO teamVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, teamVO.getLeaderID());
			pstmt.setString(2, teamVO.getInscID());
			pstmt.setDate(3, teamVO.getTemaMFD());
			pstmt.setDate(4, teamVO.getTeamEXP());
			pstmt.setInt(5, teamVO.getTeamStatus());
			pstmt.setString(6, teamVO.getTeamId());

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
	public TeamVO getOneTeam(String inscId) {

		TeamVO teamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, inscId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				teamVO = new TeamVO();
				teamVO.setTeamId(rs.getString("teamId"));
				teamVO.setLeaderID(rs.getString("leaderID"));
				teamVO.setInscID(rs.getString("inscID"));
				teamVO.setTemaMFD(rs.getDate("teamMFD"));
				teamVO.setTeamEXP(rs.getDate("teamEXP"));
				teamVO.setTeamStatus(rs.getInt("teamStatus"));

			}

		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return teamVO;
	}

	@Override
	public List<TeamVO> getAll() {
		List<TeamVO> list = new ArrayList<TeamVO>();
		TeamVO teamVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				teamVO = new TeamVO();
				teamVO.setTeamId(rs.getString("teamId"));
				teamVO.setLeaderID(rs.getString("leaderID"));
				teamVO.setInscID(rs.getString("inscID"));
				teamVO.setTemaMFD(rs.getDate("teamMFD"));
				teamVO.setTeamEXP(rs.getDate("teamEXP"));
				teamVO.setTeamStatus(rs.getInt("teamStatus"));
				list.add(teamVO);

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
	@Override
	public TeamVO findByPrimaryKey1(String teamId) {

		TeamVO teamVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT1);
			pstmt.setString(1, teamId);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				teamVO = new TeamVO();
				teamVO.setTeamId(rs.getString("teamId"));
				teamVO.setLeaderID(rs.getString("leaderID"));
				teamVO.setInscID(rs.getString("inscID"));
				teamVO.setTemaMFD(rs.getDate("teamMFD"));
				teamVO.setTeamEXP(rs.getDate("teamEXP"));
				teamVO.setTeamStatus(rs.getInt("teamStatus"));

			}

		} catch (Exception se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
		return teamVO;
	}

//-------------------------------------------------------------------------------------
	public static void main(String[] args) {
		TeamJDBCDAO dao = new TeamJDBCDAO();

//		// 新增
//		TeamVO teamVO1 = new TeamVO();
//		teamVO1.setLeaderID("weshare01");
//		teamVO1.setInscID("IC00001");
//		teamVO1.setTemaMFD(java.sql.Date.valueOf("2019-05-19"));
//		teamVO1.setTeamEXP(java.sql.Date.valueOf("2019-06-19"));
//		teamVO1.setTeamStatus((1));
//		dao.insert(teamVO1);
//
//		// 修改
		TeamVO teamVO2 = new TeamVO();
//
//		teamVO2.setLeaderID("weshare02");
//		teamVO2.setInscID("IC00002");
//		teamVO2.setTemaMFD(java.sql.Date.valueOf("2019-05-19"));
//		teamVO2.setTeamEXP(java.sql.Date.valueOf("2019-06-19"));
//		teamVO2.setTeamStatus((1));
//		teamVO2.setTeamId("TM00002");
//		dao.update(teamVO2);
//
		// 查詢
//		TeamVO TeamVO3 = dao.findByPrimaryKey("IC00001");
//		System.out.print(TeamVO3.getTeamId() + ",");
//		System.out.print(TeamVO3.getLeaderID() + ",");
//		System.out.print(TeamVO3.getInscID() + ",");
//		System.out.print(TeamVO3.getTemaMFD() + ",");
//		System.out.print(TeamVO3.getTeamEXP() + ",");
//		System.out.println(TeamVO3.getTeamStatus());
//		System.out.println("---------------------");
//		
		// 查全部

//		List<TeamVO> list = dao.getAll();
//
//		for (TeamVO teamVO4 : list) {	
//			System.out.print(teamVO4.getTeamId() + ",");
//			System.out.print(teamVO4.getLeaderID() + ",");
//			System.out.print(teamVO4.getInscID() + ",");
//			System.out.print(teamVO4.getTemaMFD() + ",");
//			System.out.print(teamVO4.getTeamEXP() + ",");
//			System.out.print(teamVO4.getTeamStatus());
//			System.out.println("---------------------");
//
//		}
		TeamVO TeamVO4 = dao.findByPrimaryKey1("TM00001");
		System.out.print(TeamVO4.getTeamId() + ",");
		System.out.print(TeamVO4.getLeaderID() + ",");
		System.out.print(TeamVO4.getInscID() + ",");
		System.out.print(TeamVO4.getTemaMFD() + ",");
		System.out.print(TeamVO4.getTeamEXP() + ",");
		System.out.println(TeamVO4.getTeamStatus());
		System.out.println("---------------------");
	}
	
//	TeamVO TeamVO4 = dao.getOneTeam("IC00001");
//	System.out.print(TeamVO4.getTeamId() + ",");
//	System.out.print(TeamVO4.getLeaderID() + ",");
//	System.out.print(TeamVO4.getInscID() + ",");
//	System.out.print(TeamVO4.getTemaMFD() + ",");
//	System.out.print(TeamVO4.getTeamEXP() + ",");
//	System.out.println(TeamVO4.getTeamStatus());
//	System.out.println("---------------------");
//}

	@Override
	public TeamVO findByPrimaryKey(String inscID) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
	


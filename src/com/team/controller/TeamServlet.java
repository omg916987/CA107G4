package com.team.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import com.inscourse.model.InsCourseService;
import com.inscourse.model.InsCourseVO;
import com.joingroup.model.JoinGroupService;
import com.joingroup.model.JoinGroupVO;


public class TeamServlet extends HttpServlet {

	private static final String String = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("Search_One".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("str");
				
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入課程");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/team/listOneInsCourse.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
				
//				}
				/*************************** 2.開始查詢資料 *****************************************/
				InsCourseService insCourseSvc = new InsCourseService();
				
				List<InsCourseVO> incrouseVO = insCourseSvc.findByCourse(str);
                
				System.out.println(str);
				
				System.out.println("到武一游2");
				if (incrouseVO.size() == 0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/team/findOneTeam.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("insCourseVOList", incrouseVO);
				// 資料庫取出的empVO物件,存入req
				String url = "/team/findOneTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/team/findOneTeam.jsp");
				failureView.forward(req, res);
			}
		}
//	---------------------------------------------------------------------------------------------	
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			java.sql.Date hiredate = null;
			try {
				String memId = req.getParameter("memId").trim();
				String teamId = req.getParameter("teamId").trim();
				
				

				JoinGroupVO joinGroupVO = new JoinGroupVO();
				joinGroupVO.setMemId(memId);
				joinGroupVO.setTeamId(teamId);
				System.out.println(memId);
				System.out.println(teamId);
				/*************************** 2.開始新增資料 ***************************************/
				JoinGroupService joinGroupSvc = new JoinGroupService();
				joinGroupVO = joinGroupSvc.addJoinGroup(memId, teamId);
				MemberService memberSvc = new MemberService();
				
				MemberVO membe = memberSvc.getOneMember(req.getParameter("memId"));
				joinGroupSvc.getAll();
				int memblance =0;
				int blance = membe.getMemBalance();
				int memBalance = membe.getMemBalance();
				
				
				
				Integer inscPrice1 = new Integer(req.getParameter("inscPrice"));
				
				int memBlock = membe.getMemBlock();
				System.out.println("餘額"+blance);
				System.out.println("要扣的錢"+inscPrice1);
				System.out.println("預扣款項"+memblance);
				if (blance < memBlock) {
					RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/withdrawalrecord.jsp");
					failureView.forward(req, res);
					return;

		    
				} else {
					 System.out.println("有走到這");
					memblance = blance - inscPrice1;
					memBlock = inscPrice1 + memBlock; 
					System.out.println(memblance);
					System.out.println("存到系統的錢"+memBlock);
					

				}
				memberSvc.update1(memblance, memBlock, membe.getMemId());
				
				System.out.println(memBlock);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/team/team.jsp";

				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/team/findOneTeam.jsp");
				failureView.forward(req, res);
			}
		}
//	____________________________________________________________________________________________	
		if ("findOneteam".equals(action)) { // 來自listAllEmp.jsp 或 /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或
																// 【/dept/listEmps_ByDeptno.jsp】 或 【
																// /dept/listAllDept.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req (是為了給update_emp_input.jsp)

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁, 存入req(只用於:istAllEmp.jsp)

			try {
				/*************************** 1.接收請求參數 ****************************************/
//				String memId = new String(req.getParameter("memId"));
//				HttpSession session = req.getSession();
//				/***************************2.開始查詢資料****************************************/
//								
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/team/findOneTeam.jsp");
////					                                 req.getRequestDispatcher("/member/editMember.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//				session.setAttribute("memberVO",VO); // 資料庫取出的memberVO物件,存入req
				String url = "/team/findOneTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 loginSuccess.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/team/findOneTeam.jsp");
				failureView.forward(req, res);
			}
		}
	
			if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			}
			try {
				/***************************1.接收請求參數***************************************/
				String memId = new String(req.getParameter("memId"));
				String teamId = new String(req.getParameter("teamId"));
				
			
			    
				/***************************2.開始刪除資料***************************************/
				JoinGroupService joinGroupSvc = new JoinGroupService();
				JoinGroupVO joinGroupVO = joinGroupSvc.deleteJoinGroup(memId, teamId);
				joinGroupSvc.deleteJoinGroup(memId, teamId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
			
				String url = "/team/team.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(requestURL);
//				failureView.forward(req, res);
			
			
			}
			}

}

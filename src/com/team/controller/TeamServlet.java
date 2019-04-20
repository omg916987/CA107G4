package com.team.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;


import com.inscourse.model.InsCourseService;
import com.inscourse.model.InsCourseVO;
import com.joingroup.model.JoinGroupService;
import com.joingroup.model.JoinGroupVO;

public class TeamServlet extends HttpServlet {

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
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
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
				
				System.out.println("1234");
				
				/***************************2.開始查詢資料*****************************************/
				
				InsCourseService insCourseSvc = new InsCourseService();
				List<InsCourseVO> insCourseVOList = insCourseSvc.findClassName(str);
				if (insCourseVOList.size()==0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/team/team_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("insCourseVOList", insCourseVOList); // 資料庫取出的empVO物件,存入req
				String url = "/team/listOneInsCourse.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
                System.out.println("1234");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/team/listOneInsCourse.jsp");
				failureView.forward(req, res);
			}
		}
//	---------------------------------------------------------------------------------------------	
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			java.sql.Date hiredate = null;
			System.out.println("到武一游");

			try {
				
				String memId = req.getParameter("memId");
				if (memId == null || (memId.trim()).length() == 0) {
					errorMsgs.add("請輸會員編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/team/team.jsp");
					failureView.forward(req, res);
					return;
				}
				String teamId = req.getParameter("teamId").trim();
				if (!teamId.matches("TM[0-9]{5}")) {
					errorMsgs.add("課程編號格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/team/team.jsp");
					failureView.forward(req, res);
					return;
				}
				
				  
				JoinGroupVO joinGroupVO = new JoinGroupVO();
				joinGroupVO.setMemId(memId);
				joinGroupVO.setTeamId(teamId);
					
				
			
				
				
				/***************************2.開始新增資料***************************************/
				JoinGroupService joinGroupSvc = new JoinGroupService();
				
				joinGroupVO = joinGroupSvc.addJoinGroup(memId,teamId);
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/team/team.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				

			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/team/team.jsp");
				failureView.forward(req, res);
			}
			}
//	____________________________________________________________________________________________	
	}

}

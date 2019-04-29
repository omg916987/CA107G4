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
System.out.println(action);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("str");
						
				/***************************2.開始查詢資料*****************************************/
				
				InsCourseService insCourseSvc = new InsCourseService();
				
				List<InsCourseVO> insCourseVOList = insCourseSvc.findClassName(str);
				
				if (insCourseVOList.size()==0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/team/One.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("insCourseVOList", insCourseVOList);
				// 資料庫取出的empVO物件,存入req
				String url = "/team/One.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("1234");
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				System.out.println("HI");
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/team/One.jsp");
				failureView.forward(req, res);
			}
		}
//	---------------------------------------------------------------------------------------------	
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);	
			try {
				JoinGroupService joinGroupSvc = new JoinGroupService();
				String memId = req.getParameter("memId").trim();
				String teamId = req.getParameter("teamId").trim();
				if(joinGroupSvc.findById(memId,teamId).getMemId()==null) {
		/*************************** 2.開始新增資料 ***************************************/
					JoinGroupVO joinGroupVO = new JoinGroupVO();
					joinGroupVO =joinGroupSvc.addJoinGroupVO(memId, teamId);
					MemberService memberSvc = new MemberService();
					MemberVO membe = memberSvc.getOneMember(req.getParameter("memId"));
					joinGroupSvc.getAll();
					int memblance =0;
					int blance = membe.getMemBalance();
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
						memblance = blance - inscPrice1;
						memBlock = inscPrice1 + memBlock; 
						System.out.println("memblance="+ memblance);				
					}
					memberSvc.update1(memblance, memBlock, membe.getMemId());           
					/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
					String url = "/team/myTeam.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res); 					
				}else {
					errorMsgs.add("您已經有相同的揪團了");
					RequestDispatcher failureView = req.getRequestDispatcher("/team/team.jsp");
					failureView.forward(req, res);
					return;
				}		
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/team/myTeam.jsp");
				failureView.forward(req, res);
			}
		}		
//	____________________________________________________________________________________________	
		if ("findOneteam".equals(action)) { // 來自listAllEmp.jsp 或 /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String memId = new String(req.getParameter("memId"));
				HttpSession session = req.getSession();
				/***************************2.開始查詢資料****************************************/						
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/team/myTeam.jsp");	                                
					failureView.forward(req, res);
					return;// 程式中斷
				}
//				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				session.setAttribute("memberVO",memId); // 資料庫取出的memberVO物件,存入req
				String url = "/team/myTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 loginSuccess.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/team/myTeam.jsp");
				failureView.forward(req, res);
		}
	}		
		
		
		
			if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL = req.getParameter("requestURL");
			try {
				/***************************1.接收請求參數***************************************/
				String memId = new String(req.getParameter("memId"));
				String teamId = new String(req.getParameter("teamId"));
				/***************************2.開始刪除資料***************************************/
				JoinGroupService joinGroupSvc = new JoinGroupService();
				JoinGroupVO joinGroupVO = joinGroupSvc.deleteJoinGroup(memId, teamId);
				joinGroupSvc.deleteJoinGroup(memId, teamId);
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				String url = "/team/myTeam.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			
			
			}
		}
	}
}

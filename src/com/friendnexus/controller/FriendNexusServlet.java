package com.friendnexus.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;


import com.friendnexus.model.FriendNexusService;
import com.friendnexus.model.FriendNexusVO;

public class FriendNexusServlet extends HttpServlet {

	private static final String String = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert1".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String memId = req.getParameter("memId");
				String friendAcc = req.getParameter("friendAcc");
//					Integer friendstatus = req.getParameter(friendstatus);

				if (memId == friendAcc) {
					errorMsgs.put("", "不能加自己為好友喔");
				}
				if (memId == null) {
					String url = "/withdrawalrecord/withdrawalrecord.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
					successView.forward(req, res);
				}

				/*************************** 2.開始新增資料 ***************************************/
				FriendNexusService friendnexusSvc = new FriendNexusService();
				friendnexusSvc.addfriendNexus(memId, friendAcc, 0);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/friend/Allfriend.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/friend/Allfriend.jsp");
				failureView.forward(req, res);
			}
		}
		
		
if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String memId = new String(req.getParameter("memId").trim());
				String friendAcc= new String(req.getParameter("friendAcc").trim());
				Integer friendstatus = new Integer(req.getParameter("friendstatus").trim());
				
				FriendNexusVO friendNexusVO = new FriendNexusVO();
				friendNexusVO.setMemId(memId);
				friendNexusVO.setFriendAcc(friendAcc);
				friendNexusVO.setFriendstatus(friendstatus);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("friendNexusVO", friendNexusVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/friend/friendCheck.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				FriendNexusService friendNexusSvc = new FriendNexusService();
				friendNexusVO = friendNexusSvc.updatefriendNexus(memId, friendAcc, friendstatus);
				
				System.out.println(memId);
				System.out.println(friendAcc);
				System.out.println(friendstatus);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("friendNexusVO", friendNexusVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/friend/friendCheck.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/friend/friendCheck.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
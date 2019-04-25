package com.friendnexus.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import com.friendnexus.model.FriendNexusService;

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
	}

}
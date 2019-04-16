package com.withdrawalrecord.contoller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.withdrawalrecord.model.*;

public class WithdrawalRecordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//查詢(單一)
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String wrnum = req.getParameter("wrnum");
				if (wrnum == null || (wrnum.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				if (!wrnum.matches("WI[0-9]{5}")) {
					errorMsgs.add("課程編號格式錯誤");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

//				String wrnum = null;
//				try {
//					wrnum = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				WithdrawalRecordService withdrawalRecordSvc = new WithdrawalRecordService();
				WithdrawalRecordVO withdrawalRecordVO = withdrawalRecordSvc.getOneWithdrawalRecord(wrnum);
				if (withdrawalRecordVO == null) {
					errorMsgs.add("查無資料");
				}

				/*************************** 2.開始查詢資料 *****************************************/

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("withdrawalRecordVO", withdrawalRecordVO); // 資料庫取出的withdrawalRecordVO物件,存入req
				String url = "/withdrawalrecord/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// 査全部
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String wrnum = new String(req.getParameter("wrnum"));

				/*************************** 2.開始查詢資料 ****************************************/
				WithdrawalRecordService withdrawalRecordSvc = new WithdrawalRecordService();
				WithdrawalRecordVO withdrawalRecordVO = withdrawalRecordSvc.getOneWithdrawalRecord(wrnum);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("withdrawalRecordVO", withdrawalRecordVO); // 資料庫取出的withdrawalRecordVO物件,存入req
				String url = "/withdrawalrecord/addWithdrawalRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 addWithdrawalRecord.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/withdrawalrecord/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}

		
//新增
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String memid = req.getParameter("memid");


				

				String memidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memid == null || memid.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}else if(!memid.trim().matches(memidReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Integer wrmoney = null;
				try {
					wrmoney = new Integer(req.getParameter("wrmoney").trim());
				} catch (NumberFormatException e) {
					wrmoney = 0;
					errorMsgs.add("交易金額請填數字.");
				}
//				
				
				
				

				java.sql.Date wrtime = null;
                try {
					wrtime = java.sql.Date.valueOf(req.getParameter("wrtime").trim());
				} catch (IllegalArgumentException e) {
					wrtime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				

				WithdrawalRecordVO withdrawalRecordVO = new WithdrawalRecordVO();
				
				withdrawalRecordVO.setMemid(memid);
				withdrawalRecordVO.setWrmoney(wrmoney);
				withdrawalRecordVO.setWrtime(wrtime);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("withdrawalRecordVO", withdrawalRecordVO); // 含有輸入格式錯誤的withdrawalRecordVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/withdrawalrecord/addWithdrawalRecord.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				WithdrawalRecordService withdrawalRecordSvc = new WithdrawalRecordService();
				withdrawalRecordVO = withdrawalRecordSvc.addWithdrawalRecord(memid, wrmoney, wrtime);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				String url = "/withdrawalrecord/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/withdrawalrecord/addWithdrawalRecord.jsp");
				failureView.forward(req, res);
			}
		}

		// 刪除
//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//	
//			try {
//				/***************************1.接收請求參數***************************************/
//				String wrnum = new String(req.getParameter("wrnum"));
//				
//				/***************************2.開始刪除資料***************************************/
//				WithdrawalRecordService withdrawalRecordSvc = new WithdrawalRecordService();
//				withdrawalRecordSvc.deleteEmp(wrnum);
//				
//				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
//				String url = "/withdrawalRecord/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
//				successView.forward(req, res);
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/withdrawalRecord/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
	}
}
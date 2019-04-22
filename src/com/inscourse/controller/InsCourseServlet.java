package com.inscourse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



import com.inscourse.model.InsCourseService;
import com.inscourse.model.InsCourseVO;




@WebServlet("/InsCourseServlet")
public class InsCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
//		response.setContentType("text/plain; charset=UTF-8");
//		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");		
//		if("search_by_CourseType".equals(action)) {
//			List<String> erroMsgs = new LinkedList();
//			
//			String courseId = request.getParameter("keyword");
//			InsCourseService insCourseService = new InsCourseService();
//			//insCourseService.findByCourse(courseId);
//			List<InsCourseVO> insCourseVOs = insCourseService.findByCourse("0005");
//			
//			Gson gson = new Gson();
//			out.print(gson.toJson(insCourseVOs));
//		}
		
//		----------------------------------------------------------------------------------------------------
	if ("Search_One".equals(action)) { // 來自select_page.jsp的請求
        
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = request.getParameter("str");
			 System.out.println("出來1");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入課程");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/team/listOneInsCourse.jsp");
//				failureView.forward(req, res);
//				return;//程式中斷
//			}		
			/***************************2.開始查詢資料*****************************************/
			 
			InsCourseService insCourseSvc = new InsCourseService();
			
			List<InsCourseVO> list = insCourseSvc.findClassName(str);
			 
			if (list.size()==0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request
						.getRequestDispatcher("/inscourse/teamOne.jsp");
				failureView.forward(request, response);
				return;//程式中斷
			}
			 
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			request.setAttribute("list", list);
			System.out.println(list);
			// 資料庫取出的empVO物件,存入req
			String url = "/team/team.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(request, response);
			
			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
			errorMsgs.add("無法取得資料:" + e.getMessage());
			RequestDispatcher failureView = request
					.getRequestDispatcher("/inscourse/teamOne.jsp");
			failureView.forward(request, response);
		}
	}
}}


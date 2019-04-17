<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.team.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	TeamService teamSvc = new TeamService();
	List<TeamVO> list = teamSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<jsp:useBean id="insCourseSvc" scope="page"
	class="com.inscourse.model.InsCourseService" />

<jsp:useBean id="insCourseTimeSvc" scope="page"
	class="com.inscoursetime.model.InsCourseTimeService" />

<jsp:useBean id="courseSvc" scope="page"
	class="com.course.model.CourseService" />
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/G4.css ">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<title>WeShare | 最棒的教育共享平台</title>
<style type="">
}
.breadcrumb-item+.breadcrumb-item::before {
	content: "|";
}

.page-link:hover {
	background-color: #8af;
	color: #fff;
	border-color: #00f;
	transform: scale(2);
}

.head {
	display: none;
}

.jumbotron {
	border-radius: 0;
}

.jumbotron {
	background-image: url(01.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center center;
}

.JumboHeaderImg {
	background-size: cover;
	background-image: url(images/333.jpg);
}

#quick_form {
	margin-top: 60px;
	margin-left: 35px;
}

.plan {
	padding: 10px;
	border: 1px #ccc solid;
	border-radius: 10px;
	margin: 10px;
}

.plan_iamge {
	width: 200px;
	height: 150px;
	float: left;
}

.container {
	width: 100%;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
	max-width: 1140px;
}

.row {
	display: flex;
	flex-wrap: wrap;
	margin-right: -15px;
	margin-left: -15px;
}

.button-group {
	margin-left: 900px;
	margin-top: -40px;
}

.page2 {
	text-align: center;
	width: 500px;
	margin: auto;
}

.form-row {
	margin-left: 5px;
	margin-top: 5px;
}

.btn-info {
    margin-top:5px;
}
</style>
</head>
<body>
	<!-------------------------------------------------------------------------headerStart------------------------------------------------------------------------->
	<div class="header headerImg">
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<img src="images/icon/logo.png" width="80" height="60" alt="" /><a
				class="navbar-brand" href="#">教育共享平台</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a class="nav-link" href="#">成為老師
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">探索課程</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">所有課程</a> <a
								class="dropdown-item" href="#">音樂</a> <a class="dropdown-item"
								href="#">語言</a> <a class="dropdown-item" href="#">運動</a> <a
								class="dropdown-item" href="#">藝術</a> <a class="dropdown-item"
								href="#">設計</a> <a class="dropdown-item" href="#">人文</a> <a
								class="dropdown-item" href="#">行銷</a> <a class="dropdown-item"
								href="#">程式語言</a> <a class="dropdown-item" href="#">投資理財</a> <a
								class="dropdown-item" href="#">職場技能</a> <a class="dropdown-item"
								href="#">手作</a> <a class="dropdown-item" href="#">烹飪</a>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="#">登入</a></li>
					<li class="nav-item"><a class="nav-link" href="#">註冊</a></li>
					<li class="nav-item"><a class="nav-link disabled" href="#">關於我們</a>
					</li>
				</ul>
			</div>
		</nav>
	</div>
	<!-------------------------------------------------------------------------headerEnd------------------------------------------------------------------------->
	<div class="jumbotron JumboHeaderImg">

		<div id="quick_form">
			<h1>
				<font face="fantasy" color="#855600">想揪什麼團呢？</font>
			</h1>


			<jsp:useBean id="incourseSvc" scope="page"
				class="com.inscourse.model.InsCourseService" />
			<jsp:useBean id="courseSvc1" scope="page"
				class="com.course.model.CourseService" />

			<div class="form-row">
				<div class="form-group col-md-4">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/incourse/incourse.do">
						<select post="請選擇課程" class="form-control">
							<c:forEach var="incourseVO" items="${incourseSvc.all}">
								<option value="${teamVO.inscID==incourseVO.inscId}">${incourseVO.courseId}
								
<%-- 									<option value="${incourseVO.courseId==courseVO.courseId}">${courseVO.courseName} --%>
							
							</c:forEach>
							<div class="form-row">
						</select>
					</FORM>
					<div class="form-row">

                       <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/team/team.do">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="submit" name="commit" value="查詢"
						class="btn btn-info submit" data-disable-with="find" />
							</FORM>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="page1.file"%>
	<c:forEach var="teamVO" items="${list}" begin="<%=pageIndex%>"
		end="<%=pageIndex+rowsPerPage-1%>">
		<div class="container">
			<div class="plan">
				<div class="plan_iamge">
					<img alt="" width="200" height="150" src="images/555.png" />
				</div>
				<div class="plan_info">
					<h4>${teamVO.inscID}
						<span class="badge badge-light">團體課程</span>
					</h4>

					<jsp:useBean id="insCoursetimeSvc" scope="page"
						class="com.inscourse.model.InsCourseService" />

					<div>
						<i class="far fa-calendar-alt"></i>截團時間 ${teamVO.teamEXP}
						<%-- ${insCourseTimeSvc.getOneInsCourseTime(insCourseVO.inscId).inscMFD} --%>
						<%-- <c:forEach var="insCourseTimeVO" items="${insCourseTimeSvc.getOneInsCourseTime(insCourseVO.inscId)}">   --%>
						<%-- <c:if test="${insCourseVO.inscId==insCourseTimeVO.inscId}"> --%>
						<%-- ${insCourseTimeVO.inscMFD}             --%>
						<%--   </c:if> --%>
						<%--  </c:forEach> --%>

					</div>
					<div></div>
					<hr>
					<div>
						<span class="badge badge-light">收費模式</span> <span
							class="badge badge-success">預先扣款</span> <span
							class="badge badge-lisght"> <i class="fas fa-dollar-sign"></i>
						</span>每人 ${insCourseSvc.findOneById(teamVO.inscID).inscPrice}元<br>
					</div>

					<div>
						<span class="badge badge-light"> 隊伍型態 </span> <span
							class="badge badge-info">自主性揪團</span>
					</div>
				</div>
				<div class="button-group">
				 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/team/team.do">
				    <input type="hidden" name="action"	value="getOne_For_Update">
					<input type="submit" name="commit" value="詳情"
						class="btn btn-info submit" data-disable-with="find" />			 
				</FORM>
					<input type="submit" name="commit" value="申請加入"
						class="btn btn-info submit" data-disable-with="find" />
					

				</div>
			</div>
		</div>
	</c:forEach>
	<%@ include file="page2.file"%>










	<!-------------------------------------------------------------------------footerStart------------------------------------------------------------------------->
	<footer class="section footer-classic context-dark bg-image"
		style="background: #74b49b;">
		<div class="container">
			<div class="row row-30">
				<div class="col-md-4 col-xl-5">
					<div class="pr-xl-4">
						<a href="index.html"></a>
						<p class="reademe">我們是最佳的共享教育的平台，致力於在分享技能，保障交易，展現自我，使用戶得到最棒的學習體驗。</p>
						<!-- Rights-->
						<p class="rights">
							<span>©  </span><span class="copyright-year">2018</span><span> </span><span>WeShare教育共享平台</span><span>. </span><span>©
								All Rights Reserved. .</span>
						</p>
					</div>
				</div>
				<div class="col-md-4">
					<h5 class="reademe">聯絡我們</h5>
					<dl class="contact-list">
						<dt>地址:</dt>
						<dd>桃園市中壢區中大路300號</dd>
					</dl>
					<dl class="contact-list">
						<dt>信箱:</dt>
						<dd>
							<a href="mailto:#">weshare@gmail.com</a>
						</dd>
					</dl>
					<dl class="contact-list">
						<dt>電話:</dt>
						<dd>
							<a href="tel:#">03-425-7387</a>
						</dd>
					</dl>
				</div>
				<div class="col-md-4 col-xl-3">
					<h5 class="reademe2">關於</h5>
					<ul class="nav-list">
						<li><a href="#">關於我們</a></li>
						<li><a href="#">團隊成員</a></li>
						<li><a href="#">加入WeShare</a></li>
						<li><a href="#">隱私權政策</a></li>
						<li><a href="#">功能更新</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="row no-gutters social-container">
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-facebook"></span><span>Facebook</span></a>
			</div>
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-instagram"></span><span>instagram</span></a>
			</div>
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-twitter"></span><span>twitter</span></a>
			</div>
			<div class="col">
				<a class="social-inner" href="#"><span
					class="icon mdi mdi-youtube-play"></span><span>google</span></a>
			</div>
		</div>
	</footer>
	<!-------------------------------------------------------------------------footerEnd------------------------------------------------------------------------->
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.team.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.joingroup.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.inscourse.model.*"%>


<html>
<head>


<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    color: black;
    display: block;
    margin: 5px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-1" class="myTable">
	
</table>
<table class="myTable">
	<tr>
		<th>團主姓名</th>
		<th>連絡電話</th>
		<th>預扣金額</th>
		<th>截團時間</th>
		
	</tr>
	<tr>
		<td>${memberVO.memName}</td>
		<td>${memberVO.memPhone}</td>
<%-- 		<td>${insCourseVO.inscPrice}</td> --%>
<%-- 		<td>${teamSvc.getAll().get(0).getTeamEXP()}</td> --%>
	</tr>
	
</table>

</body>
</html>
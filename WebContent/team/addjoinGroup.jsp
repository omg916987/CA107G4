<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.joingroup.model.*"%>

<%
JoinGroupVO joinGroupVO = (JoinGroupVO) request.getAttribute("joinGroupVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>課程資料新增 - addEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
    width: 100%;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
	form{
	margin: 1px auto;
	}
  table {
	width: 450px;
	background-color: white;
	margin: 1px auto;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>課程資料新增 - addEmp.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3 align="center">資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="team.do" name="form1">
<table>

	
	<tr>
		<td>老師:</td>
		<td><input type="TEXT" name="memId" size="45"
			 value="<%= (joinGroupVO==null)? "weshare04" : joinGroupVO.getMemId()%>" /></td>
	</tr>
	<tr>
		<td>課程種類:</td>
		<td><input type="TEXT" name="teamId" size="45"
			 value="<%= (joinGroupVO==null)? "TM00004" : joinGroupVO.getTeamId()%>" /></td>
	</tr>
	

	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" ></FORM>
</body>
</html>
<%@page import="java.io.BufferedInputStream"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.Base64"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="courseSvc" scope="page" class="com.course.model.CourseService" />
<html>
<head>
<title>�Ҧ��|����� - listAllMember.jsp</title>

<style>
  table#table-1 {
	background-color: #a7d7c5;
    border: 2px solid black;
    text-align: center;
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
  body{
  background:#f4f9f4;
  }
</style>

<style>
  table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ��|����� - listAllMember.jsp</h3>
		 <h4><a href="http://localhost:8081/CA107G4/"><img src="http://localhost:8081/CA107G4/images/icon/logo.png" width="120" height="120" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>�b��</th>
		<th>�m�W</th>
		<th>�����Ҧr��</th>
		<th>�H�c</th>
		<th>�q��</th>
		<th>�ͤ�</th>
		<th>�a�}</th>
		<th>�M��</th>
		<th>�Q�Ǫ�</th>
		<th>�|���t��</th>
		<th>�K�X</th>
		<th>���ܱK�X</th>
		<th>�ʧO</th>
		<th>�Ϥ�</th>
		<th>����</th>
		<th>�Ȧ�b��</th>
		<th>�Ѿl�I��</th>
		<th>�w���I��</th>
		<th>���A</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVO.memId}</td>
			<td>${memberVO.memName}</td>
			<td>${memberVO.memIdCard}</td>
			<td>${memberVO.memEmail}</td>
			<td>${memberVO.memPhone}</td>
			<td>${memberVO.memAdd}</td>
			<td>${memberVO.memBirth}</td>
<td>
<c:forEach var="courseVO" items="${courseSvc.getAll()}">
<c:if test="${memberVO.memSkill==courseVO.courseId}"> 
	       ${courseVO.courseName}
 </c:if>
</c:forEach>
</td>
			
<td>
<c:forEach var="courseVO" items="${courseSvc.getAll()}">
<c:if test="${memberVO.memWantSkill==courseVO.courseId}"> 
	       ${courseVO.courseName}
 </c:if>
</c:forEach>
</td>
			
			<td>${memberVO.memPair}</td>
			<td>${memberVO.memPsw}</td>
			<td>${memberVO.memPswHint}</td>
			<td>${memberVO.memSex}</td>
			<td><img src="<%=request.getContextPath()%>/member/DBGifReader.do?memId=${memberVO.memId}" width="226" height="225"></td>
			<td>${memberVO.memText}</td>
			<td>${memberVO.memBank}</td>
			<td>${memberVO.memBalance}</td>
			<td>${memberVO.memBlock}</td>
			<td>${memberVO.memStatus}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			      
			      <input type="hidden" name="memId"  value="${memberVO.memId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			      <input type="submit" value="�ק�">
			 </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
 

</body>
</html>
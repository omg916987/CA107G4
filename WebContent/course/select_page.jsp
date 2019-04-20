<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Course: Home</title>

<style>
  table#table-1 {
	width:100%;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin: 1px auto;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table{
  	margin: 1px auto;
  }
  
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Course: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Course: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllCourse.jsp'>List</a> all CourseServlets.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="Course.do" >
        <b>輸入課程 (如0000):</b>
        <input type="text" name="courseId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="CourseSVC" scope="page" class="com.course.model.CourseService" />
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="Course.do" > -->
<!--        <b>選擇課程編號:</b> -->
<!--        <select size="1" name="courseId"> -->
<%--          <c:forEach var="CourseVO" items="${CourseSVC.all}" >  --%>
<%--           <option value="${CourseVO.inscId}">${CourseVO.inscId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  

</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addCourse.jsp'>Add</a> a new Course.</li>
</ul>

</body>
</html>
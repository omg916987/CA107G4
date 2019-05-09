<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.friendnexus.model.*"%>



<jsp:useBean id="courseSvc" scope="page"
	class="com.course.model.CourseService" />

<jsp:useBean id="friendnexusSvc" scope="page"
	class="com.friendnexus.model.FriendNexusService" />
<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />

<c:set var="list" value="${friendnexusSvc.friendNexus1(memberVO.memId)}" />

 <!doctype html>
    <html lang="en">

    <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="css/G4.css ">
    <link rel="stylesheet" href="css/amazeui.min.css" />
    <link rel="stylesheet" href="css/main.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <title>WeShare | 最棒的教育共享平台</title>
    <style type="text/css">
    
    .headName{
        margin-top: 10px;
        margin-left:10px;
    
    }
    
    
    
    </style>
    </head>
     <body onload="connect();" onunload="disconnect();" >
    <!-------------------------------------------------------------------------headerStart------------------------------------------------------------------------->	
    <div class="header headerImg">
      <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top"> <img src="images/icon/logo.png" width="80" height="60" alt=""/><a class="navbar-brand" href="#">教育共享平台</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item active"> <a class="nav-link" href="#">成為老師 <span class="sr-only">(current)</span></a> </li>
            <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">探索課程</a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> <a class="dropdown-item" href="#">所有課程</a> <a class="dropdown-item" href="#">音樂</a> <a class="dropdown-item" href="#">語言</a> <a class="dropdown-item" href="#">運動</a> <a class="dropdown-item" href="#">藝術</a> <a class="dropdown-item" href="#">設計</a> <a class="dropdown-item" href="#">人文</a> <a class="dropdown-item" href="#">行銷</a> <a class="dropdown-item" href="#">程式語言</a> <a class="dropdown-item" href="#">投資理財</a> <a class="dropdown-item" href="#">職場技能</a> <a class="dropdown-item" href="#">手作</a> <a class="dropdown-item" href="#">烹飪</a> </div>
            </li>
            <li class="nav-item"> <a class="nav-link" href="#">登入</a> </li>
            <li class="nav-item"> <a class="nav-link" href="#">註冊</a> </li>
            <li class="nav-item"> <a class="nav-link disabled" href="#">關於我們</a> </li>
          </ul>
        </div>
      </nav>
    </div>	
    <!-------------------------------------------------------------------------headerEnd------------------------------------------------------------------------->
<div class="box">
        <div class="wechat">
            <div class="sidestrip">
                <div class="am-dropdown" data-am-dropdown>
                    <!--圖片-->
                    <div class="own_head am-dropdown-toggle"></div> 
                </div>
                <!---->
                <div class="sidestrip_icon">
                    <a id="si_1"></a>                  
                </div>
                <!--底部-->
                <div id="doc-dropdown-justify-js">
                    <div class="am-dropdown" id="doc-dropdown-js" style="position: initial;">      
                        <ul class="am-dropdown-content" style="">    
                        </ul>
                    </div>
                </div>
            </div>
            <!--聊天列表-->
            <div class="middle on">
                <div class="wx_search">
                    <input type="text" placeholder="搜索" />
                    <button>+</button>
                </div>
                <div class="office_text">          
                <c:forEach var="friendNexusVO" items="${list}">
                    <ul class="user_list">      
                        <li class="bg">
                            <div class="user_head"><img src="<%=request.getContextPath()%>/member/DBGifReader.do?memId=${friendNexusVO.friendAcc}" /></div>
                            <div class="user_text">
                                <p class= "intername">${memberSvc.getOneMember(friendNexusVO.friendAcc).memName}</p>
                                 <p class="infor" id="${friendNexusVO.friendAcc}" value="${friendNexusVO.friendAcc}">${friendNexusVO.friendAcc}</p>
                            </div> 
                        </li>
                    </ul>
          <script type="text/javascript">   
          
          
                  $('.office_text li').on('click',function(){
                	  
                	  $('.bg').removeClass('bg');
                	  $(this).addClass('bg');
                	  var intername=$(this).children('.user_text').children('.intername').text();
                	
              		$('.headName').text(intername);
              		$('.content').html('');
              		
              	})      
              	       

                    </script>
                    
                    </c:forEach> 
                </div>
            </div>
            <!--聊天窗口-->
            <div class="talk_window">
                <div class="windows_top">
                    <div class="windows_top_box">
                    
                        <div class="headName"></div>
                    </div>
                </div>
                <!--聊天内容-->
                <div class="windows_body" style="overflow:auto;">
                    <div class="office_text" id="who" style="height: 100%;">
                        <ul class="content" id="chatbox" style="overflow:auto;">
                            
                        </ul>
                    </div>
                </div>

                <div class="windows_input" id="talkbox">
                    <div class="input_icon">
                        <a href="javascript:;"></a>
                        <a href="javascript:;"></a>
                        <a href="javascript:;"></a>
                        <a href="javascript:;"></a>
                        <a href="javascript:;"></a>
                        <a href="javascript:;"></a>
                    </div>
                    <div class="input_box">
                    
                    <!-- 輸入框在這 -->
                        <textarea name="" rows="" cols="" id="input_box" onkeydown="if (event.keyCode == 13) sendMessage();"></textarea>
                        <input type="hidden" class="button" id="sendMessage" value="送出" onclick="sendMessage();"/>
                        
                         <input type="button" value="sendFile" onclick="sendFile()"/> 
  		                 <input type="file" id="file" />

                  </div>
                </div>
            </div>
        </div>
    </div>
    <!-------------------------------------------------------------------------footerStart------------------------------------------------------------------------->	
    <footer class="section footer-classic context-dark bg-image" style="background: #74b49b;">
      <div class="container">
        <div class="row row-30">
          <div class="col-md-4 col-xl-5">
            <div class="pr-xl-4"><a href="index.html"></a>
              <p class="reademe">我們是最佳的共享教育的平台，致力於在分享技能，保障交易，展現自我，使用戶得到最棒的學習體驗。</p>
              <!-- Rights-->
              <p class="rights"><span>©  </span><span class="copyright-year">2018</span><span> </span><span>WeShare教育共享平台</span><span>. </span><span>© All Rights Reserved.
                .</span></p>
            </div>
          </div>
          <div class="col-md-4" >
            <h5 class="reademe">聯絡我們</h5>
            <dl class="contact-list">
              <dt>地址:</dt>
              <dd>桃園市中壢區中大路300號</dd>
            </dl>
            <dl class="contact-list">
              <dt>信箱:</dt>
              <dd><a href="mailto:#">weshare@gmail.com</a></dd>
            </dl>
            <dl class="contact-list">
              <dt>電話:</dt>
              <dd><a href="tel:#">03-425-7387</a> </dd>
            </dl>
          </div>
          <div class="col-md-4 col-xl-3" >
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
        <div class="col"><a class="social-inner" href="#"><span class="icon mdi mdi-facebook"></span><span>Facebook</span></a></div>
        <div class="col"><a class="social-inner" href="#"><span class="icon mdi mdi-instagram"></span><span>instagram</span></a></div>
        <div class="col"><a class="social-inner" href="#"><span class="icon mdi mdi-twitter"></span><span>twitter</span></a></div>
        <div class="col"><a class="social-inner" href="#"><span class="icon mdi mdi-youtube-play"></span><span>google</span></a></div>
      </div>
    </footer>
    <!-------------------------------------------------------------------------footerEnd------------------------------------------------------------------------->	
    <!-- Optional JavaScript --> 
    <!-- jQuery first, then Popper.js, then Bootstrap JS --> 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> 
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


      <script type="text/javascript">
      
      
      
      var MyPoint = "/FriendWS/${memberVO.memId}";
      var host = window.location.host;
      var path = window.location.pathname;
      var webCtx = path.substring(0, path.indexOf('/', 1));
      var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

      var webSocket;
      webSocket = new WebSocket(endPointURL);
      
      var me = 'weshare01';
      var friend= 'weshare02';
      
      
      var intername;

 	  $('.office_text li').on('click',function(){
          intername=$(this).children('.user_text').children('.infor').text();
           
         	})  
      
      
      
      
      
      var chat = document.getElementById('chatbox');
      function connect() {
          // create a websocket
          webSocket.onopen = function(event) {
             
              document.getElementById('sendMessage').disabled = false;
              document.getElementById('connect').disabled = true;
              document.getElementById('disconnect').disabled = false;
          };

          webSocket.onmessage = function(event) {
        	  
              var jsonObj = JSON.parse(event.data);
              var message = jsonObj.sender + ": " + jsonObj.message + "\r\n";
              chat.innerHTML += '<li class="other"><img src="' + '/CA107G4/member/DBGifReader.do?memId=weshare03' + '"><span>' + message +'</span></li>';
              $('.windows_body').scrollTop($('.windows_body')[0].scrollHeight );
          };
          
          
          
          

          webSocket.onclose = function(event) {
              updateStatus("WebSocket Disconnected");
          };
      }
      
      
  	 function sendMessage() {
  		
         var text = document.getElementById('input_box');
       
//          var btn = document.getElementById('sendMessage');
         var talk = document.getElementById('talkbox');
 //        btn.onclick = function () {
             if (text.value == '') {     	 
                 alert('請輸入訊息');
                 return;	
             } else{           
         	     chat.innerHTML += '<li class="me"><img src="' + '/CA107G4/member/DBGifReader.do?memId=weshare01' + '"><span> '+ text.value +'</span> </li>';
//          	  <img src="' + 'images/own_head.jpg' + '">
              $('.windows_body').scrollTop($('.windows_body')[0].scrollHeight );
               chat.scrollTop = chat.scrollHeight;
                talk.style.background = "#fff";
                text.style.background = "#fff";
                var jsonObj = {
   					 "type": "chat",
    					   "sender" : "${memberVO.memId}",
    					   "receiver": intername,
    					   "message" : text.value
   				};
   			webSocket.send(JSON.stringify(jsonObj));
   		    text.value = '';
   		   
   		}
//           }	
     };
     function disconnect() {
 		webSocket.close();
 		document.getElementById('sendMessage').disabled = true;
 		document.getElementById('connect').disabled = false;
 		document.getElementById('disconnect').disabled = true;
 	}
 	function updateStatus(newStatus) {
 		statusOutput.innerHTML = newStatus;
 	} 	
 	$('.conLeft li').on('click',function(){
		$(this).addClass('bg').siblings().removeClass('bg');
		var intername=$(this).children('.liRight').children('.intername').text();
		$('.headName').text(intername);
		$('.newsList').html('');
	})
		$("#input_box").keypress(function(e){

			  code = (e.keyCode ? e.keyCode : e.which);

			  if (code == 13)

			  {

			      //targetForm是表單的ID

			      $("sendMessage").submit();

			  }

			});
 	
 	
	 
 	
 	
 	

 	
 </script>
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
<!--   	window.onload = function () { -->
<!--             function a() { -->
<!--                 var si1 = document.getElementById('si_1'); -->
                
<!--                 si1.onclick = function () { -->
<!--                     si1.style.background = "url(images/icon/head_2_1.png) no-repeat" -->
                 
<!--                 }; -->
             
<!--             }; -->
           
<!--             a(); -->
           
<!--         }; -->
    </script>
    </body>
    </html>
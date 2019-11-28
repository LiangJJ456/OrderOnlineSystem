<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.LiangZhenJi.www.dao.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天界面</title>
<style>
        *{
    padding: 0;
    margin: 0;
}
html , body {
    width: 100%;
    height: 100%;
    margin: 0 auto;
    font-size: 20px;
}
#all{
    width: 100%;
    height: 100%;
    position: relative;
    text-align: center;
}
    #loading_div{
        display: inline-block;
       height:auto;
        width: 600px;
        position: relative;
        z-index: 300;
        margin: 0 auto;
        padding: 0;
        text-align: right;
        opacity: 0.8;
         text-align: center;
           border: 1px solid  black; 
 		background-color: orange;

}
     #btn2{
      display: inline-block;
        width: 120px;
        height: 45px;
        background-color: #fff;
        color: black;
        position: relative;
        font-size: 27px;
        left: 1%;
    }
    .form1{
        display: inline-block;
        width: 300px;
        height: 25px;
        margin: 12px;
        position: relative;
    }
    #logo{
        display: inline-block;
        height:120px;
        width: 500px;
        position: relative;
        margin-top: 1px;

    }
    #img{
    width:100px;
    height:120px;
    margin-left:243px;
}
    </style>
</head>
<body>
    <section id="all">
            <section><img id="logo" src="img/logo.jpg" alt="logo"></section>
            <section id="loading_div">
            <h1 >聊天界面</h1>
            <hr>
            <div id="message"></div>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
           <input id="text" type="text" class="form1">
           <button onclick="send()" id="btn2">发送消息</button>
           <hr/>
           <button onclick="closeWebSocket()" id="btn2">关闭连接</button>
           <hr/>
          
            </section>
    </section>
</body>
 <script type="text/javascript">
var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	<%UserDao userDao=new UserDao();%>
websocket = new WebSocket("ws://localhost:8080/LiangZhenJi_OrderedOnlineSystem/newwebsocket/<%=userDao.userNameFindId((String)session.getAttribute("userName"))%>");
}
else {
alert('当前浏览器 Not support websocket')
}
//连接发生错误的回调方法
websocket.onerror = function () {
setMessageInnerHTML("WebSocket连接发生错误");
};
//连接成功建立的回调方法
websocket.onopen = function () {
setMessageInnerHTML("WebSocket连接成功");
}
//接收到消息的回调方法
websocket.onmessage = function (event) {
setMessageInnerHTML(event.data);
}
//连接关闭的回调方法
websocket.onclose = function () {
setMessageInnerHTML("WebSocket连接关闭");
}
//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
closeWebSocket();
}
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
document.getElementById('message').innerHTML += innerHTML + '<br/>';
}
//关闭WebSocket连接
function closeWebSocket() {
websocket.close();
}
//发送消息
function send() {
var message = document.getElementById('text').value;
websocket.send(message);
}
</script>
</html>
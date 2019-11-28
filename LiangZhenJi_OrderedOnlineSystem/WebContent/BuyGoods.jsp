<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付界面</title>
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
        height: 500px;
        width: 600px;
        background-color: orange;
        position: absolute;
        z-index: 300;
        left: 50%;
        top: 50%;
        margin-left: -300px;
        margin-top: -180px;
        padding: 0;
        text-align: right;
        opacity: 0.8;
         text-align: center;

}
    #loading_form{
        display: inline-block;
        position: absolute;
        font-size: 25px;
        z-index: 300;
        left: 50%;
        top: -1%;
        margin-left: -300px;
        padding: 0;
        text-align: center;


}
    #btn1{
        width: 120px;
        height: 55px;
        background-color: #fff;
        color: black;
        position: absolute;
        font-size: 27px;
        left: 48%;
        top: 120%;
    }
    .form1{
        display: inline-block;
        width: 300px;
        height: 30px;
        margin: 30px;
        position: relative;
        margin-right: 65px;
    }
    select{
        display: inline-block;
        font-size:16px;
        height: 30px;
    border: none;
    -webkit-appearance:none;
    cursor: pointer;
    background: url("http://ourjs.github.io/static/2015/arrow.png") no-repeat scroll right center transparent;
    padding-right: 14px;
    }
    #logo{
        display: inline-block;
        height:120px;
        width: 500px;
        position: relative;
        margin-top: 1px;

    }
    div {
            margin: 0;
            padding: 0;
            width: 100%;
            height:  100%;
            background-size: cover;
            position: absolute;
            -webkit-transition: all 2s ease-out;
        }

        .disappear {
             opacity: 0;
        }

        .show {
            opacity: 1;
        }

        #div1{
            width: 300px;
            height: 729px;
            position: absolute;
            left: 0px;
            z-index: 300;
        }#div2{
            width: 300px;
            height: 729px;
            position: absolute;
            right: 0;

            top:20px;
            z-index: 300;
        }
        #bt1
{
    width:40px;
    height:40px;
    border-radius:20px;
    background-image:url('img/1.png');
    background-position:-0px -84px;
    margin:0 10px 0 10px;
    z-index: 2999;
    position:absolute;
    top:500px;
    left:30px ;

}
#bt1:hover
{
    background-position: -588px -84px ;
    cursor: pointer;
}
#bt2
{
    width:40px;
    height:40px;
    border-radius:20px;
    background-image:url('img/1.png');
    background-position:0px -126px;
    margin:0 10px 0 10px;
    z-index: 2999;
    position:absolute;
    top:500px;
    right:30px ;

}#bt2:hover
{
    background-position: -588px -126px ;
    cursor: pointer;
}
    </style>
</head>
<body>
    <section id="all">
            <section style=" display:none;" id="bt1"></section>
            <section style=" display:none;" id="bt2"></section>
            <section id="div1"></section>
            <section id="div2"></section>
            <div class="show"></div>
            <div class="disappear"></div>
            <div class="disappear"></div>
            <div class="disappear"></div>
            <div class="disappear"></div>
            <div class="disappear"></div>
            <section><img id="logo" src="img/logo.jpg" alt="logo"></section>
            <section id="loading_div">
                    <form  id= "loading_form" action="CheckPaypasswordVeiw"  onsubmit="return validate()"  method="post" >
                      <h1 ><%=new String(request.getParameter("goodsName").getBytes("iso-8859-1"),"utf-8") %></h1>
                        支付密码:&nbsp<input type="text" name="payPassword" class="form1"  ><br>
                        <input type="hidden" value=<%=new String(request.getParameter("goodsName").getBytes("iso-8859-1"),"utf-8") %> name="goodsName">
                        <input type="hidden" value=<%=request.getParameter("userKind") %> name="userKind">
                         <% if(request.getAttribute("error")!=null){ %>
				<font face="微软雅黑" color="red" size="3.5"><%=request.getAttribute("error") %></font><br>
		 <%} %>
                        <input id="btn1" type="submit" value="确定"><br>
                    </form>
            </section>
    </section>
    <script>

            window.onload = function(){

                var aDiv = document.getElementsByTagName('div');
                var oDiv1 = document.getElementById('div1');
                var oDiv2 = document.getElementById('div2');
                var oBtn1 =document.getElementById('bt1');
                var oBtn2 =document.getElementById('bt2');
                var k=0;
                for (var i=0 ;i<aDiv.length ;i++){
                    aDiv[i].style.backgroundImage='url(img/'+i+'.jpg)';
                }
                var timer=null;
                var timer1=null;
                move();

                    oBtn1.onmouseover = oDiv1.onmouseover = function (){
                    clearInterval(timer);
                   oBtn1.style.display='block';
                   oBtn2.style.display='none';
                }
                oBtn2.onmouseover = oDiv2.onmouseover = function (){
                    clearInterval(timer);
                   oBtn2.style.display='block';
                   oBtn1.style.display='none';
                }
                oBtn1.onmouseout = oDiv1.onmouseout = oBtn2.onmouseout = oDiv2.onmouseout = function (){
                    oBtn1.style.display='none';
                    oBtn2.style.display='none';
                    move();


                }
                oBtn1.onclick = function(){
                    move2();
                }
                oBtn2.onclick = function(){
                    move1();
                }


            function move(){
                timer = setInterval(function(){
                    for(var j=0 ;j<aDiv.length ;j++)
                    {
                       if(aDiv[j].className =='show'){
                           k=j;
                       }
                    }
                     aDiv[k].className = 'disappear';
                        if(k<aDiv.length-1){
                            aDiv[k+1].className='show';
                           }
                           else {
                               aDiv[0].className ='show';
                            }

                },4000)

            }
            function move1(){
                timer = setTimeout(function(){
                    for(var j=0 ;j<aDiv.length ;j++)
                    {
                       if(aDiv[j].className =='show'){
                           k=j;
                       }
                    }
                     aDiv[k].className = 'disappear';
                        if(k<aDiv.length-1){
                            aDiv[k+1].className='show';
                           }
                           else {
                               aDiv[0].className ='show';
                            }

                },500)

            }function move2(){
                timer = setTimeout(function(){
                    for(var j=0 ;j<aDiv.length ;j++)
                    {
                       if(aDiv[j].className =='show'){
                           k=j;
                       }
                    }
                     aDiv[k].className = 'disappear';
                        if(k>0){
                            aDiv[k-1].className='show';
                           }
                           else {
                               aDiv[5].className ='show';
                            }

                },500)

            }

        }
          //验证表单
            function validate(){
        	  var payPassword=document.getElementsByName('payPassword')[0];
                if(paypassword.value==''){
          
                    alert("支付密码不能为空");
                    return false;
                }
                return true;
            }
        </script>
</body>
</html>
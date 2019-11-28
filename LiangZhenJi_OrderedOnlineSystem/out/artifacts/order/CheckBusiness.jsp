<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.LiangZhenJi.www.po.CheckBusiness"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审核商家店铺</title>
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

}
    #loading_form{
      
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
        left: 0%;
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
            <h1 >审核商家店铺</h1>
            <% 
            List<CheckBusiness> checkBusiness=(List<CheckBusiness>)request.getAttribute("checkBusiness");
            Iterator<CheckBusiness> it=checkBusiness.iterator();
            while(it.hasNext()){
            	CheckBusiness cb=it.next();
            	%>
                    <form  id= "loading_form" action="CheckBusinessVeiw"  method="post" >   
                      <p class="form1" >店铺名称:&nbsp<%=cb.getShopName() %></p><br>
                       <p class="form1" >店主名字:&nbsp<%=cb.getSeller() %></p><br>
                      <input type="hidden" value=<%=cb.getSeller() %> name="checkName">
          <p id="img">店铺图片:&nbsp<img src=<%=cb.getShopPhoto()%> alt="" width="100px" height="100px" ><br></p>
                        <input id="btn2" type="submit" value="审核通过"  >
                   </form>
                        <hr>
         <%} %>
         <% if(request.getAttribute("checkInformation")!=null){ %>
								<script> alert("审核成功"); </script>
		 				<%} %>
            </section>
    </section>
</body>
</html>
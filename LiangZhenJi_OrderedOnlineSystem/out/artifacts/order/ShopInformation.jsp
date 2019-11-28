<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.LiangZhenJi.www.po.Goods"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺界面</title>
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
            <h1 ><%=request.getAttribute("shopName") %></h1>
            <% 
            List<Goods> goodsList=(List<Goods>)request.getAttribute("goodsList");
            Iterator<Goods> it=goodsList.iterator();
            while(it.hasNext()){
            	Goods goods=it.next();
            	%>
                    <form  id= "loading_form" action="ScanGoodsInformationVeiw"  method="post" >
                     <p id="img">商品图片:&nbsp<img src=<%=goods.getGoodsPhoto()%> alt="" width="100px" height="100px" ><br></p>
                      <p class="form1" >商品名称:&nbsp<%=goods.getGoodsName() %></p><br>
                      <input type="hidden" value=<%=goods.getGoodsName() %> name="goodsName">
                      <p class="form1" >价格:&nbsp<%=goods.getPrice()%></p><br>
                      <p class="form1" >好评度:&nbsp<%=goods.getWellReputed()%></p><br>
                      <p class="form1" >销量:&nbsp<%=goods.getSales()%></p><br>
                       <input type="hidden" name="userKind" value=<%=request.getAttribute("userKind") %>>
                        <input id="btn2" type="submit" value="查看详情"  >
                   </form>
                        <hr>
         <%} %>
            </section>
    </section>
</body>
</html>
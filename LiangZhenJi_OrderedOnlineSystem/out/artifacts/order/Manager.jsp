<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*,com.LiangZhenJi.www.po.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/User.css">
    <title>管理员界面</title>
     <style>
#loading_div{
        display: inline-block;
       height:auto;
        width: 625px;
        position: relative;
        z-index: 300;
        margin: 0 auto;
        padding: 0;
        text-align: right;
        opacity: 0.8;
        left:2%;

}
    #loading_form1{
        text-align: center;
        border: 1px solid  black;
 		background-color: orange;
}
     #look{
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
    .page{
        display: inline-block;s
        width: 60px;
        height: 40px;
        margin: 12px;
        position: relative;
        left:-42%;
        color: blue;
    }
    #img{
    width:100px;
    height:120px;
    margin-left:243px;
}
    </style>
</head>
<body>
    <div id="big_container">
        <div id="anmu" style="   display: none;  ">

        </div>
        <nav>
            <ul id="nav_ul1" style="list-style: none;">
                <li >
                    <img src="img/nav.png" alt="nav">
                    <p>网站导航</p>
                </li>
                <li >
                    <p id="loading">欢迎你,<%=session.getAttribute("userName") %></p>
                </li>
                <li >
                    <a  href="Login.jsp" >退出登录</a>
                </li>
            </ul>
            <ul id="nav_ul2" style="list-style: none;">
            	<li >
                    <select onchange="window.location=this.value">
                        <option value ="#">管理网站</option>
                        <option value ="ScanCheckLoginVeiw">审核注册</option>
                        <option value="ScanCheckBusinessVeiw">审核商家店铺</option>
                         <option value="ScanCheckGoodsVeiw">审核上架商品</option>
                    </select>
                </li>
                <li >
                    <select onchange="window.location=this.value">
                        <option value ="#">我的饿了么</option>
                        <option value ="ScanMyOrderVeiw">我的订单</option>
                        <option value="ScanInformationVeiw">我的信息</option>
                    </select>
                </li>
                <li >
                    <img src="img/shopcar.png" alt="shopcar">
                     <a href="ShopCar.jsp?userKind=manager">购物车</a>
                </li>
                <li >
                    <a>用户反馈</a>
                </li>
            </ul>
        </nav>

        <section>
            <div><img id="logo" src="img/logo.jpg" alt="logo"></div>
            <div><img id="logo1" src="img/logo1.jpg" alt="logo1"></div>
             <%if(request.getAttribute("buyInformation")!=null){
                    	  %>
                    	  <script type="text/javascript"> alert("购买成功");</script>
                    <% }%>  
            <div id="sosuokuang">
                <form id="form1" action="LikeSearchVeiw" method="post">
                    <select name="kind">
                        <option value ="goods" name="kind" selected = "selected">商品</option>
                        <option value ="shop" name="kind"  >店家</option>
                    </select>
            </div>
                <input type="text" name="search" id="sosuo">
                <input type="submit" name="ensure" value="搜索" id="sosuo2" >
                <input type="hidden" name="userKind" value="manager">
            </form>
            <form action="" id="form2">
                <p>筛选：</p>
                    <select id="sosuo0">
                        <option value ="goods" name="kind1" selected = "selected">商品</option>
                        <option value ="shop"  name="kind1" >店家</option>
                    </select>
                     <input type="button" value="好评度" id="btn1" onclick="wellReputed()">
                     <input type="button" value="销量" id="btn2" onclick="sales()">
                      <input type="button"  value="价格" id="btn3" onclick="price()">
                      <input type="hidden" name="userKind" value="manager">
 <script type="text/javascript">
                      var  obj=document.getElementById( 'sosuo0' );
                      var  btn3=document.getElementById('btn3');
                      var form=document.getElementById('form2');
                         obj.onclick =  function(){
                              var  index=obj.selectedIndex;
                              
                              if(index==1){
                            	  btn3.style.display='none'; 
                              }
                              else{
                            	  btn3.style.display='inline-block'; 
                              }
                                 
                                  }
                         //对用户选择的好评度销量价格选择的处理
                         function wellReputed(){
                        	 var  index=obj.selectedIndex;
                        	 if(index==0){
                        		 form.action="GoodsWellReputedVeiw";
                        		 form.method="post";
                        		 form.submit();
                        	 }else{
                        		 form.action="ShopWellReputedVeiw";
                        		 form.method="post";
                        		 form.submit();
                        	 }
                         }
                         function sales(){
                        	 var  index=obj.selectedIndex;
                        	 if(index==0){
                        		form.action="GoodsSalesVeiw";
                        		form.method="post";
                        		form.submit();
                        	 }else{
                        		form.action="ShopSalesVeiw";
                        		form.method="post";
                        		form.submit();
                        	 }
                         }
                        	 function price(){
                            	form.action="GoodsPriceVeiw";
                            	form.method="post";
                            	form.submit();
                        	 }
</script>
            </form>
            <hr>
              <%if("goods".equals(request.getAttribute("selectKind"))) {
            %>
             <section id="loading_div">
            <h1 >浏览商品</h1>
            <%
            Page<Goods> pg=(Page<Goods>)session.getAttribute("page");
            List<Goods> goodsList=pg.getList();
            Iterator<Goods> it=goodsList.iterator();
            while(it.hasNext()){
            	Goods goods=it.next();
            	%>
                    <form  id= "loading_form1" action="ScanGoodsInformationVeiw"  method="post" >
                     <p id="img">商品图片:&nbsp<img src=<%=goods.getGoodsPhoto()%> alt="" width="100px" height="100px" ><br></p>
                      <p class="form1" >商品名称:&nbsp<%=goods.getGoodsName() %></p><br>
                      <input type="hidden" value=<%=goods.getGoodsName() %> name="goodsName">
                      <p class="form1" >价格:&nbsp<%=goods.getPrice()%></p><br>
                      <p class="form1" >好评度:&nbsp<%=goods.getWellReputed()%></p><br>
                      <p class="form1" >销量:&nbsp<%=goods.getSales()%></p><br>
                        <input id="look" type="submit" value="查看详情"  >
                         <input type="hidden" name="userKind" value="manager">
                   </form>
                        <hr>
         <%} %>
           <select onchange="window.location=this.value" class="page" >
         <%int totalPage=pg.getTotalPage(); 
         for(int i=1;i<=totalPage;i++){
         %>
         <option value ="GoodsPageVeiw?pageNum=<%=i %>&sortKind=<%=request.getAttribute("sortKind")%>&userKind=manager&msg=<%=request.getAttribute("searchInformation") %>"  >第<%=i %>页</option>
         <%} %>
         </select>
            </section>
            <%}else{ %>
             <section id="loading_div">
            <h1 >浏览商家</h1>
            <%
            Page<Shop> pg=(Page<Shop>)session.getAttribute("page");
            List<Shop> shopList=pg.getList();
            Iterator<Shop> it=shopList.iterator();
            while(it.hasNext()){
            	Shop shop=it.next();
            	%>
                    <form  id= "loading_form1" action="ScanShopInformationVeiw"  method="post" >
                     <p id="img">商家图片:&nbsp<img src=<%=shop.getShopPhoto()%> alt="" width="100px" height="100px" ><br></p>
                      <p class="form1" >商家名称:&nbsp<%=shop.getShopName() %></p><br>
                      <input type="hidden" value=<%=shop.getShopName() %> name="shopName">
                      <p class="form1" >好评度:&nbsp<%=shop.getWellReputed()%></p><br>
                      <p class="form1" >销量:&nbsp<%=shop.getSales()%></p><br>
                        <input id="look" type="submit" value="查看详情"  >
                         <input type="hidden" name="userkind" value="manager">
                   </form>
                        <hr>
         <%} %>
          <select onchange="window.location=this.value" class="page" >
         <%int totalPage=pg.getTotalPage(); 
         for(int i=1;i<=totalPage;i++){
         %>
         <option value ="ShopPageVeiw?pageNum=<%=i %>&sortKind=<%=request.getAttribute("sortKind")%>&userKind=manager&msg=<%=request.getAttribute("searchInformation") %>"  >第<%=i %>页</option>
         <%} %>
         </select>
            </section>
            <%} %>
        </section>
    </div>
</body>
</html>
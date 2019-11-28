<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的评论</title>
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
            <h1 >我的评论</h1>
                    <form  id= "loading_form" action="AddAppraiseVeiw" method="post" onsubmit="return validate()" enctype="multipart/form-data"  > 
                    <input type="hidden" value=<%=request.getAttribute("goodsName") %> name="goodsName">
                    评论:&nbsp<input type="textarea" name="comment" class="form1" ><br>
                       评分:&nbsp<select class="from1" name="grade">
                        <option value ="1"  selected = "selected">1</option>
                        <option value ="2"   >2</option>
                        <option value ="3"   >3</option>
                        <option value ="4"   >4</option>
                        <option value ="5"   >5</option>
                    </select><br>
                      图片:&nbsp<input type="file" name="commentPhoto" class="form1" ><br>
                        <input id="btn2" type="submit" value="提交评论"  >
                   </form>
                        <hr>
            </section>
    </section>
    <script type="text/javascript">
     //验证表单
            function validate(){
        	  var comment=document.getElementsByName('comment')[0];
        	  var commentPhoto=document.getElementsByName('commentPhoto')[0];
                if(comment.value==''){
          
                    alert("评论不能为空");
                    return false;
                }
                if(commentPhoto.value=='' ){
                    alert("必须上传评论图片");
                    return false;
                }
                return true;
            }

        </script>
</body>
</html>
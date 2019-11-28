package com.LiangZhenJi.www.view;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.LiangZhenJi.www.dao.AppraiseDao;
import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.dao.UserDao;
import com.LiangZhenJi.www.po.Appraise;
import com.LiangZhenJi.www.util.UploadFile;

/**
 * Servlet implementation class AddAppraiseVeiw
 */
@WebServlet("/AddAppraiseVeiw")
public class AddAppraiseVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAppraiseVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");  //防止乱码！！！！！！！！
        response.setCharacterEncoding("utf-8");  
        //创建一个DiskFileItemFactory工厂  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //创建一个文件上传解析器  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        //解决上传文件名的中文乱码  
        upload.setHeaderEncoding("UTF-8");   
        factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K  
        File linshi = new File("E:\\linshi");//当超过500K的时候，存到一个临时文件夹中  
        factory.setRepository(linshi);  
        upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M  
        //把用户上传的信息放进审核注册表里
        Appraise appraise=new Appraise();
        String goodsName=null;
        try {  
            // 得到 FileItem 的集合 items  
            @SuppressWarnings("unchecked")
			List<FileItem> fileItemsList = upload.parseRequest(request);  
            Iterator<FileItem> fileItems=fileItemsList.iterator();
            // 遍历 items:  
           while(fileItems.hasNext()){  
        	   FileItem fileItem = fileItems.next();
                // 一般的表单域 
                if (fileItem.isFormField()) {  
                	if("comment".equals(fileItem.getFieldName())) {
                		appraise.setComment(fileItem.getString("utf-8"));
                	} 
                	if("goodsName".equals(fileItem.getFieldName())) {
                		goodsName=fileItem.getString("utf-8");
                		GoodsDao goodsDao=new GoodsDao();
                		int goodsId=goodsDao.goodsNameFindId(goodsName);
                		appraise.setGoodsId(goodsId);
                	}
                	if("grade".equals(fileItem.getFieldName())) {
                		int grade=Integer.parseInt(fileItem.getString("utf-8"));
                		appraise.setGrade(grade);
                	} 
                }  
             
                else {  
                    String fileName = fileItem.getName();  
                    //创建新文件名
                    String suffix = fileName.substring(fileName.lastIndexOf('.')); 
                    String newFileName = new Date().getTime() + suffix;
                    //把图片路径写入数据库中
                    appraise.setCommentPhoto("upload/comment_photoImg/"+newFileName);
                    String realPath = getServletContext().getRealPath("/upload/comment_photoImg/");
                    newFileName = realPath + "\\" + newFileName;//文件最终上传的位置 ,上传到服务器中 
                    //文件上传
                    UploadFile.upload(fileItem.getInputStream(), newFileName);
            }  
  
        }
      } catch (FileUploadException e) {  
            e.printStackTrace();  
        }
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        UserDao userDao =new UserDao();
        int userId=userDao.userNameFindId(userName);
        appraise.setUserId(userId);
        //增加评论
        AppraiseDao  appraiseDao=new  AppraiseDao();
        appraiseDao.add(userName, goodsName, appraise);
        //增加商品好评度
        GoodsDao goodsDao=new GoodsDao();
        goodsDao.addReputed(goodsName, userName);
        //增加店家好评度
        ShopDao shopDao=new ShopDao();
        shopDao.addReput(goodsName, userName);
        String AppraiseInformation="评论成功";
        request.setAttribute("AppraiseInformation",AppraiseInformation);
        RequestDispatcher rd=request.getRequestDispatcher("ScanMyOrderVeiw");//跳转到评论的界面
		rd.forward(request, response);
	}
	 

}

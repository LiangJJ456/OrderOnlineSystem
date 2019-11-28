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

import com.LiangZhenJi.www.dao.UserDao;
import com.LiangZhenJi.www.po.User;
import com.LiangZhenJi.www.util.UploadFile;

/**
 * Servlet implementation class UpdateInformation
 */
@WebServlet("/UpdateInformationVeiw")
public class UpdateInformationVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInformationVeiw() {
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
        User user=new User();
        try {  
            // 得到 FileItem 的集合 items  
            @SuppressWarnings("unchecked")
			List<FileItem> fileItemsList = upload.parseRequest(request);  
            Iterator<FileItem> fileItems=fileItemsList.iterator();
            // 遍历 items:  
           while(fileItems.hasNext()){  
        	   FileItem fileItem = fileItems.next();
                // 一般的表单域,   
                if (fileItem.isFormField()) {  
                	if("userName".equals(fileItem.getFieldName())) {
                		user.setUserName(fileItem.getString("utf-8"));
                	}
                	if("password".equals(fileItem.getFieldName())) {
                		user.setPassword(fileItem.getString("utf-8"));
                	}
                	if("sex".equals(fileItem.getFieldName())) {
                		user.setSex(fileItem.getString("utf-8"));
                	}
                	if("payPassword".equals(fileItem.getFieldName())) {
                		user.setPayPassword(fileItem.getString("utf-8"));
                	}
                	if("address".equals(fileItem.getFieldName())) {
                		user.setAddress(fileItem.getString("utf-8"));
                	}  
                      
                      
                }  
             
                else {
                	//登录时的用户名
                    HttpSession session=request.getSession();
                    String userName=(String)session.getAttribute("userName");
                    UserDao userDao=new UserDao();
                    String oldPhoto=userDao.find(userName).getPersonPhoto();//获得未修改前的文件路径
                    String oldPath= getServletContext().getRealPath("");
                    File oldFile=new File(oldPath+oldPhoto);
                    oldFile.delete();//删除服务器中的旧文件
                    //
                    String fileName = fileItem.getName();  
                    //创建新文件名
                    String suffix = fileName.substring(fileName.lastIndexOf('.')); 
                    String newFileName = new Date().getTime() + suffix;
                    //把图片路径写入数据库中
                    user.setPersonPhoto("upload/personImg/"+newFileName);
                    String realPath = getServletContext().getRealPath("/upload/personImg/");
                    newFileName = realPath + "\\" + newFileName;//文件最终上传的位置 ,上传到服务器中
                  //文件上传
                    UploadFile.upload(fileItem.getInputStream(), newFileName);  
                }  
            }  
  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }
        //登录时的用户名
        HttpSession session=request.getSession();
        String userName=(String)session.getAttribute("userName");
        //修改会话中的用户名
        session.setAttribute("userName", user.getUserName());
        UserDao userDao=new UserDao();
        userDao.updateUserInformation(user, userName);
       String information="修改成功";
       request.setAttribute("information", information);
       //跳转回个人信息界面
       RequestDispatcher rd=request.getRequestDispatcher("ScanInformationVeiw");
       rd.forward(request, response);
	}

}

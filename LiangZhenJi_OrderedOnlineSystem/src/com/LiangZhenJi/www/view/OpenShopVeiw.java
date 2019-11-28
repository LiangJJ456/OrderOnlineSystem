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

import com.LiangZhenJi.www.dao.CheckBusinessDao;
import com.LiangZhenJi.www.po.CheckBusiness;
import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.po.Page;
import com.LiangZhenJi.www.service.PagingFunctionService;
import com.LiangZhenJi.www.util.UploadFile;

/**
 * Servlet implementation class OpenShopVeiw
 */
@WebServlet("/OpenShopVeiw")
public class OpenShopVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenShopVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

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
        CheckBusiness cb=new CheckBusiness();
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
                	if("shopName".equals(fileItem.getFieldName())) {
                		cb.setShopName(fileItem.getString("utf-8"));
                	}     
                }  
             
                else {  
                    String fileName = fileItem.getName();  
                    //创建新文件名
                    String suffix = fileName.substring(fileName.lastIndexOf('.')); 
                    String newFileName = new Date().getTime() + suffix;
                    //把图片路径写入数据库中
                    cb.setShopPhoto("upload/shopImg/"+newFileName);
                    String realPath = getServletContext().getRealPath("/upload/shopImg/");
                    newFileName = realPath + "\\" + newFileName;//文件最终上传的位置 ,上传到服务器中
                  //文件上传
                    UploadFile.upload(fileItem.getInputStream(), newFileName); 
                }  
            }  
  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }
        //把用户上传的店铺信息放进审核表中
        HttpSession session=request.getSession();
        cb.setSeller((String)session.getAttribute("userName"));
        CheckBusinessDao checkBusinessDao=new CheckBusinessDao(); 
        checkBusinessDao.add(cb);
       String information="注册店铺信息已发送，请等待审核";
       request.setAttribute("information", information);
       //跳转回用户界面
     //获取商品页面信息
     		int pageNum=1;
     		//获取第一页的商品信息，每页有5个商品
     		Page<Goods> pg=PagingFunctionService.getGoodsWithPage(pageNum, 5);
     		session.setAttribute("page",pg);//建立一个商品页面的session
     		//跳转回用户界面时判断要展示的为商家还是商品
       		String selectKind="goods";
       		request.setAttribute("selectKind", selectKind);
       		//跳转回用户界面时判断按什么排序展示,便于下一页商品或店家按什么顺序展示
       		String sortKind="noSort";
       		request.setAttribute("sortKind", sortKind);
       RequestDispatcher rd=request.getRequestDispatcher("User.jsp");
       rd.forward(request, response);
	}

}

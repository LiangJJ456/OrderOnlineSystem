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

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.util.UploadFile;

/**
 * Servlet implementation class DealGoodsVeiw
 */
@WebServlet("/DealGoodsVeiw")
public class DealGoodsVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealGoodsVeiw() {
        super();
        // TODO Auto-generated constructor stub
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
        Goods goods=new Goods();
        //获得未修改前的商品名字
        String oldGoodsName=null;
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
                	if("goodsName".equals(fileItem.getFieldName())) {
                		goods.setGoodsName(fileItem.getString("utf-8"));
                	}
                	if("oldGoodsName".equals(fileItem.getFieldName())) {
                		oldGoodsName=fileItem.getString("utf-8");
                	}
                	if("price".equals(fileItem.getFieldName())) {
                		int price=Integer.parseInt(fileItem.getString("utf-8"));
                		goods.setPrice(price);
                	}
                	if("goodsKind".equals(fileItem.getFieldName())) {
                		goods.setGoodsKind(fileItem.getString("utf-8"));
                	}
                }  
             
                else {
                	GoodsDao goodsDao=new GoodsDao();
                    String oldPhoto=goodsDao.Find(oldGoodsName).getGoodsPhoto();//获得未修改前的文件路径
                    String oldPath= getServletContext().getRealPath("");
                    File oldFile=new File(oldPath+oldPhoto);
                    oldFile.delete();//删除服务器中的旧文件
                    //
                    String fileName = fileItem.getName();  
                    //创建新文件名
                    String suffix = fileName.substring(fileName.lastIndexOf('.')); 
                    String newFileName = new Date().getTime() + suffix;
                    //把图片路径写入数据库中
                    goods.setGoodsPhoto("upload/goodsImg/"+newFileName);
                    String realPath = getServletContext().getRealPath("/upload/goodsImg/");
                    newFileName = realPath + "\\" + newFileName;//文件最终上传的位置 ,上传到服务器中
                  //文件上传
                    UploadFile.upload(fileItem.getInputStream(), newFileName);
                }  
            }  
  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }
        GoodsDao goodsDao=new GoodsDao();
        goodsDao.updateGoodsInformation(goods, oldGoodsName);
        String UpdateInformation="修改成功";
        request.setAttribute("UpdateInformation", UpdateInformation);
        HttpSession session=request.getSession();
        ShopDao shopDao=new ShopDao();
		String shopName=shopDao.sellerFindShopName((String)session.getAttribute("userName"));
		List<Goods> shopGoodsList=goodsDao.shopNameFindGoods(shopName);
		request.setAttribute("shopGoodsList", shopGoodsList);//找到店铺的商品
		RequestDispatcher rd=request.getRequestDispatcher("DealGoods.jsp");//跳转到管理商品的界面
		rd.forward(request, response);
      
	}

}

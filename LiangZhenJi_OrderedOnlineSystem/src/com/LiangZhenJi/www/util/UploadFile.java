package com.LiangZhenJi.www.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadFile {
	/**
	 * 上传文件
	 * @param in
	 * @param newFileName
	 */
	public static void upload(InputStream in,String newFileName){
		//写入文件 
        byte[] buffer = new byte[1024];  
        int len = 0; 
        OutputStream out = null;
		try {
			out = new FileOutputStream(newFileName);
			try {
				while ((len = in.read(buffer)) != -1) {  
				    out.write(buffer, 0, len);  
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				out.close();
				 in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

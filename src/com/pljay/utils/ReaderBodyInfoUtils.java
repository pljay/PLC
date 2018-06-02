package com.pljay.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;

 
public class ReaderBodyInfoUtils
{

	private static Logger logger=Logger.getLogger(ReaderBodyInfoUtils.class);
	public static String ReadBody(HttpServletRequest request)
	{
		StringBuffer sb = null;
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null)
			{
				sb.append(temp);
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
	public static  List binaryReader(HttpServletRequest request,String uploadpath) {
		logger.info(System.getProperty("file.encoding"));
		List valuelist=new ArrayList<>();
		Map<String, String> map=new HashMap<>();
		String token=null;
		logger.info(request.getContentLength());
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置文件上传路径
		File file = new File(uploadpath);
		//判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			logger.info(uploadpath+"目录不存在，需要创建");
			//创建目录
			file.mkdir();
		}
		//String upload = servletContext.getRealPath("/");
		logger.info(uploadpath);
		// 获取系统默认的临时文件保存路径，该路径为Tomcat根目录下的temp文件夹
		String temp = System.getProperty("java.io.tmpdir");
		logger.info(temp);
		// 设置缓冲区大小为 5M
		factory.setSizeThreshold(1024 * 1024 * 5);
		// 设置临时文件夹为temp 
		factory.setRepository(new File(temp));
		// 用工厂实例化上传组件,ServletFileUpload 用来解析文件上传请求
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		// 解析结果放在List中

		List<FileItem> list=new ArrayList<>();
		try {
			list = servletFileUpload.parseRequest(request);
			logger.info(list.size());
			for (int i=0;i<list.size();i++)
			{
				FileItem item=list.get(i);
				logger.info(item.getFieldName());
				InputStream is = null;
				try {
					is = item.getInputStream();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (item.isFormField())
				{
					token=ReaderBodyInfoUtils.inputStream2String(is);
					map.put(item.getFieldName(), token);
					logger.info(token);
				} else  {

					inputStream2File(is, uploadpath +item.getName());
					map.put(item.getFieldName(), item.getName());
				}
				
			}
			valuelist.add(map);
		} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		return valuelist;
	}
	// 流转化成字符串
	public static  String inputStream2String(InputStream is) 
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		try {
			while ((i = is.read()) != -1)
			{
				baos.write(i);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toString();
	}

	// 流转化成文件
	public static Boolean inputStream2File(InputStream is, String savePath)

	{
		logger.info("文件保存路径为:" + savePath);
		try {
			File file = new File(savePath);
			InputStream inputSteam = is;
			BufferedInputStream fis = new BufferedInputStream(inputSteam);
			FileOutputStream fos = new FileOutputStream(file);
			int f;

			while ((f = fis.read()) != -1)
			{
				fos.write(f);
			}

			fos.flush();

			fos.close();
			fis.close();
			inputSteam.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public int hashCode()
	{
		return super.hashCode();
	}

	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}

	protected Object clone()
			throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String toString()
	{
		return super.toString();
	}

	protected void finalize()
			throws Throwable
	{
		super.finalize();
	}
}



package com.pljay.ziyuan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pljay.utils.ReaderBodyInfoUtils;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(description = "�ϴ�", urlPatterns = { "/UploadServlet" })
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		String path=request.getSession().getServletContext().getRealPath("/uploads/");
		String filename="";
		String url="";
		int id;
		System.out.println(path);
		List<String> list = ReaderBodyInfoUtils.binaryReader(request, path+"\\");
		for (String object : list) {
			System.out.println(object);
		}
		filename=(String) list.get(0);
		url="/uploads/"+list.get(1);
		System.out.println(url);
	}

}

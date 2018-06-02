package com.pljay.basicmanager.teacher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ReaderBodyInfoUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class UpdateTeacher
 */
@WebServlet(description = "更新教师", urlPatterns = { "/UpdateTeacher" })
public class UpdateTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(UpdateTeacher.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		String path=request.getSession().getServletContext().getRealPath("/uploads/image");
		String id = request.getParameter("id");
		logger.info(id); 
		Connection connection = Mysql.getInstance().getConnection();
		List<Map<String, String>> list = ReaderBodyInfoUtils.binaryReader(request, path+"\\");
		try {
			for (Map<String, String> map : list) {
				String sql="UPDATE `plc`.teacher\r\n" + 
						"SET `rolelid` = (\r\n" + 
						"	SELECT\r\n" + 
						"		id\r\n" + 
						"	FROM\r\n" + 
						"		plc.role\r\n" + 
						"	WHERE\r\n" + 
						"		plc.role.rolename = '"+map.get("level")+"'\r\n" + 
						"),"+
						" `name` = '"+map.get("teachername")+"',\r\n" + 
						" `password` = '"+map.get("password")+"',\r\n" + 
						" `image` = '/uploads/image/"+map.get("image")+"',\r\n" + 
						" `teachertitleid` = "+map.get("teacher_type")+",\r\n" + 
						" `teacherdiscribe` = '"+map.get("jianjie")+"'\r\n" + 
						"WHERE\r\n" + 
						"	`id` = '"+id+"'" ;
				logger.info(sql);

				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.executeUpdate();
				prepareStatement.close();
			}
			ResponseUtils.renderJson(response,"application/json","更新成功，请关闭当前窗口！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

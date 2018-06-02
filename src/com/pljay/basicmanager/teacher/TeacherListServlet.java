package com.pljay.basicmanager.teacher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pljay.bean.Pages;
import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class TeacherListServlet
 */
@WebServlet(description = "教师列表", urlPatterns = { "/TeacherList" })
public class TeacherListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(TeacherListServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TeacherListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer pagenumber = Integer.parseInt(request.getParameter("pageNumber"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String sql="SELECT\r\n" + 
				"	a.id,\r\n" + 
				"	a. NAME,\r\n" + 
				"	a.`password`,\r\n" + 
				"	a.image,\r\n" + 
				"	a.teacherdiscribe,\r\n" + 
				"	b.teachertitle,\r\n" + 
				"	c.rolename,\r\n" + 
				"	c.`level`\r\n" + 
				"FROM\r\n" + 
				"	plc.teacher AS a\r\n" + 
				"LEFT JOIN plc.teachertitle AS b ON a.teachertitleid = b.id\r\n" + 
				"LEFT JOIN plc.role AS c ON a.rolelid = c.id\r\n"+
				"limit \r\n "+(pagenumber-1)*pageSize+","+pageSize;
		logger.info(sql);
		String sql2="SELECT COUNT(a.ID) AS total FROM plc.teacher as a\r\n";
		Integer total = null;
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List teacherlist=new ArrayList<>();
			while(resultSet.next()) {
				Map map=new HashMap<>();
				map.put("ID", resultSet.getInt("ID"));
				map.put("NAME", resultSet.getString("NAME"));
				map.put("password", resultSet.getString("password"));
				map.put("image", resultSet.getString("image"));
				map.put("teachertitle", resultSet.getString("teachertitle"));
				map.put("teacherdiscribe", resultSet.getString("teacherdiscribe"));
				map.put("rolename", resultSet.getString("rolename"));
				map.put("level", resultSet.getString("level"));
				teacherlist.add(map);
			}
			prepareStatement.close();
			resultSet.close();
			PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
			ResultSet resultSet2 = prepareStatement2.executeQuery();
			while(resultSet2.next()) {
				total=resultSet2.getInt("total");
			}
			resultSet.close();
			prepareStatement.close();
			Pages pages = new Pages(total, pagenumber, pageSize, teacherlist);
			logger.info(JsonUtils.objectToJsonNotNull(pages)); 
			ResponseUtils.renderJson(response, JsonUtils.objectToJsonNotNull(pages));
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

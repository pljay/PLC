package com.pljay.basicmanager.information;
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

import com.pljay.bean.Pages;
import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class SubjectListServlet
 */
@WebServlet(description = "通知列表", urlPatterns = { "/InformationList" })
public class InformationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InformationListServlet() {
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
				"	id,\r\n" + 
				"	title,\r\n" + 
				"	content,\r\n" + 
				"	date,\r\n" + 
				"	type\r\n" + 
				"FROM\r\n" + 
				"	plc.information ORDER BY date DESC \r\n"+
				"limit \r\n "+(pagenumber-1)*pageSize+","+pageSize;
		System.out.println(sql);
		String sql2="SELECT COUNT(*) AS total "+
				"FROM\r\n" + 
				"	plc.`information`\r\n";
		int total = 0;
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List<Map> list=new ArrayList<>();
			while(resultSet.next()) {
				Map map=new HashMap<>();
				map.put("id", resultSet.getInt("id"));
				map.put("title", resultSet.getString("title"));
				map.put("content", resultSet.getString("content"));
				map.put("date", resultSet.getDate("date"));
				map.put("type", resultSet.getString("type"));
				list.add(map);
			}
			resultSet.close();
			prepareStatement.close();
			PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
			ResultSet resultSet2 = prepareStatement2.executeQuery();
			while(resultSet2.next()) {
				total=resultSet2.getInt("total");
			}
			prepareStatement2.close();
			resultSet2.close();
			Pages pages = new Pages(total, pagenumber, pageSize, list);
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

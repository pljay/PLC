package com.pljay.ziyuan;

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

import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class ZiYuanListServlet
 */
@WebServlet(description = "资源列表", urlPatterns = { "/ZiYuanListServlet" })
public class ZiYuanListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ZiYuanListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sql="SELECT\r\n" + 
				"	id,\r\n" + 
				"	typename\r\n" + 
				"FROM\r\n" + 
				"	plc.resourcetype";
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List<Map> list=new ArrayList<>();
			while(resultSet.next()) {
				Map map=new HashMap<>();
				map.put("id", resultSet.getInt("id"));
				map.put("typename", resultSet.getString("typename"));
				list.add(map);
			}
			resultSet.close();
			prepareStatement.close();
			ResponseUtils.renderJson(response, JsonUtils.objectToJsonNotNull(list));
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

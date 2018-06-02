package com.pljay.admin;

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
 * Servlet implementation class SchoolManageServlet
 */
@WebServlet(description = "学校管理", urlPatterns = { "/SchoolManage" })
public class SchoolManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(SchoolManageServlet.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchoolManageServlet() {
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
				"	ID,\r\n" + 
				"	ITEM_SCHOOLID,\r\n" + 
				"	ITEM_CAMPUSTRA\r\n" + 
				"FROM\r\n" + 
				"	we.`tlk_校园信息`";
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List<Map<String, String>> list = new ArrayList<>();
			while(resultSet.next()) {
				Map<String, String> map=new HashMap<>();
				map.put("ID", resultSet.getString("id"));
				map.put("ITEM_CAMPUSTRA", resultSet.getString("ITEM_CAMPUSTRA"));
				map.put("ITEM_SCHOOLID", resultSet.getString("ITEM_SCHOOLID"));
				list.add(map);
			}
			Pages pages = new Pages(list.size(), pagenumber, pageSize, list);
			resultSet.close();
			prepareStatement.close();

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

package com.pljay.basicmanager.resource;

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
 * Servlet implementation class ListServlet
 */
@WebServlet(description = "资源列表", urlPatterns = { "/Resourcelist" })
public class ResourcelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResourcelistServlet() {
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
				"	a.resourcename,\r\n" + 
				"	a.resource,\r\n" + 
				"	b.typename\r\n" + 
				"FROM\r\n" + 
				"	plc.`resource` AS a\r\n" + 
				"LEFT JOIN plc.resourcetype AS b ON a.typeid = b.id\r\n"+
				"limit \r\n "+(pagenumber-1)*pageSize+","+pageSize;
		System.out.println(sql);
		String sql2="SELECT COUNT(*) AS total "+
				"FROM\r\n" + 
				"	plc.`resource`\r\n";
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List list=new ArrayList<>();
			Integer total=0;
			while(resultSet.next()) {
				Map map=new HashMap<>();
				map.put("ID", resultSet.getInt("ID"));
				map.put("resourcename", resultSet.getString("resourcename"));
				map.put("resource", resultSet.getString("resource"));
				map.put("typename", resultSet.getString("typename"));
				list.add(map);
			}
			prepareStatement.close();
			resultSet.close();
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

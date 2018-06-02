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
 * Servlet implementation class UserManageServlet
 */
@WebServlet(description = "用户管理", urlPatterns = { "/UserManage" })
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(UserManageServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("用户管理");
		Integer pagenumber = Integer.parseInt(request.getParameter("pageNumber"));
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		logger.info(pagenumber);
		logger.info(pageSize);
		String sql="SELECT\r\n" +  
				"	a.ID as userid,\r\n" + 
				"	a.ITEM_NAME,\r\n" + 
				"	a.ITEM_ACCOUNT,\r\n" + 
				"	a.ITEM_JUESE,\r\n" + 
				"	a.ITEM_ORGNAME ,\r\n"+
				"   a.ITEM_PHONE,\r\n" + 
				"	a.ITEM_EMAIL,\r\n" + 
				"	a.ITEM_PASS,\r\n" + 
				"	a.ITEM_CLASSID,\r\n" + 
				"	a.ITEM_TEACHERID,\r\n" + 
				"	a.ITEM_ORGID,\r\n" + 
				"	b.ITEM_CAMPUSNAME\r\n" + 
				"FROM\r\n" + 
				"	we.`tlk_账户管理` as a \r\n"+
				"LEFT JOIN we.`tlk_校园信息` AS b ON a.item_schoolid = b.ID \r\n"+
				"limit \r\n "+(pagenumber-1)*pageSize+","+pageSize;
		String sql2="SELECT COUNT(a.ID) AS total FROM we.`tlk_账户管理` as a\r\n"+
				"LEFT JOIN we.`tlk_校园信息` AS b ON a.item_schoolid = b.ID \r\n";	
		logger.info(sql);
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet= prepareStatement.executeQuery();
			Integer total=0;
			List<Map> list = new ArrayList<>();
			while(resultSet.next()) {
				Map map=new HashMap<>();
				map.put("ID", resultSet.getString("userid"));
				map.put("ITEM_NAME", resultSet.getString("ITEM_NAME"));
				map.put("ITEM_ACCOUNT", resultSet.getString("ITEM_ACCOUNT"));
				map.put("ITEM_JUESE", resultSet.getString("ITEM_JUESE"));
				map.put("ITEM_ORGNAME", resultSet.getString("ITEM_ORGNAME"));
				map.put("ITEM_PHONE", resultSet.getString("ITEM_PHONE"));
				map.put("ITEM_PASS", resultSet.getString("ITEM_PASS"));
				map.put("ITEM_CLASSID", resultSet.getString("ITEM_CLASSID"));
				map.put("ITEM_TEACHERID", resultSet.getString("ITEM_TEACHERID"));
				map.put("ITEM_ORGID", resultSet.getString("ITEM_ORGID"));
				map.put("ITEM_CAMPUSNAME", resultSet.getString("ITEM_CAMPUSNAME"));
				list.add(map);
			}
			PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
			ResultSet resultSet2 = prepareStatement2.executeQuery();
			while(resultSet2.next()) {
				total=resultSet2.getInt("total");
			}
			resultSet.close();
			prepareStatement.close();
			Pages pages = new Pages(total, pagenumber, pageSize, list);
			logger.info(JsonUtils.objectToJsonNotNull(list));
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

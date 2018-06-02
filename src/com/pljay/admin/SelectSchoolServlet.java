package com.pljay.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class SelectSchoolServlet
 */
@WebServlet(description = "查询学校信息", urlPatterns = { "/SelectSchool" })
public class SelectSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger=Logger.getLogger(SelectSchoolServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSchoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String schoolid = request.getParameter("schoolid");
		logger.info(schoolid);
		String sql="SELECT\r\n" + 
				"	*\r\n" + 
				"FROM\r\n" + 
				"	xiaoxuan.`tlk_校园信息` where id='"+schoolid+"'";
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			Map<String, String> map=new HashMap<>();
			while(resultSet.next()) {
				map.put("ID",resultSet.getString("ID"));
				map.put("ITEM_CAMPUSNAME",resultSet.getString("ITEM_CAMPUSNAME"));
				map.put("ITEM_CAMPUSTRA",resultSet.getString("ITEM_CAMPUSTRA"));
				map.put("BUMENID",resultSet.getString("BUMENID"));
				map.put("ITEM_WEIXINID",resultSet.getString("ITEM_WEIXINID"));
				map.put("ITEM_SCHOOLID",resultSet.getString("ITEM_SCHOOLID"));
				map.put("ITEM_Abbreviation",resultSet.getString("ITEM_Abbreviation"));
				map.put("ITEM_Location",resultSet.getString("ITEM_Location"));
				map.put("ITEM_Address",resultSet.getString("ITEM_Address"));
				map.put("ITEM_ZipCode",resultSet.getString("ITEM_ZipCode"));
				map.put("ITEM_Contacts",resultSet.getString("ITEM_Contacts"));
				map.put("ITEM_Contacts_tel",resultSet.getString("ITEM_Contacts_tel"));
				map.put("ITEM_Contacts_qq",resultSet.getString("ITEM_Contacts_qq"));
				map.put("ITEM_Contacts_mail",resultSet.getString("ITEM_Contacts_mail"));
				map.put("ITEM_School_type",resultSet.getString("ITEM_School_type"));
			}
			prepareStatement.close();
			resultSet.close();
			ResponseUtils.renderJson(response, JsonUtils.objectToJsonNotNull(map));
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

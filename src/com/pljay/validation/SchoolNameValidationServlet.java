package com.pljay.validation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pljay.bean.Validation;
import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class SchoolNameValidationServlet
 */
@WebServlet(description = "瀛︽牎鍚嶉獙璇�", urlPatterns = { "/SchoolNameValidationServlet" })
public class SchoolNameValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(SchoolNameValidationServlet.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SchoolNameValidationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String schoolname = request.getParameter("schoolname");
		String schoolid = request.getParameter("schoolid");
		logger.info("schoolid==="+schoolid+"\nserialnumber==="+schoolname);
		String sql="SELECT\r\n" + 
				"	*\r\n" + 
				"FROM\r\n" + 
				"xiaoxuan.`tlk_鏍″洯淇℃伅`\r\n" + 
				"WHERE\r\n" + 
				"	ITEM_CAMPUSNAME ='"+schoolname+"' and id <>'"+schoolid+"'";
		logger.info(sql);
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			Validation validation;
			if(resultSet.next()) {
				validation = new Validation(false);
			}else {
				validation = new Validation(true);
			}
			ResponseUtils.renderJson(response, JsonUtils.objectToJsonNotNull(validation));
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


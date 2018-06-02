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
 * Servlet implementation class SerialNumberValidation
 */
@WebServlet(description = "学校编号验证", urlPatterns = { "/SerialNumberValidation" })
public class SerialNumberValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(SerialNumberValidation.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SerialNumberValidation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String serialnumber = request.getParameter("serialnumber");
		String schoolid = request.getParameter("schoolid");
		logger.info("schoolid==="+schoolid+"\nserialnumber==="+serialnumber);
		String sql="SELECT\r\n" + 
				"	*\r\n" + 
				"FROM\r\n" + 
				"xiaoxuan.`tlk_校园信息`\r\n" + 
				"WHERE\r\n" + 
				"	item_schoolid= '"+serialnumber+"' and id <>'"+schoolid+"'";
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

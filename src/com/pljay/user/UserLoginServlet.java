package com.pljay.user;

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
import javax.websocket.MessageHandler.Whole;

import org.apache.log4j.Logger;

import com.pljay.jdbc.Mysql;
import com.pljay.utils.JsonUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet(description = "登录", urlPatterns = { "/LoginServlet" })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger=Logger.getLogger(UserLoginServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
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
		String username = request.getParameter("name");
		String rolename= request.getParameter("level");
		String password = request.getParameter("password");
		String sql="";
		String message="";
		switch (rolename) {
		case "teacher":
			sql="SELECT\r\n" + 
					"	*\r\n" + 
					"FROM\r\n" + 
					"	plc.teacher\r\n" + 
					"WHERE\r\n" + 
					"	plc.teacher.`name` = '"+username+"'\r\n" + 
					"AND plc.teacher.`password` = '"+password+"'";
			break;
		case "student":
			sql="SELECT\r\n" + 
					"	*\r\n" + 
					"FROM\r\n" + 
					"	plc.student\r\n" + 
					"WHERE\r\n" + 
					"	plc.student.`name` = '"+username+"'\r\n" + 
					"AND plc.student.`password` = '"+password+"'";
			
			break;
		case "admin":
	        if(username.equals("admin")&&password.equals("123")) {
	        	message= "success";
	        }else {
	        	message= "fail";
			}
			break;

		default:
			break;
		}
		Connection connection = Mysql.getInstance().getConnection();
		try {
			System.out.println(rolename);
			if(!rolename.equals("admin") ) {
				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				logger.info(sql);
				ResultSet executeQuery = prepareStatement.executeQuery();
				if(executeQuery.next()) {
					message="success";
				}else {
					message="fail";
				}
				
			}
				ResponseUtils.renderJson(response, message);
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

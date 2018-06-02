package com.pljay.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pljay.jdbc.Mysql;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class AddRole
 */
@WebServlet(description = "更新权限", urlPatterns = { "/UpdateRole" })
public class UpdateRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(UpdateRole.class);
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRole() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String rolename = request.getParameter("rolename");
		int level = Integer.parseInt(request.getParameter("level"));
		String sql="UPDATE `plc`.`role`\r\n" + 
				"SET \r\n" + 
				" `rolename` = '"+rolename+"',\r\n" + 
				" `level` = '"+level+"'\r\n" + 
				"WHERE\r\n" + 
				"	(`id` = '"+id+"')" ; 
		logger.info(sql);
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.executeUpdate();
			prepareStatement.close();
			ResponseUtils.renderJson(response,"application/json","添加成功，请关闭当前窗口！");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

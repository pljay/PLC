package com.pljay.basicmanager.teacher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pljay.jdbc.Mysql;
import com.pljay.utils.ReaderBodyInfoUtils;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class AddTeacher
 */
@WebServlet(description = "添加老师", urlPatterns = { "/AddTeacher" })
public class AddTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(AddTeacher.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeacher() {
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
		String path=request.getSession().getServletContext().getRealPath("/uploads/image");
		Connection connection = Mysql.getInstance().getConnection();
		List<Map<String, String>> list = ReaderBodyInfoUtils.binaryReader(request, path+"\\");
		try {
			for (Map<String, String> map : list) {
				String sql="INSERT INTO `plc`.`teacher` (\r\n" + 
						"	`rolelid`,\r\n" + 
						"	`name`,\r\n" + 
						"	`password`,\r\n" + 
						"	`image`,\r\n" + 
						"	`teachertitleid`,\r\n" + 
						"	`teacherdiscribe`\r\n" + 
						")\r\n" + 
						"VALUES\r\n" + 
						"	(\r\n" + 
						"		\r\n" + 
						"		(SELECT\r\n" + 
						"			id\r\n" + 
						"		FROM\r\n" + 
						"			plc.role\r\n" + 
						"		WHERE\r\n" + 
						"			plc.role.rolename = '"+map.get("level")+"')\r\n" + 
						"	  ,\r\n" + 
						"		'"+map.get("teachername")+"',\r\n" + 
						"		'"+map.get("password")+"',\r\n" + 
						"		'/uploads/image/"+map.get("image")+"',\r\n" +  
						"		(SELECT id FROM plc.teachertitle WHERE plc.teachertitle.teachertitle='"+map.get("teacher_type")+"'),\r\n" + 
						"		'"+map.get("jianjie")+"'\r\n" + 
						"	)";
				logger.info(sql);

				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.execute();
				prepareStatement.close();
			}
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

}

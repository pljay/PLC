package com.pljay.basicmanager.resource;

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
 * Servlet implementation class AddResource
 */
@WebServlet(description = "添加资源", urlPatterns = { "/AddResource" })
public class AddResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(AddResource.class);
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		String path=request.getSession().getServletContext().getRealPath("/uploads/resource");
		Connection connection = Mysql.getInstance().getConnection();
		List<Map<String, String>> list = ReaderBodyInfoUtils.binaryReader(request, path+"\\");
		try {
			for (Map<String, String> map : list) {
				String sql="INSERT INTO `plc`.`resource` (\r\n" + 
						"	`resourcename`,\r\n" + 
						"	`resource`,\r\n" + 
						"	`typeid`\r\n" + 
						")\r\n" + 
						"VALUES\r\n" + 
						"	(\r\n" + 
						"		'"+map.get("name")+"',\r\n" + 
						"		'/uploads/resource"+map.get("file")+"',\r\n" + 
						"		'"+map.get("resource_type")+"'\r\n" + 
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

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
 * Servlet implementation class UpdateResource
 */
@WebServlet(description = "更新资源", urlPatterns = { "/UpdateResource" })
public class UpdateResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger(UpdateResource.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		int id =Integer.parseInt(request.getParameter("id"));
		String path=request.getSession().getServletContext().getRealPath("/uploads/resource");
		Connection connection = Mysql.getInstance().getConnection();
		List<Map<String, String>> list = ReaderBodyInfoUtils.binaryReader(request, path+"\\");
		try {
			for (Map<String, String> map : list) {
				String sql="UPDATE `plc`.`resource`\r\n" + 
						"SET \r\n" + 
						" `resourcename` = '"+map.get("name")+"',\r\n" + 
						" `resource` = '/uploads/resource/"+map.get("file")+"',\r\n" + 
						" `typeid` = '"+map.get("resource_type")+"'\r\n" + 
						"WHERE\r\n" + 
						"	(`id` = '"+id+"')";
				logger.info(sql);

				PreparedStatement prepareStatement = connection.prepareStatement(sql);
				prepareStatement.execute();
				prepareStatement.close();
			} 
			ResponseUtils.renderJson(response,"{\"status\":\"success\"}");
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

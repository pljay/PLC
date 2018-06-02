package com.pljay.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pljay.jdbc.Mysql;
import com.pljay.utils.ResponseUtils;

/**
 * Servlet implementation class UpdateSchoolServlet
 */
@WebServlet(description = "更新学校信息", urlPatterns = { "/UpdateSchool" })
public class UpdateSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Logger logger=Logger.getLogger(UpdateSchoolServlet.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateSchoolServlet() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		String ID = request.getParameter("schoolid");
		String schoolname = request.getParameter("schoolname");
		String abbreviation = request.getParameter("abbreviation");
		String campustra = request.getParameter("campustra");
		String serialnumber = request.getParameter("serialnumber");
		String WeChatid = request.getParameter("WeChatid");
		String place = request.getParameter("place");
		String address = request.getParameter("address");
		String zipCode = request.getParameter("zipCode");
		String tel_man = request.getParameter("tel_man");
		String phoneNumberCN = request.getParameter("phoneNumberCN");
		String QQ = request.getParameter("QQ");
		String email = request.getParameter("email");
		String school_type = request.getParameter("school_type");
		logger.info(school_type); 
		logger.info(place); 
		String sql="UPDATE `xiaoxuan`.`tlk_校园信息`\r\n" + 
				"SET `PARENT` = NULL,\r\n" + 
				" `LASTMODIFIED` = NOW(),\r\n" + 
				" `FORMNAME` = '智校通/校宣管理系统/校园信息',\r\n" + 
				" `STATE` = '',\r\n" + 
				" `AUDITUSER` = NULL,\r\n" + 
				" `AUDITDATE` = NULL,\r\n" + 
				" `AUTHOR` = NULL,\r\n" + 
				" `AUTHOR_DEPT_INDEX` = '11e6-b052-9fa869f7-b160-b54b43a1b70f',\r\n" + 
				" `CREATED` = '2017-05-09 18:41:23',\r\n" + 
				" `FORMID` = '11e7-201c-7ae3ec10-a365-838f52f9c2a3',\r\n" + 
				" `ISTMP` =  '',\r\n" + 
				" `VERSIONS` = '19',\r\n" + 
				" `APPLICATIONID` = '11e6-b051-0f2f0d26-b160-b54b43a1b70f',\r\n" + 
				" `STATEINT` = '0',\r\n" + 
				" `STATELABEL` = NULL,\r\n" + 
				" `AUDITORNAMES` = '',\r\n" + 
				" `LASTFLOWOPERATION` = NULL,\r\n" + 
				" `LASTMODIFIER` = '11e8-062f-d16fe1ce-85fb-25f307ac0600',\r\n" + 
				" `DOMAINID` = '11e6-b052-9fa5d1e6-b160-b54b43a1b70f',\r\n" + 
				" `AUDITORLIST` = '{}',\r\n" + 
				" `STATELABELINFO` = NULL,\r\n" + 
				" `PREVAUDITNODE` = NULL,\r\n" + 
				" `PREVAUDITUSER` = NULL,\r\n" + 
				" `OPTIONITEM` = NULL,\r\n" + 
				" `SIGN` = '',\r\n" + 
				" `ITEM_CAMPUSNAME` = '"+schoolname+"',\r\n" + 
				" `ITEM_CAMPUSTRA` = '"+campustra+"',\r\n" + 
				" `BUMENID` = '"+WeChatid+"',\r\n" + 
				" `ITEM_WEIXINID` = '"+WeChatid+"',\r\n" + 
				" `ITEM_SCHOOLID` = '"+serialnumber+"',\r\n" + 
				" `ITEM_Abbreviation` = '"+abbreviation+"',\r\n" + 
				" `ITEM_Location` = '"+place+"',\r\n" + 
				" `ITEM_Address` = '"+address+"',\r\n" + 
				" `ITEM_ZipCode` = '"+zipCode+"',\r\n" + 
				" `ITEM_Contacts` = '"+tel_man+"',\r\n" + 
				" `ITEM_Contacts_tel` = '"+phoneNumberCN+"',\r\n" + 
				" `ITEM_Contacts_qq` = '"+QQ+"',\r\n" + 
				" `ITEM_Contacts_mail` = '"+email+"',\r\n" + 
				" `ITEM_School_type` = '"+school_type+"'\r\n" + 
				"WHERE\r\n" + 
				"	(\r\n" + 
				"		`ID` = '"+ID+"'\r\n" + 
				"	)" ;
		String sql2="UPDATE `xiaoxuan`.`t_document`\r\n" + 
				"SET \r\n" + 
				" `LASTMODIFIED` = NOW()\r\n" + 
				"WHERE\r\n" + 
				"	(\r\n" + 
				"		`ID` = '"+ID+"'\r\n" + 
				"	)";
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.executeUpdate();
			prepareStatement.close();
			PreparedStatement prepareStatement2 = connection.prepareStatement(sql2);
			prepareStatement2.executeUpdate();
			prepareStatement2.close();
			ResponseUtils.renderJson(response, "success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ResponseUtils.renderJson(response, "fail");
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

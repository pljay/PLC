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
 * Servlet implementation class InsertSchoolServlet
 */
@WebServlet(description = "插入学校信息", urlPatterns = { "/InsertSchool" })
public class InsertSchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(InsertSchoolServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSchoolServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
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
		UUID ID=UUID.randomUUID();
		logger.info(school_type); 
		logger.info(place); 
		String sql="INSERT INTO `xiaoxuan`.`tlk_校园信息` (\r\n" + 
				"	`PARENT`,\r\n" + 
				"	`LASTMODIFIED`,\r\n" + 
				"	`FORMNAME`,\r\n" + 
				"	`STATE`,\r\n" + 
				"	`AUDITUSER`,\r\n" + 
				"	`AUDITDATE`,\r\n" + 
				"	`AUTHOR`,\r\n" + 
				"	`AUTHOR_DEPT_INDEX`,\r\n" + 
				"	`CREATED`,\r\n" + 
				"	`FORMID`,\r\n" + 
				"	`ISTMP`,\r\n" + 
				"	`VERSIONS`,\r\n" + 
				"	`APPLICATIONID`,\r\n" + 
				"	`STATEINT`,\r\n" + 
				"	`STATELABEL`,\r\n" + 
				"	`AUDITORNAMES`,\r\n" + 
				"	`LASTFLOWOPERATION`,\r\n" + 
				"	`LASTMODIFIER`,\r\n" + 
				"	`DOMAINID`,\r\n" + 
				"	`AUDITORLIST`,\r\n" + 
				"	`STATELABELINFO`,\r\n" + 
				"	`PREVAUDITNODE`,\r\n" + 
				"	`PREVAUDITUSER`,\r\n" + 
				"	`OPTIONITEM`,\r\n" + 
				"	`SIGN`,\r\n" + 
				"	`ID`,\r\n" + 
				"	`ITEM_CAMPUSNAME`,\r\n" + 
				"	`ITEM_CAMPUSTRA`,\r\n" + 
				"	`BUMENID`,\r\n" + 
				"	`ITEM_WEIXINID`,\r\n" + 
				"	`ITEM_SCHOOLID`,\r\n" + 
				"	`ITEM_Abbreviation`,\r\n" + 
				"	`ITEM_Location`,\r\n" + 
				"	`ITEM_Address`,\r\n" + 
				"	`ITEM_ZipCode`,\r\n" + 
				"	`ITEM_Contacts`,\r\n" + 
				"	`ITEM_Contacts_tel`,\r\n" + 
				"	`ITEM_Contacts_qq`,\r\n" + 
				"	`ITEM_Contacts_mail`,\r\n" + 
				"	`ITEM_School_type`\r\n" + 
				")\r\n" + 
				"VALUES\r\n" + 
				"	(\r\n" + 
				"		NULL,\r\n" + 
				"		NOW(),\r\n" + 
				"		'智校通/校宣管理系统/校园信息',\r\n" + 
				"		'',\r\n" + 			
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		'11e6-b052-9fa869f7-b160-b54b43a1b70f',\r\n" + 
				"		'2017-05-09 18:41:23',\r\n" + 
				"		'11e7-201c-7ae3ec10-a365-838f52f9c2a3',\r\n" + 
				"		'',\r\n" + 
				"		'19',\r\n" + 
				"		'11e6-b051-0f2f0d26-b160-b54b43a1b70f',\r\n" + 
				"		'0',\r\n" + 
				"		NULL,\r\n" + 
				"		'',\r\n" + 
				"		NULL,\r\n" + 
				"		'11e8-062f-d16fe1ce-85fb-25f307ac0600',\r\n" + 
				"		'11e6-b052-9fa5d1e6-b160-b54b43a1b70f',\r\n" + 
				"		'{}',\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		'',\r\n" + 
				"		'"+ID+"',\r\n" + 
				"		'"+schoolname+"',\r\n" + 
				"		'"+campustra+"',\r\n" + 
				"		'"+WeChatid+"',\r\n" + 
				"		'"+WeChatid+"',\r\n" + 
				"		'"+serialnumber+"',\r\n" + 
				"		'"+abbreviation+"',\r\n" + 
				"		'"+place+"',\r\n" + 
				"		'"+address+"',\r\n" + 
				"		'"+zipCode+"',\r\n" + 
				"		'"+tel_man+"',\r\n" + 
				"		'"+phoneNumberCN+"',\r\n" + 
				"		'"+QQ+"',\r\n" + 
				"		'"+email+"',\r\n" + 
				"		'"+school_type+"'\r\n" + 
				"	);\r\n" + 
				"\r\n" + 
				"" ;
		String sql2="INSERT INTO `xiaoxuan`.`t_document` (\r\n" + 
				"	`ID`,\r\n" + 
				"	`LASTMODIFIED`,\r\n" + 
				"	`FORMNAME`,\r\n" + 
				"	`AUDITDATE`,\r\n" + 
				"	`AUTHOR`,\r\n" + 
				"	`AUTHOR_DEPT_INDEX`,\r\n" + 
				"	`CREATED`,\r\n" + 
				"	`FORMID`,\r\n" + 
				"	`ISTMP`,\r\n" + 
				"	`VERSIONS`,\r\n" + 
				"	`SORTID`,\r\n" + 
				"	`APPLICATIONID`,\r\n" + 
				"	`STATELABEL`,\r\n" + 
				"	`INITIATOR`,\r\n" + 
				"	`AUDITUSER`,\r\n" + 
				"	`AUDITORNAMES`,\r\n" + 
				"	`LASTFLOWOPERATION`,\r\n" + 
				"	`PARENT`,\r\n" + 
				"	`STATE`,\r\n" + 
				"	`STATEINT`,\r\n" + 
				"	`LASTMODIFIER`,\r\n" + 
				"	`DOMAINID`,\r\n" + 
				"	`AUDITORLIST`,\r\n" + 
				"	`STATELABELINFO`,\r\n" + 
				"	`PREVAUDITNODE`,\r\n" + 
				"	`PREVAUDITUSER`,\r\n" + 
				"	`OPTIONITEM`,\r\n" + 
				"	`SIGN`,\r\n" + 
				"	`MAPPINGID`\r\n" + 
				")\r\n" + 
				"VALUES\r\n" + 
				"	(\r\n" + 
				"		'"+ID+"',\r\n" + 
				"		NOW(),\r\n" + 
				"		'智校通/校宣管理系统/校园信息',\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		'11e6-b052-9fa869f7-b160-b54b43a1b70f',\r\n" + 
				"		NOW(),\r\n" + 
				"		'11e7-201c-7ae3ec10-a365-838f52f9c2a3',\r\n" + 
				"		'',\r\n" + 
				"		'19',\r\n" + 
				"		NULL,\r\n" + 
				"		'11e6-b051-0f2f0d26-b160-b54b43a1b70f',\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		'',\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		'',\r\n" + 
				"		'0',\r\n" + 
				"		'11e8-062f-d16fe1ce-85fb-25f307ac0600',\r\n" + 
				"		'11e6-b052-9fa5d1e6-b160-b54b43a1b70f',\r\n" + 
				"		'{}',\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		NULL,\r\n" + 
				"		'',\r\n" + 
				"		'"+ID+"'\r\n" + 
				"	);\r\n" ;
	
		Connection connection = Mysql.getInstance().getConnection();
		try {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.execute();
			prepareStatement.close();
			PreparedStatement prepareStatement1 = connection.prepareStatement(sql2);
			prepareStatement1.execute();
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

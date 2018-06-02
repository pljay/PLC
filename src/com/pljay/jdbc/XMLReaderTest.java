package com.pljay.jdbc;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class XMLReaderTest
{
	private Logger logger=Logger.getLogger(XMLReaderTest.class);
	public XMLReaderTest(String url)
	{
		url = url + "\\Mysql.xml";
		try
		{
			url = URLDecoder.decode(url, "utf-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		logger.info(url);
		Element element = null;

		File f = new File(url);

		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try
		{
			dbf = DocumentBuilderFactory.newInstance();

			db = dbf.newDocumentBuilder();

			Document dt = db.parse(f);

			element = dt.getDocumentElement();

			logger.info("根元素：" + element.getNodeName());

			NodeList childNodes = element.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++)
			{
				Node node1 = childNodes.item(i);
				if ("Mysql".equals(node1.getNodeName()))
				{
					Mysql.url = node1.getAttributes().getNamedItem("url").getNodeValue() + "?Unicode=true&characterEncoding=UTF-8";
					logger.info(Mysql.url);
					Mysql.user = node1.getAttributes().getNamedItem("user").getNodeValue();
					Mysql.pass = node1.getAttributes().getNamedItem("pass").getNodeValue();
					Mysql.ip = node1.getAttributes().getNamedItem("ip").getNodeValue();
					Mysql.port = Integer.parseInt(node1.getAttributes().getNamedItem("port").getNodeValue());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

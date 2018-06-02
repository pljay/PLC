package com.pljay.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HttpUtils
{
	private static Logger logger=Logger.getLogger(HttpUtils.class);
	public static String getServerPath(HttpServletRequest request)
	{
		String serverName = request.getServerName();
		int serverPort = request.getServerPort();
		String scheme = request.getScheme();
		String contextPath = request.getContextPath();
		return scheme + "://" + serverName + ":" + serverPort + contextPath;
	}
}



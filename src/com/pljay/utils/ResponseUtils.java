package com.pljay.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class ResponseUtils
{
	private static Logger logger=Logger.getLogger(ResponseUtils.class);
	public static void renderJson(HttpServletResponse response, String text)
	{
		render(response, "text/plain;charset=utf-8", text);
	}

	public static void renderJson(HttpServletResponse response, String content, String text)
	{
		render(response, content, text);
	}

	public static void render(HttpServletResponse response, String content, String text)
	{
		response.setContentType(content);
		response.setCharacterEncoding("UTF-8");

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");

		response.setDateHeader("Expires", 0L);
		try
		{
			response.getWriter().write(text);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}



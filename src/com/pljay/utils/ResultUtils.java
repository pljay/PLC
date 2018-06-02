package com.pljay.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.List;

import org.apache.log4j.Logger;

public class ResultUtils
{
	private static Logger logger=Logger.getLogger(ResultUtils.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private Integer code;
	private String msg;
	private Object items;

	public static ResultUtils build(Integer code, String msg, Object items)
	{
		return new ResultUtils(code, msg, items);
	}

	public static ResultUtils ok(Object items)
	{
		return new ResultUtils(items);
	}

	public static ResultUtils ok()
	{
		return new ResultUtils(null);
	}

	public ResultUtils() {}

	public static ResultUtils build(Integer code, String msg)
	{
		return new ResultUtils(code, msg, null);
	}

	public ResultUtils(Integer code, String msg, Object items)
	{
		this.code = code;
		this.msg = msg;
		this.items = items;
	}

	public ResultUtils(Object items)
	{
		this.code = Integer.valueOf(200);
		this.msg = "OK";
		this.items = items;
	}

	public Integer getcode()
	{
		return this.code;
	}

	public void setcode(Integer code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Object getitems()
	{
		return this.items;
	}

	public void setitems(Object items)
	{
		this.items = items;
	}

	public static ResultUtils formatToPojo(String jsonitems, Class<?> clazz)
	{
		try
		{
			if (clazz == null) {
				return (ResultUtils)MAPPER.readValue(jsonitems, ResultUtils.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonitems);
			JsonNode items = jsonNode.get("items");
			Object obj = null;
			if (clazz != null) {
				if (items.isObject()) {
					obj = MAPPER.readValue(items.traverse(), clazz);
				} else if (items.isTextual()) {
					obj = MAPPER.readValue(items.asText(), clazz);
				}
			}
			return build(Integer.valueOf(jsonNode.get("code").intValue()), jsonNode.get("msg").asText(), obj);
		}
		catch (Exception e) {}
		return null;
	}

	public static ResultUtils format(String json)
	{
		try
		{
			return (ResultUtils)MAPPER.readValue(json, ResultUtils.class);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static ResultUtils formatToList(String jsonitems, Class<?> clazz)
	{
		try
		{
			JsonNode jsonNode = MAPPER.readTree(jsonitems);
			JsonNode items = jsonNode.get("items");
			Object obj = null;
			if ((items.isArray()) && (items.size() > 0)) {
				obj = MAPPER.readValue(items.traverse(), 
						MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(Integer.valueOf(jsonNode.get("code").intValue()), jsonNode.get("msg").asText(), obj);
		}
		catch (Exception e) {}
		return null;
	}
}



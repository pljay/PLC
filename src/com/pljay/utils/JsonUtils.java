package com.pljay.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

public class JsonUtils
{
	private static Logger logger=Logger.getLogger(JsonUtils.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static String objectToJson(Object data)
	{
		try
		{
			return MAPPER.writeValueAsString(data);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String objectToJsonNotNull(Object data)
	{
		try
		{
			MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer()
			{
				public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
						throws IOException, JsonProcessingException
				{
					jg.writeString("");
				}
			});
			return MAPPER.writeValueAsString(data);
		}
		catch (JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T jsonToPojo(String jsonData, Class<T> beanType)
	{
		try
		{
			
			return MAPPER.readValue(jsonData, beanType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	

	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType)
	{
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, new Class[] { beanType });
		try
		{
			return MAPPER.readValue(jsonData, javaType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T jsonToPojonotnull(String jsonData, Class<T> beanType)
	{
		try
		{
			MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer()
			{
				public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
						throws IOException, JsonProcessingException
				{
					jg.writeString("");
				}
			});
			return MAPPER.readValue(jsonData, beanType);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}



package com.pljay.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgementUtils
{
	public static boolean valiDateTimeWithLongFormat(String timeStr)
	{
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(timeStr);
		if (matcher.matches())
		{
			pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
			matcher = pattern.matcher(timeStr);
			if (matcher.matches())
			{
				int y = Integer.valueOf(matcher.group(1)).intValue();
				int m = Integer.valueOf(matcher.group(2)).intValue();
				int d = Integer.valueOf(matcher.group(3)).intValue();
				if (d > 28)
				{
					Calendar c = Calendar.getInstance();
					c.set(y, m - 1, 1);
					int lastDay = c.getActualMaximum(5);
					return lastDay >= d;
				}
			}
			return true;
		}
		return false;
	}
}



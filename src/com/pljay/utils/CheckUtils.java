package com.pljay.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>Title: CheckUtils</p> 
 * <p>Description: </p>  
 * @author PLJay 
 * @date 2018骞�4鏈�17鏃� 
 */
public class CheckUtils {

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉鎵嬫満鍙�
	 */
	private static final String REGEX_MOBILE;

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉閭
	 */
	private static final String REGEX_EMAIL;

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉姹夊瓧
	 */
	private static final String REGEX_CHINESE;

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉韬唤璇�
	 */
	private static final String REGEX_ID_CARD;

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉URL
	 */
	private static final String REGEX_URL;

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉IP鍦板潃
	 */
	private static final String REGEX_IP_ADDR;

	/**
	 * 姝ｅ垯琛ㄨ揪寮忥細楠岃瘉涓枃鍚�
	 */
	private static final String REGEX_NAME;

	static {
		REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		REGEX_NAME = "^(([\u4e00-\u9fa5]{2,8})|([a-zA-Z]{2,16}))$";
		REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
		REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
		REGEX_ID_CARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
	}

	/**
	 * 鏍￠獙韬唤璇�
	 * 
	 * @param idCard
	 * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean isIDCard(String idCard) {
		return Pattern.matches(REGEX_ID_CARD, idCard);
	}

	/**
	 * 鏍￠獙鎵嬫満鍙�
	 * 
	 * @param mobile
	 * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

	/**
	 * 鏍￠獙閭
	 * 
	 * @param email
	 * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean isEmail(String email) {
		return Pattern.matches(REGEX_EMAIL, email);
	}

	/**
	 * 鏍￠獙姹夊瓧
	 * 
	 * @param chinese
	 * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean isChinese(String chinese) {
		return Pattern.matches(REGEX_CHINESE, chinese);
	}

	/**
	 * 鏍￠獙URL
	 *
	 * @param url
	 * @return 鏍￠獙閫氳繃杩斿洖true锛屽惁鍒欒繑鍥瀎alse
	 */
	public static boolean isUrl(String url) {
		return Pattern.matches(REGEX_URL, url);
	}

	/**
	 * 鏍￠獙IP鍦板潃
	 * 
	 * @param ipAddr
	 * @return
	 */
	public static boolean isIPAddr(String ipAddr) {
		return Pattern.matches(REGEX_IP_ADDR, ipAddr);
	}

	/**
	 * 鏍￠獙鏃堕棿
	 * @param Format
	 * @param Time
	 * @return 濡傛灉鏍煎紡鍖栧け璐ヨ繑鍥瀎alse
	 */
	public static boolean isTime(String Format,String Time)  
	{  
		try  
		{  
			SimpleDateFormat dateFormat = new SimpleDateFormat(Format);  

			System.out.println(dateFormat.parse(Time));
			return true;  
		}  
		catch (Exception e)  
		{  
			return false;  
		}  
	}  

	/**
	 * 鏍￠獙涓枃鍚�
	 * @param name
	 * @return
	 */
	public static boolean isName(String name){
		return Pattern.matches(REGEX_NAME, name);
	}
    /**
     * 鍒ゆ柇鏁板瓧鐨勪綅鏁�
     *
     * @param number
     * @param length
     *
     * @return
     */
    public static boolean checkNumberLength(String number, int length) {
        if (number == null) {
            return false;
        }

        Pattern p = Pattern.compile("\\d*");
        return p.matcher(number).matches() && number.length() == length;
    }
    /**
     * 瀵硅薄鏄惁涓虹┖
     * 
     * @param obj
     *            瀵硅薄
     * @return boolean TRUE-绌�;FALSE-闈炵┖
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 鏄惁涓虹┖瀛楃涓�
     * 
     * @param str
     *            瀛楃涓�
     * @return boolean TRUE-绌哄瓧绗︿覆;FALSE-闈炵┖瀛楃涓�
     */
    public static boolean isEmpty(CharSequence str) {
        return isNull(str) || str.length() == 0;
    }

    /**
     * 鏄惁涓虹┖闆嗗悎
     * 
     * @param coll
     *            闆嗗悎
     * @return boolean TRUE-绌洪泦鍚�;FALSE-闈炵┖闆嗗悎
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.size() == 0;
    }

    /**
     * 鏄惁涓虹┖闆嗗悎
     * 
     * @param map
     *            闆嗗悎
     * @return boolean TRUE-绌洪泦鍚�;FALSE-闈炵┖闆嗗悎
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.size() == 0;
    }

    /**
     * 鏄惁涓虹┖鏁扮粍
     * 
     * @param arr
     *            鏁扮粍
     * @return boolean TRUE-绌烘暟缁�;FALSE-闈炵┖鏁扮粍
     */
    public static boolean isEmpty(Object[] arr) {
        return isNull(arr) || arr.length == 0;
    }
    
	public static void main(String[] args) {
		System.out.println(CheckUtils.isTime("YYYYMM","199919"));
	}
}

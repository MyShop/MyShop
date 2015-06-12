package base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ibsValidate {

	//判断字符串是否为空
	public static boolean isEmpty(String s)
	{
		if(s == null || s=="" || s.length()==0)
			return true;
		else 
			return false;
	}
	
	// 判断是否包含中文字符(需要完善)
	public static boolean isContainChinese()
	{
		return false;
	}
	
	//判断是否是邮箱
	public static boolean isMail(String email)
	{
		String  str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	//校验电话号码
	public static boolean isMobileNumber(String mobiles) {
	    return Pattern
	            .compile("^((13[0-9])|(15[^4,\\D])|(18[^1^4,\\D]))\\d{8}")
	            .matcher(mobiles).matches();
	}
	public static void main(String[] args)
	{
		String email = "你好大进口将都卡死了jjkkk";
		System.out.println(email.length());
	}
}

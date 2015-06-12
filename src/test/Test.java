package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	public static void main(String[] args)
	{
		int a = 100;
		int b = 3;
		int c = 0;
		
		short a1 = 1;
		a1 += 1;
		System.out.println(a / b);
		System.out.println("Math.round()"+Math.round(11.5));
		System.out.println("Math.round()"+Math.round(-11.5));
		
		
		System.out.println(getDate());
	}
	
	//获取标准格式的日期
	public static String getDate()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(date);
		return dateStr;
	}
}

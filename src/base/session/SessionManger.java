package base.session;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ibs.hibernate.bean.user.Userinfo;

/**
 * 
 * @author Robin  该类用于统一管理Session中的内容，以便其他类存储和访问统一
 *
 */
public class SessionManger {
	
	public static void  PutUserInfo(HttpSession session,Userinfo userInfo)
	{
	
		session.putValue("userInfo", userInfo);
		
	}
	
	public static Userinfo GetUserInfo(HttpSession session)
	{
		
		return (Userinfo)session.getAttribute("userInfo");
		
	}
	
	//存储是否登录标志位
	public static void PutLoginFlag(HttpSession session)
	{
		session.putValue("loginCheck", "checked");
	}
	
	//获取是否登录标志位
	public static String GetLoginFlag(HttpSession session)
	{
		return (String)session.getAttribute("loginCheck");
	}

}

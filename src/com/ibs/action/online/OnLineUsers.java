package com.ibs.action.online;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/** 
 *  
 * 需要在登录时候调用 session.setAttribute("xx",new OnLineUsers(userName)); 
 * 即可绑定，不需要在web.xml中注册 
 *  
 *  
 * 可以通过这个来 保存sessionid 和session的对应关系，供需要的时候使用 
 *  
 * @author 
 * @date 2013-1-5 上午09:06:06 
 *  
 * @version 1.0 
 */  
public class OnLineUsers implements HttpSessionBindingListener{

	String yhm;  
	  
    public OnLineUsers(String yhm)  
    {  
        System.out.println("OnLineUsers...");  
        this.yhm = yhm;  
    }  
  
    public void valueBound(HttpSessionBindingEvent event)  
    {  
        System.out.println("OnLineUsers... valueBound");  
        HttpSession session = event.getSession();  
        ServletContext context = session.getServletContext();  
        HashMap<String, String> olu = (HashMap<String, String>) context.getAttribute("OnLineUsers");  
        if (olu == null)  
        {  
            olu = new HashMap<String, String>();  
            context.setAttribute("OnLineUsers", olu);  
        }  
        olu.put(yhm, session.getId());  
    }  
  
    public void valueUnbound(HttpSessionBindingEvent event)  
    {  
        System.out.println("OnLineUsers... valueUnbound");  
        HttpSession session = event.getSession();  
        ServletContext context = session.getServletContext();  
        HashMap<String, String> olu = (HashMap<String, String>) context.getAttribute("OnLineUsers");  
        if (olu != null)  
        {  
            olu.remove(yhm);  
        }  
    }  
  
}

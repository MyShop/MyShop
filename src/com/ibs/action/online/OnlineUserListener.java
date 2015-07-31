package com.ibs.action.online;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import base.session.SessionManger;

import com.ibs.hibernate.bean.user.Userinfo;

/** 
 * @robin 版本 监听所有session的创建和销毁 ，需要在web.xml中注册 
 */  
public class OnlineUserListener implements HttpSessionListener{

	/** 
     * 新建session 
     */  
    public void sessionCreated(HttpSessionEvent event)  
    {  
        System.out.println("新建session:" + event.getSession().getId());  
        AuthsOfSession authSession = AuthsOfSession.getInstance();
        authSession.setActiveOnline(authSession.getActiveOnline() + 1);
        authSession.setHistoryOnline(authSession.getHistoryOnline() + 1);
    }  
  
    /** 
     * 销毁 session ，如session失效，测试时候可以修改 web.xml 中 session-timeout 
     */  
    public void sessionDestroyed(HttpSessionEvent event)  
    {  
        HttpSession session = event.getSession();  
        // 取得登录的用户名  
        Userinfo userinfo = SessionManger.GetUserInfo(session);
        
        AuthsOfSession authSession = AuthsOfSession.getInstance();
        if(userinfo != null)
        {
        	Integer key = userinfo.getId();
        	authSession.getSessionTable().remove(key);
        }
        authSession.setActiveOnline(authSession.getActiveOnline() - 1);
    }

}

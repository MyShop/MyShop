package com.ibs.action.online;

import java.util.Arrays;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.ibs.hibernate.bean.user.User;
import com.ibs.hibernate.bean.user.Userinfo;

/**
 * 此例目的只是为了实现在线人数统计,不涉及数据库方面操作,故此处初始化10个用户.
 * @author <a href="mailto:weijunqiang2010@gmail.com">Ajunboys</a>
 *
 */
@WebListener
public class InitUserMappingListener implements ServletContextListener{

	/**
     * Default constructor. 
     */
    public InitUserMappingListener() {
        // TODO Auto-generated constructor stub
    }
        
 
    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        ServletContext application = event.getServletContext();
        Userinfo [] users = new Userinfo[]{
                new Userinfo("1000", "user00", "password"),
                new Userinfo("1001", "user01", "password"),
                new Userinfo("1002", "user02", "password"),
                new Userinfo("1003", "user03", "password"),
                new Userinfo("1004", "user04", "password"),
                new Userinfo("1005", "user05", "password"),
                new Userinfo("1006", "user06", "password"),
                new Userinfo("1007", "user07", "password"),
                new Userinfo("1008", "user08", "password"),
                new Userinfo("1009", "user09", "password")
        };
         
        application.setAttribute("users", Arrays.asList(users));
         
        logger.info("init " + users.length +" users ");
    }
 
    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
    }
     
    private Logger logger = Logger.getLogger(InitUserMappingListener.class.getName());
}

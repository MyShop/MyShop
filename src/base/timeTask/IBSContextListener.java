package base.timeTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;

import com.ibs.action.online.AuthsOfSession;
import com.ibs.hibernate.bean.system.Menu;
import com.ibs.hibernate.bean.system.Visitor;

public class IBSContextListener<T>  implements ServletContextListener{

	//定时器成员对象
	private  Timer  timer  =  null;
	
	Session session = null;
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();    
		event.getServletContext().log("定时器销毁");  
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		try
		{
			session = HibernateSessionFactory.getSession();
			String hql = " from Visitor where id=0";
			List<Visitor> visitors = (ArrayList<Visitor>) session.createQuery(hql).list();
			AuthsOfSession  authSession = AuthsOfSession.getInstance();
			authSession.setActiveOnline(visitors.get(0).getActiveOnline());
			authSession.setHistoryOnline(visitors.get(0).getHistoryOnline());
			
			authSession.setProductTypes();
			this.timer  =  new  Timer(true);    
			event.getServletContext().log("定时器已启动!");
			timer.schedule(new MyTask(event.getServletContext()), 0,60*60*1000*24);
			//程序初始化，获取在线人数和历史访问人数
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(session.isOpen())
				session.close();
		}
		finally
		{
			if(session.isOpen())
				session.close();
		}
		
		
	}
}

package base.timeTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.hibernate.Session;

import Hibernate.HibernateSessionFactory;

import com.ibs.action.online.AuthsOfSession;
import com.ibs.hibernate.bean.system.Visitor;

public class MyTask<T> extends TimerTask {
	
	//时间常数，表示晚上12点
	private static final int C_SCHEDULE_HOUR = 0;

	private static boolean isRunning = false;

	private ServletContext context = null;
	
	private Session session = null;
	

	public MyTask(ServletContext context) {
		this.context = context;
	}

	public void run() {
		
		 Calendar  cal  =  Calendar.getInstance();
		 if(!isRunning)
		 {
			 
			 if(C_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY))
			 {
				 isRunning = true;
				 AuthsOfSession  authSession = AuthsOfSession.getInstance();
				 Integer activeOnline = authSession.getActiveOnline();
				 Integer historyOnline = authSession.getHistoryOnline();
				 if(activeOnline == null && historyOnline == null)
				 {
					try
					{
						session = HibernateSessionFactory.getSession();
						//定时更新访问统计功能
						String hql = " from Visitor where id=0";
						List<Visitor> visitors =  (List<Visitor>)session.createQuery(hql).list();
						Visitor theVisitor = visitors.get(0);
						theVisitor.setActiveOnline(authSession.getActiveOnline());
						theVisitor.setHistoryOnline(authSession.getHistoryOnline());
						session.update(theVisitor);
						AuthsOfSession.setProductTypes();
						
					}
					catch(Exception ex)
					{
						ex.printStackTrace();
						if(session.isOpen())
						{
							session.close();
						}
					}
					finally
					{
						if(session.isOpen())
						{
							session.close();
						}					
					}
				 }
				 isRunning  =  false;    
				 context.log("指定任务执行结束");    
			 }
		 }
		 else
		 {
			 context.log(" 上次任务还未执行完成!");
		 }

	}

}

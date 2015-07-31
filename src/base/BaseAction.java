package base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 2648183755483368254L;
		public HttpServletRequest request;
	    public HttpServletResponse response;
	    public HttpSession session;
	    private ActionContext action;

		@Override
		public void setServletResponse(HttpServletResponse response) {
			this.response = response;
		}

		@Override
		public void setServletRequest(HttpServletRequest request) {
			this.request = request;
			session = request.getSession(true);
		}
		public HttpSession getSession() {
			return session;
		}

		public void setSession(HttpSession session) {
			this.session = session;
		}
		public HashMap<Object,Object> rejectMap = new HashMap();
		
	    protected void printJson(String json) throws IOException{
			this.response.setCharacterEncoding("UTF-8");
			this.response.setContentType("application/x-json");
			this.response.getWriter().print(json);
			this.response.getWriter().flush();
		}
	    
	    protected void printHtml(String html) throws IOException
	    {
	    	this.response.setCharacterEncoding("UTF-8");
	    	this.response.setContentType("text/html; charset=utf-8");
	    	this.response.getWriter().print(html);
	    	this.response.getWriter().flush();
	    }
	    
	    protected void returnRejMap() throws IOException{
	    	Iterator iterator = rejectMap.entrySet().iterator();
			StringBuffer json=new StringBuffer();
			json.append("[");
			if (rejectMap != null && rejectMap.size() > 0) 
			{
				while(iterator.hasNext())
				{
					json.append("{");
					Map.Entry entry = (Map.Entry) iterator.next();
					String filedName = (String) entry.getKey();
					String errorMessage = (String) entry.getValue();
					json.append("\"filedName\"" +":" +"\"" + filedName +"\",");
					json.append("\"errorMessage\"" +":" +"\"" + errorMessage +"\"");
					json.append("},");
				}
				json.delete(json.length()-1, json.length());
			}
			json.append("]");
			this.printJson(json.toString());
		}
}

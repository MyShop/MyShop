package base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction{
	
	    public HttpServletRequest request;
	    public HttpServletResponse response;
	    public HttpSession session;
	    public loadProperties  loadPro= loadProperties.getInstance();
	    public HttpSession getSession() {
			return session;
		}
		public void setSession(HttpSession session) {
			this.session = session;
		}
		public HashMap<Object,Object> rejectMap = new HashMap();
		
	    
	    public HttpServletRequest getRequest() {
			return request;
		}
		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}
		public HttpServletResponse getResponse() {
			return response;
		}
		public void setResponse(HttpServletResponse response) {
			this.response = response;
		}
		public ActionContext getContext() {
			return context;
		}
		public void setContext(ActionContext context) {
			this.context = context;
		}
		private ActionContext context;
	    {
	    	context = ActionContext.getContext();  
	    	request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
	    	response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
	    	session = request.getSession();  
	    }
	    protected void printJson(String json) throws IOException{
			this.getResponse().setCharacterEncoding("UTF-8");
			this.getResponse().setContentType("application/x-json");
			this.getResponse().getWriter().print(json);
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

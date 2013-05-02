package jspTag;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.ibs.hibernate.bean.system.Menu;

public class ulTag<T extends Menu>  extends TagSupport {
	
	private List<T> list;
	
	public int doStartTag() throws JspException {

        try {

            JspWriter out = this.pageContext.getOut();
            if(list == null) 
            {
                out.println("No list Found...");
            }
            
            if(!(list instanceof List))
            {
            	 out.println("Not a list");
            }
           
            if(list != null)
            {
            	out.println(this.getPrintString(list));
            }
        } catch(Exception e) {

            throw new JspException(e.getMessage());

        }
        return SKIP_BODY;
    }
	
	public int doEndTag() throws JspException {

        return EVAL_PAGE;

    }
	

	    public void release() {
	        super.release();
	        this.list = null;
	    }

		public List<T> getList() {
			return list;
		}

		public void setList(List<T> list) {
			this.list = list;
		}
		
		public String getPrintString(List list) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
		{
			StringBuffer bf  = new StringBuffer();
        	bf.append("<ul>");
        	for(int i = 0; i < list.size();i++)
        	{
        		
        		Method getId = list.get(i).getClass().getMethod("getId", null);
        		Method getName = list.get(i).getClass().getMethod("getName", null);
        		Method getUrl = list.get(i).getClass().getMethod("getUrl", null);
        		Method getPareng = list.get(i).getClass().getMethod("getParent", null);
        		if(getName != null && getId != null && getUrl != null)
        		{
        			String name = (String) getName.invoke(((Menu)list.get(i)), null);
        			Integer id  = (Integer) getId.invoke(((Menu)list.get(i)), null);
        			String  url = (String) getUrl.invoke(((Menu)list.get(i)),null );
        			Integer  parent = (Integer) getPareng.invoke(((Menu)list.get(i)),null );
        			bf.append("<li id=\"node_" +id.toString()+ "\"" + "  parent=\"" + parent.toString() +"\"" + " onclick = \"edit(this.id)\""+">").append(name +"</a>"+"</li>");
        			List tempList = ((Menu)list.get(i)).getChild();
        			if(tempList != null)
        			{
        				bf.append(this.getPrintString(tempList));
        			}
        		}
        		else
        		{
        		   bf.append("Not have id or name or url");
        		}
        	}
        	bf.append("</ul>");
        	
        	return bf.toString();
		}
}

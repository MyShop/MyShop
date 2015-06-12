package com.ibs.action.console;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ibs.action.login.errorMessage;
import com.ibs.hibernate.bean.role.RoleType;
import com.ibs.hibernate.bean.user.User;
import com.ibs.hibernate.bean.user.UserRole;
import com.ibs.hibernate.bean.user.Userinfo;
import com.ibs.hibernate.dao.BaseDAO;
import com.opensymphony.xwork2.ActionContext;

import base.BaseAction;
import base.SystemVariable;
import base.ibsValidate;
/**
 * 控制台登录Action
 * @author Robin
 *
 */
public class consoleLoginAction<T> extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4645416777642978771L;
	
	private String email;
	private String password;
	private String rePassword;
	private Userinfo userInfo;
	
    private BaseDAO<T> dao;
	
	/**
	 * 提交表单前验证
	 * @return
	 * @throws IOException
	 */
    
    public BaseDAO<T> getDao() {
		return dao;
	}

	public void setDao(BaseDAO<T> dao) {
		this.dao = dao;
	}

	public void  validateEnter() throws IOException
	{
		
			String hql = "" ;
			List<User> userList = null;
			//validate email
			if(ibsValidate.isEmpty(email))
			{
				this.addFieldError("email", errorMessage.login100);
			}
			else
			{
				hql =" from User u where u.email ='" + email +"'";
				userList = (List<User>) dao.list(hql);
				if(userList.size()==0)
				{
					this.addFieldError("email", errorMessage.login101);
				}
			}
			
			//validate password
			if(!ibsValidate.isEmpty(password) && userList!=null && userList.size() > 0)
			{
				if(ibsValidate.isEmpty(password))
				{
					this.addFieldError("password", errorMessage.login102);
				}
				else if(!password.equals(userList.get(0).getPassword()))
				{
					this.addFieldError("password", errorMessage.login103);
				}
			}
			
			//validate repPssword
			if(ibsValidate.isEmpty(rePassword))
			{
				this.addFieldError("rePassword", errorMessage.login104);
			}
			else if(!rePassword.equals(session.get("rePassword")))
			{
				this.addFieldError("rePassword", errorMessage.login105);
			}
			
			//字段校验通过再检验权限问题
			if(this.getFieldErrors().size() == 0)
			{
				userInfo = userList.get(0).getUserInfo();//将查询的用户信息保存
				//查询用户权限 
				hql =" from UserRole where userAccount='" + email +"'";
				List<UserRole> roles = (List<UserRole>) dao.list(hql);
				RoleType[] tempRoles =null;
				Iterator it =null;
				if(roles != null)
				{
					it =  roles.iterator();
				}
				
				//根据用户权限判断是否允许标志位
				boolean isAllow = false;
				
				while(it.hasNext())
				{
					Integer tempRole = ((UserRole)it.next()).getRoleId();
					System.out.print(SystemVariable.Role0.equals(tempRole));
					if(SystemVariable.Role1.equals(tempRole))
					{
						isAllow = true;
					    break;
					}
				}
				
				if(isAllow == false)
				{
					this.addFieldError("email", errorMessage.login107);
				}

			}
}
	
	/**
	 * 提交表单，提交表单前需再次验证谨防通过修改JS修改表单数据
	 * @return
	 * @throws Exception
	 */
	public String Enter() throws Exception
	{
			session.put("userInfo",userInfo);
			userInfo = (Userinfo) session.get("userInfo");
			String hql =" from UserRole where userAccount='" + email +"'";
			List roles = dao.list(hql);
			RoleType[] tempRoles =null;
			Iterator it =null;
			if(roles != null)
			{
				it =  roles.iterator();
			}
			
			 //根据用户权限判断是否允许标志位
			boolean isAllow = false;
			
			while(it.hasNext())
			{
				Integer tempRole = ((UserRole)it.next()).getRoleId();
				if(SystemVariable.Role0.equals(tempRole))
				{
					isAllow = true;
				    break;
				}
			}
			if(isAllow)
			 return "success";
			else
				return "cosole";
		
	}
    
	public String execute()
	{
		return "input";
	}
	
	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Userinfo userInfo) {
		this.userInfo = userInfo;
	}

	
}

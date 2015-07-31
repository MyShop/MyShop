package base;

import java.util.HashMap;

public class SystemVariable {
	
	//禁止该类实例化
	private SystemVariable()
	{
		
	}
	/**
	 * 超级用户
	 */
	public static Integer  Role0=0;
	
	/**
	 *  1	仅注册个人客户，支持所有的个人基本服务。
	 */
	public static Integer  Role1=1;
	/**
	 *  2	会员个人客户，支持个人增值服务。
	 */
	public static Integer  Role2=2;
	
	/**
	 *  3	仅注册企业客户，支持所有的企业基本服务。
	 */
	public static Integer  Role3=3;
	
	/**
	 *  4	仅注册企业客户，支持所有的企业基本服务。
	 */
	public static Integer  Role4=4;
	
	
	
	
	public static HashMap<Integer,String>  payType;
	{
		payType.put(0, "货到付款");
	}
	
}

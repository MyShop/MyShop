package base;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletException;


public class loadProperties extends Thread{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1773572448656660385L;
	
	private static loadProperties loadPro = null;
	
	private static ArrayList<String> fileList = new ArrayList<String>();
	private static HashMap<String,String> keyValues = new HashMap<String,String>();
	
	/**
	 * 线程是否运行标志
	 */
	private static boolean flag = false;
	
	private loadProperties()
	{
	}
	public static loadProperties getInstance()
	{
		if(loadPro == null)
		{
			loadPro = new loadProperties();
			loadPro.start();
			return loadPro;
		}
		return loadPro;
	}
	public static void load(String filePath)
	{
		File dir = new File(filePath);
		try
		{
			File[] files = dir.listFiles();
			if(files == null)
			return;
			for(int i = 0;i < files.length;i++)
			{
				//检查该文件是否是标准文件
				if(files[i].isFile())
				{
					//检查该文件是否是properties文件
					if(files[i].getName().endsWith(".properties"))
					{
						fileList.add(files[i].getPath());
					}
				}
				
				if(files[i].isDirectory())
				{
					load(files[i].getPath());
				}
			}
			
				
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static void getKeyValue()
	{
		for(int i = 0;i < fileList.size(); i++)
		{
			Properties props = new Properties();
			try
			{
				InputStream in = new BufferedInputStream (new FileInputStream(fileList.get(i)));
				props.load(in);
				Iterator itr = props.entrySet().iterator();
				while(itr.hasNext())
				{
					Entry e =(Entry)itr.next();
					if(keyValues.containsKey(e.getKey().toString()))
					{
						System.out.println(fileList.get(i)+"中重复包含:"+e.getKey().toString());
						continue;
					}
					keyValues.put(e.getKey().toString(), e.getValue().toString());
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(fileList.get(i)+"文件未找到");
			}
		}
	}
	
	public void run()
	{
		while(true)
		{
			// TODO Auto-generated method stub
			load("C:\\Users\\Robin\\Workspaces\\IBS\\IBS\\src\\property");
			keyValues.clear();
			getKeyValue();
			fileList.clear();
			try {
				sleep(1000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
			}
		}
		
	}
	
	public  String getMessage(String key)
	{
		return keyValues.get(key);
	}
}

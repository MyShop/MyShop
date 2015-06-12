package com.ibs.action.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class FileAction {
	
	private File file;
	
	private String fileFileName;
	
	private String filePath;
	
	private String downloadFilePath;
	
	private InputStream inputStream;
	
	/**
	 * 文件上传
	 * @return
	 */
	public String fileUpload()
	{
		String path =new String("A:\\workspace\\IBS\\upload");
		File fileTmp = new File(path);
		if(!fileTmp.exists())
			fileTmp.mkdir();
		try
		{
			if(this.file != null)
			{
				String fileNameTemp = java.util.UUID.randomUUID().toString();
				String name = fileNameTemp + fileFileName.substring(fileFileName.lastIndexOf("."));
				FileInputStream  inputStream = new FileInputStream(file);
				FileOutputStream outputStream = new FileOutputStream(path + "\\" + name);
				byte[] buf = new byte[2048];
				int length = 0;
				while((length = inputStream.read(buf)) != -1)
				{
					outputStream.write(buf,0,length);
				}
				
				inputStream.close();
				outputStream.flush();
				filePath = path + "\\" + name;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "success";
	}

	public String downloadFile()
	{
		String path = downloadFilePath;
		HttpServletResponse  response = ServletActionContext.getResponse();
		try
		{
			//path是指欲下载的文件路径
			File file = new File(path);
			
			//取得文件名
			String fileName = file.getName();
			
			//以流的形式下载文件
			InputStream  fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			
			//清空response
			response.reset();
			
			//设置response的Header
			String fileNameString = new String(fileName.getBytes("gbk"),"ISO-8859-1");
			response.setHeader("Content-Disposition", "attachement;fileName=" + fileNameString);
			response.setHeader("Content-Length","" + file.length());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("applicarion/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDownloadFilePath() {
		return downloadFilePath;
	}

	public void setDownloadFilePath(String downloadFilePath) {
		this.downloadFilePath = downloadFilePath;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

}

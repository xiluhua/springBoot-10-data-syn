package com.springBoot.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileStreamTool {
	
	public static final String lineSeparator = System.getProperty("line.separator");
	/**
	 * 文件转换成流，返回流
	 * @param xmlStream
	 * @return
	 * @throws Exception 
	 */
	public byte[] parseFileToStream(String path) throws Exception{
		FileInputStream in = new FileInputStream(path);
		byte  buffer[] = read(in);
		return buffer;
	}
	
	/**
	 * 使用ByteArrayOutputStream将流变成字节
	 * @param in InputStream,输入流
	 * @return 字节数组
	 * @throws Exception
	 */
	public static byte[] read(InputStream in) {  
		ByteArrayOutputStream out = null;  
		try {
			out = new ByteArrayOutputStream();  
			if (in != null) {  
				byte[] buffer = new byte[1024];  
			    int length = 0;  
				while ((length = in.read(buffer)) != -1) {  
				    out.write(buffer, 0, length);  
				}
				out.close();  
				in.close();  
				return out.toByteArray();  
			}  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(in, out, null);  
		}
		return null;  
	}

	private static void close(InputStream in, ByteArrayOutputStream out, OutputStream out2) {
		try {
			if (out != null) {
				out.close();
			}
			if (out2 != null) {
				out2.close();
			}
			if (in != null) {
				in.close();  
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	/**
	 * 把byte转文件
	 * @param path
	 * @param b
	 * @throws Exception
	 */
    public static void write(String path,byte[] b) throws Exception {  
    	FileOutputStream outStream = null;
    	try {
    		File file = new File(path);
    		if(file.exists()){
    			file.delete();
    		}
    		outStream = new FileOutputStream(file);
    		outStream.write(b);
    		outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(null, null, outStream);
		}
		
	 }

	/**
	 * 复制文件
	 * @param oldPath 要复制的文件的绝对路径
	 * @param newPath 新文件的绝对路径
	 * @throws Exception 
	 */
	public void copyFile(String oldPath,String newPath){
		try {
			FileInputStream in = new FileInputStream(oldPath);
			byte  buffer[] = read(in);
			write(newPath, buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public String UnicodeToString(String str) {
	    Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");    
	    Matcher matcher = pattern.matcher(str);
	    char ch;
	    while (matcher.find()) {
	        ch = (char) Integer.parseInt(matcher.group(2), 16);
	        str = str.replaceAll(matcher.group(1), ch + "");    
	    }
	    return str;
	}
    	
	
}

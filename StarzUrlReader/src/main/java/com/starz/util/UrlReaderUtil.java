package com.starz.util;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;



public class UrlReaderUtil {
	
	
	public String readUrlFromPropertyFile(){
		String videoUrl =  null;
		try{
		  Properties prop = new Properties();
			InputStream  inputStream =
					UrlReaderUtil.class.getClassLoader().getResourceAsStream("application.properties");
			
			prop.load(inputStream);
			//31.569281,74.398697
			videoUrl = prop.getProperty("url");
			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return videoUrl;
		
		
	}

}

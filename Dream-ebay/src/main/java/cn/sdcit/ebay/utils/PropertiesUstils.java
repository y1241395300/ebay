package cn.sdcit.ebay.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUstils {
	private static final String PROPERTIES_NAME = "src/main/resources/resources.properties";
	public static String TOKEN = null;
	
	static{
		FileInputStream in = null;
		try{
			Properties properties = new Properties();
			in = new FileInputStream(PROPERTIES_NAME);
			properties.load(in);
			TOKEN = properties.getProperty("token");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
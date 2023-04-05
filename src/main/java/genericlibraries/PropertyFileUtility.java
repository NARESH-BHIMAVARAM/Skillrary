package genericlibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtility {
	
	private Properties property;
	/**
	 * this method is used to initialize properties file
	 * @param filepath
	 */
	public void propertyConfig(String filepath) {
		FileInputStream fis =null;
		try {
			 fis =new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		property=new Properties();
		try {
			property.load(fis);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
	}
	/**
	 * This method is used to fetch the value form properties file based on key
	 * @param key
	 * @return
	 */
	public String fetchProperty(String key) {
		
		
		return property.getProperty(key);
	}
	/**
	 * this method is used to write data into properties file
	 * @param key
	 * @param value
	 * @param message
	 * @paramfilepath
	 */
	public void setDataToProperties(String key,String value,String message,String filepath) {
		property.put(key, value);
		
		try {
			property.store(new FileOutputStream(filepath), message);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

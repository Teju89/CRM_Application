package com.crm.generic.fileUtility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileUtitlity {

	public String getDataFromPropertyFile(String key) throws Exception {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(new File("./configData/commonData.properties"));
		properties.load(stream);

		return properties.getProperty(key);
	}
}

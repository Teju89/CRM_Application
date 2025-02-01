package com.crm.generic.fileUtitlity;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class PropertyFileUtility {

	public String getDataFromPropertyFile(String key) throws Exception {
		Properties properties = new Properties();
		FileInputStream stream = new FileInputStream(new File("./CommonData/commonData.properties"));
		properties.load(stream);

		return properties.getProperty(key);
	}
}

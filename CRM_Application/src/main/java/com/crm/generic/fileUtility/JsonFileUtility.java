package com.crm.generic.fileUtility;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileUtility {

	public String getDataFromJsonFile(String key) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(new FileReader(new File("./configData/commanData.json")));
		return (String) object.get(key);
	}
}

package com.crm.generic.fileUtitlity;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileUtitlity {

	public String getDataFromJsonFile(String key) throws Exception {

		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(new FileReader(new File("./CommanData/commanData.json")));
		return (String) object.get(key);
	}
}

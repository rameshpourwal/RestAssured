package com.rest_assured.smokeapi;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.rest_assured.testbase.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class Registartion extends TestBase {

	private String uri = "/api/users";
	private HashMap<String, String> data;

	public Registartion() throws Exception {
		super();
		data = new HashMap<>();
	}

	private HashMap<String, String> setData() {
		data.put("name", "morpheus");
		data.put("job", "leader");
		return data;
	}

	@Test
	@Description(" register the user ")
	@Severity(SeverityLevel.CRITICAL)

	public void registartion() {
		restExecutor.executePOSTMethod(uri, setData());
		restValidator.expectedResponseCode(201).expectedResponseBody("id");
		
	}

}

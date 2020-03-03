package com.rest_assured.smokeapi;

import java.util.HashMap;
import org.testng.annotations.Test;

import com.rest_assured.testbase.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class PutDataTest extends TestBase {

	private String uri = "/api/users/2";
	private HashMap<String, String> data;

	public PutDataTest() throws Exception {
		super();
		data = new HashMap<>();
	}

	private HashMap<String, String> setData() {
		data.put("name", "morpheus");
		data.put("job", "zion resident");
		return data;
	}

	@Test
	@Description(" edit the data  ")
	@Severity(SeverityLevel.MINOR)
	@Issue("AM-111")
	public void putData() {
		restExecutor.executePUTMethod(uri, setData());
		restValidator.expectedResponseCode(200).expectedHeaderName("Connection", "keep-alive");

	}

}

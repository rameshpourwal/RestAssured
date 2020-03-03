package com.rest_assured.smokeapi;

import org.testng.annotations.Test;

import com.rest_assured.testbase.TestBase;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class GetWeatherData extends TestBase {

	private String uri = "/api/unknown/2";

	public GetWeatherData() throws Exception {
		super();
	}

	@Test
	@Description(" get the weather data ")
	@Severity(SeverityLevel.MINOR)
	
	public void getWeatherDataTest() {

		restExecutor.executeGETMethod(uri);
		restValidator.expectedResponseCode(200).expectedResponseBody("name").expectedHeaderName("Connection",
				"keep-alive");

	}

}

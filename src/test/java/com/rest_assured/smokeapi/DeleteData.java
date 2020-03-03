package com.rest_assured.smokeapi;

import org.testng.annotations.Test;
import com.rest_assured.testbase.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class DeleteData extends TestBase {

	public DeleteData() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	private String uri = "/api/users/2";

	@Test
	@Description(" Delete action performed ")
	@Severity(SeverityLevel.MINOR)
	@Issue("AM-111")
	public void deleteData() {
		restExecutor.executeDELETEMethod(uri);
		restValidator.expectedResponseCode(200).expectedHeaderName("Connection", "keep-alive");
	}

}

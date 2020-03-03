package com.rest_assured.utils;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import java.io.File;
import java.io.IOException;
import com.google.common.collect.ImmutableMap;

/**
 * @author rampourw
 *
 */
public class AllureUtils {

	public void allureEnvironment() throws IOException {

		allureEnvironmentWriter(
				ImmutableMap.<String, String>builder().put("Browser", getBrowser()).put("URL", getUrl()).build(),
				System.getProperty("user.dir").concat(File.separator).concat(getAllureReportDirectory())
						.concat(File.separator));

	}

	private String getUrl() throws IOException {

		return PropertyUtils.getConfigProperty("apiurl");

	}

	private String getBrowser() {
		return "API";

	}

	private String getAllureReportDirectory() throws IOException {

		return PropertyUtils.getProperty("allure.properties", "allure.results.directory");
	}

}

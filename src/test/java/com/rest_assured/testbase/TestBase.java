package com.rest_assured.testbase;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.rest_assured.elasticsearch.KibanaStatus;
import com.rest_assured.restapi.RestExecutor;
import com.rest_assured.restapi.RestValidator;
import com.rest_assured.utils.AllureUtils;
import com.rest_assured.utils.FileUtil;
import com.rest_assured.utils.ProcessUtils;
import com.rest_assured.utils.PropertyUtils;

/**
 * @author rampourw
 *
 */
public class TestBase {

	protected RestExecutor restExecutor;
	protected RestValidator restValidator;
	private ProcessUtils processUtils;
	private KibanaStatus kibanaStatus;

	public TestBase() throws Exception {
		restExecutor = new RestExecutor();
		restValidator = new RestValidator();
		processUtils = new ProcessUtils();
		kibanaStatus = new KibanaStatus();
	}

	/**
	 * Setting up the allure , log4j env and delete the file directories and elastic
	 * search data
	 * 
	 * @throws Exception
	 */
	@BeforeSuite
	public void setAllureEnvironment() throws Exception {
		new FileUtil().deleteDirectories();
		PropertyUtils.setLogger();
		processUtils.deleteElasticSearchData();
		processUtils.startKibanaServer();
		new AllureUtils().allureEnvironment();
		kibanaStatus.openKibanaServer();

	}

	/**
	 * refresh the kibana server to view the live test results
	 */
	@AfterMethod
	public void refreshKibanaServer() {
		kibanaStatus.refreshKibanaServer();
	}

	/**
	 * showing the allure report
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@AfterSuite
	public void showAllureReport() throws IOException, InterruptedException {
		processUtils.showAllureReport();
		kibanaStatus.closeKibanaServer();
		processUtils.killKibanaProcess();
	}

}

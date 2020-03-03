package com.rest_assured.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * @author rampourw
 *
 */
public class Driver {

	private static String strTestSuites;

	public static void main(String[] args) throws Exception {

		CustomLog.info("---------------Preparing For Execution-------------");

		List<String> selectedModules;
		if (strTestSuites != null) {
			selectedModules = Arrays.asList(strTestSuites.split(","));
		} else {
			ExcelReader excelConroller = new ExcelReader(
					PropertyUtils.getResourcePath().concat("testdata").concat(File.separator).concat("TestCase.xls"));
			selectedModules = excelConroller.getSelectedModuleNames();
		}

		List<XmlSuite> suites = new ArrayList<>();

		XmlSuite browserSuite = new XmlSuite();

		List<String> listeners = new ArrayList<>();
		listeners.add("com.rest_assured.elasticsearch.ExecutionListener");
		browserSuite.setListeners(listeners);

		browserSuite.setName("Test Suite - framework");

		List<XmlClass> classes = new ArrayList<>();
		for (String ts : selectedModules) {

			XmlClass aClass = new XmlClass();
			aClass.setName(ts);
			classes.add(aClass);

		}

		XmlTest test = new XmlTest(browserSuite);
		test.setName("api_Test ");
		test.setClasses(classes);
		suites.add(browserSuite);

		CustomLog.debug(browserSuite.toXml());

		try (FileWriter writer = new FileWriter(new File("MasterSuite.xml"))) {
			writer.write(browserSuite.toXml());
			writer.flush();

		} catch (IOException e) {
			CustomLog.warn("file not found " + e.getMessage());
		}

		CustomLog.debug(new File("MasterSuite.xml").getAbsolutePath());

	}

}

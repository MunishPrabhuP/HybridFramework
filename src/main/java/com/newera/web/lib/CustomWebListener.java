package com.newera.web.lib;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.newera.lib.Driver;

import io.qameta.allure.Allure;

public class CustomWebListener implements ITestListener, ISuiteListener {

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		try {
			WebHelper.loadEnviroment();
			WebHelper.setUpEnvironment();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		Driver driver;

		ITestContext context = result.getTestContext();
		driver = (Driver) context.getAttribute("Driver");
		Allure.addAttachment("Test Failure Screenshot", new ByteArrayInputStream(driver.takeScreenshot()));
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

}

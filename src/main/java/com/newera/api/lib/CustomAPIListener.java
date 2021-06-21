package com.newera.api.lib;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomAPIListener implements ITestListener, ISuiteListener {

	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		try {
			ApiHelper.loadEndPoints();
			ApiHelper.loadEnviroment();
			ApiHelper.setUpEnvironment();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

}

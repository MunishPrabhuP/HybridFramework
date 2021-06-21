package com.newera.api.testcases;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.newera.api.lib.ApiHelper;
import com.newera.api.public_api.Booker;
import com.newera.api.utils.BookerUtils;
import com.newera.lib.CustomLogger;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIAutomationCases {
	private Map<String, String> headers = new HashMap<String, String>();
	private BookerUtils bookerUtils;
	private SoftAssert s_assert;
	private Booker bookerApi;

	@SuppressWarnings("serial")
	@BeforeClass
	public void setUp() {
		CustomLogger.info("TestCase execution started");
		headers.put("Content-Type", ContentType.JSON.toString());
		headers.put("Accept", ContentType.JSON.toString());
		Map<String, String> authDetails = new HashMap<String, String>() {
			{
				put("username", ApiHelper.ENV_PROP.getProperty("USER_NAME"));
				put("password", ApiHelper.ENV_PROP.getProperty("PASSWORD"));
			}
		};
		bookerApi = new Booker(ApiHelper.ENV_PROP.getProperty("BASE_URL"), authDetails);
		bookerUtils = new BookerUtils();
	}

	@BeforeMethod
	public void beforeMethod() {
		s_assert = new SoftAssert();
	}

	@AfterMethod
	public void afterMethod() {
		s_assert.assertAll();
	}

	@Test(priority = 1, description = "Verify whether the User is able to create a resource", enabled = true)
	@Feature("Backend Automation")
	@Story("Booking Public API")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyCreateBooking() {
		CustomLogger.info("TestCase verifyCreateBooking started");
		JSONObject bookingDetails = new JSONObject(bookerUtils.generateBookingDetails(new HashMap<String, Object>()));
		Response response = bookerApi.createBooking(bookingDetails, headers);
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPath = response.jsonPath();
		s_assert.assertEquals(jsonPath.getString("booking.firstname"), bookingDetails.get("firstname"));
		s_assert.assertEquals(jsonPath.getString("booking.lastname"), bookingDetails.get("lastname"));
		s_assert.assertEquals(Integer.parseInt(jsonPath.getString("booking.totalprice")),
				Integer.parseInt(bookingDetails.get("totalprice").toString()));
		s_assert.assertEquals(Boolean.parseBoolean(jsonPath.getString("booking.depositpaid")),
				Boolean.parseBoolean(bookingDetails.get("depositpaid").toString()));
		s_assert.assertEquals(jsonPath.getString("booking.additionalneeds"), bookingDetails.get("additionalneeds"));
		s_assert.assertEquals(jsonPath.getMap("booking.bookingdates"), bookingDetails.get("bookingdates"));
		CustomLogger.info("TestCase verifyCreateBooking completed successfully");
	}

	@Test(priority = 2, description = "Verify whether the User is able to modify the created resource", enabled = true)
	@Feature("Backend Automation")
	@Story("Booking Public API")
	@Severity(SeverityLevel.NORMAL)
	public void verifyUpdateBooking() {
		long bookingId;

		CustomLogger.info("TestCase verifyUpdateBooking started");
		JSONObject bookingCreateDetails = new JSONObject(
				bookerUtils.generateBookingDetails(new HashMap<String, Object>()));
		Response createBookingResp = bookerApi.createBooking(bookingCreateDetails, headers);
		CustomLogger.info("verifyUpdateBooking -> createBooking response " + createBookingResp.asString());
		Assert.assertEquals(createBookingResp.getStatusCode(), 200);
		JsonPath jsonPath = createBookingResp.jsonPath();
		bookingId = jsonPath.getLong("bookingid");

		JSONObject bookingUpdateDetails = new JSONObject(
				bookerUtils.generateBookingDetails(new HashMap<String, Object>()));
		Response updBookingResp = bookerApi.updateBooking(bookingId, bookingUpdateDetails, headers);
		Assert.assertEquals(updBookingResp.getStatusCode(), 200);
		jsonPath = updBookingResp.jsonPath();
		s_assert.assertEquals(jsonPath.getString("firstname"), bookingUpdateDetails.get("firstname"));
		s_assert.assertEquals(jsonPath.getString("lastname"), bookingUpdateDetails.get("lastname"));
		s_assert.assertEquals(Integer.parseInt(jsonPath.getString("totalprice")),
				Integer.parseInt(bookingUpdateDetails.get("totalprice").toString()));
		s_assert.assertEquals(Boolean.parseBoolean(jsonPath.getString("depositpaid")),
				Boolean.parseBoolean(bookingUpdateDetails.get("depositpaid").toString()));
		s_assert.assertEquals(jsonPath.getString("additionalneeds"), bookingUpdateDetails.get("additionalneeds"));
		s_assert.assertEquals(jsonPath.getMap("bookingdates"), bookingUpdateDetails.get("bookingdates"));
		CustomLogger.info("TestCase verifyUpdateBooking completed successfully");
	}

	@Test(priority = 3, description = "Verify whether the User is able to view the resource", enabled = true)
	@Feature("Backend Automation")
	@Story("Booking Public API")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyGetBooking() {
		long bookingId;

		CustomLogger.info("TestCase verifyGetBooking started");
		JSONObject bookingCreateDetails = new JSONObject(
				bookerUtils.generateBookingDetails(new HashMap<String, Object>()));
		Response createBookingResp = bookerApi.createBooking(bookingCreateDetails, headers);
		CustomLogger.info("verifyGetBooking -> createBooking response " + createBookingResp.asString());
		Assert.assertEquals(createBookingResp.getStatusCode(), 200);
		JsonPath jsonPath = createBookingResp.jsonPath();
		bookingId = jsonPath.getLong("bookingid");

		Response getBookingResp = bookerApi.getBooking(bookingId, headers);
		Assert.assertEquals(getBookingResp.getStatusCode(), 200);
		jsonPath = getBookingResp.jsonPath();
		s_assert.assertEquals(jsonPath.getString("firstname"), bookingCreateDetails.get("firstname"));
		s_assert.assertEquals(jsonPath.getString("lastname"), bookingCreateDetails.get("lastname"));
		s_assert.assertEquals(Integer.parseInt(jsonPath.getString("totalprice")),
				Integer.parseInt(bookingCreateDetails.get("totalprice").toString()));
		s_assert.assertEquals(Boolean.parseBoolean(jsonPath.getString("depositpaid")),
				Boolean.parseBoolean(bookingCreateDetails.get("depositpaid").toString()));
		s_assert.assertEquals(jsonPath.getString("additionalneeds"), bookingCreateDetails.get("additionalneeds"));
		s_assert.assertEquals(jsonPath.getMap("bookingdates"), bookingCreateDetails.get("bookingdates"));
		CustomLogger.info("TestCase verifyGetBooking completed successfully");
	}

	@Test(priority = 4, description = "Verify whether the User is able to delete the resource", enabled = true)
	@Feature("Backend Automation")
	@Story("Booking Public API")
	@Severity(SeverityLevel.NORMAL)
	public void verifyDeleteBooking() {
		long bookingId;

		CustomLogger.info("TestCase verifyDeleteBooking started");
		JSONObject bookingCreateDetails = new JSONObject(
				bookerUtils.generateBookingDetails(new HashMap<String, Object>()));
		Response createBookingResp = bookerApi.createBooking(bookingCreateDetails, headers);
		CustomLogger.info("verifyDeleteBooking -> createBooking response " + createBookingResp.asString());
		Assert.assertEquals(createBookingResp.getStatusCode(), 200);
		JsonPath jsonPath = createBookingResp.jsonPath();
		bookingId = jsonPath.getLong("bookingid");

		Response deleteBookingResp = bookerApi.deleteBooking(bookingId, headers);
		Assert.assertEquals(deleteBookingResp.getStatusCode(), 201);
		CustomLogger.info("TestCase verifyDeleteBooking completed successfully");
	}

	@SuppressWarnings("serial")
	@Test(priority = 5, description = "Verify unauthorized access of resource", enabled = true)
	@Feature("Backend Automation")
	@Story("Booking Public API")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyUnauthorizedAccess() {
		Map<String, String> authDetails = new HashMap<String, String>() {
			{
				put("username", "INVALID");
				put("password", "INVALID");
			}
		};
		Booker bookerInvalidAPIInstance = new Booker(ApiHelper.ENV_PROP.getProperty("BASE_URL"), authDetails);
		long bookingId;

		CustomLogger.info("TestCase verifyUnauthorizedAccess started");
		JSONObject bookingCreateDetails = new JSONObject(
				bookerUtils.generateBookingDetails(new HashMap<String, Object>()));
		Response createBookingResp = bookerApi.createBooking(bookingCreateDetails, headers);
		Assert.assertEquals(createBookingResp.getStatusCode(), 200);
		JsonPath jsonPath = createBookingResp.jsonPath();
		bookingId = jsonPath.getLong("bookingid");

		Response deleteBookingResp = bookerInvalidAPIInstance.deleteBooking(bookingId, headers);
		Assert.assertEquals(deleteBookingResp.getStatusCode(), 403);
		CustomLogger.info("TestCase verifyUnauthorizedAccess completed successfully");
	}
}

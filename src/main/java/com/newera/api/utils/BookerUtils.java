package com.newera.api.utils;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

public class BookerUtils extends TestUtils {
	private Faker faker;

	public BookerUtils() {
		// TODO Auto-generated constructor stub
		faker = new Faker();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> generateBookingDetails(Map<String, Object> bookingInfo) {
		Map<String, Object> bookingDetails = new HashMap<String, Object>();

		if (bookingInfo.containsKey("firstname")) {
			bookingDetails.put("firstname", bookingInfo.get("firstname"));
		} else {
			bookingDetails.put("firstname", faker.name().firstName());
		}
		if (bookingInfo.containsKey("lastname")) {
			bookingDetails.put("lastname", bookingInfo.get("lastname"));
		} else {
			bookingDetails.put("lastname", faker.name().lastName());
		}
		if (bookingInfo.containsKey("totalprice")) {
			bookingDetails.put("totalprice", bookingInfo.get("totalprice"));
		} else {
			bookingDetails.put("totalprice", faker.number().numberBetween(100, 1000));
		}
		if (bookingInfo.containsKey("depositpaid")) {
			bookingDetails.put("depositpaid", bookingInfo.get("depositpaid"));
		} else {
			bookingDetails.put("depositpaid", faker.bool().bool());
		}
		if (bookingInfo.containsKey("additionalneeds")) {
			bookingDetails.put("additionalneeds", bookingInfo.get("additionalneeds"));
		} else {
			bookingDetails.put("additionalneeds", faker.color().name());
		}
		if (bookingInfo.containsKey("bookingdates")) {
			bookingDetails.put("bookingdates", bookingInfo.get("bookingdates"));
		} else {
			JSONObject bookingDates = new JSONObject();
			bookingDates.put("checkin", generateFutureDate("yyyy-MM-dd", 5));
			bookingDates.put("checkout", generateFutureDate("yyyy-MM-dd", 15));
			bookingDetails.put("bookingdates", bookingDates);
		}
		return bookingDetails;
	}
}

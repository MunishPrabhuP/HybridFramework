package com.newera.api.public_api;

import java.util.Map;

import org.json.simple.JSONObject;

import com.newera.api.lib.ApiHelper;
import com.newera.api.lib.Login;

import io.restassured.response.Response;

public class Booker extends Login {
	private String endPoint = ApiHelper.END_POINT_PROP.getProperty("BOOKING");

	public Booker(String baseURI, Map<String, String> authDetails) {
		// TODO Auto-generated constructor stub
		super(baseURI, authDetails);
	}

	public Response createBooking(JSONObject bookingDetails, Map<String, String> headers) {
		headers.put("Cookie", authToken);
		Response response = apiClient.POST(endPoint, bookingDetails.toJSONString(), headers);
		return response;
	}

	public Response updateBooking(long bookingId, JSONObject bookingDetails, Map<String, String> headers) {
		headers.put("Cookie", authToken);
		Response response = apiClient.PUT(endPoint + "/" + bookingId, bookingDetails.toJSONString(), headers);
		return response;
	}

	public Response getBooking(long bookingId, Map<String, String> headers) {
		headers.put("Cookie", authToken);
		Response response = apiClient.GET(endPoint + "/" + bookingId, headers);
		return response;
	}

	public Response deleteBooking(long bookingId, Map<String, String> headers) {
		headers.put("Cookie", authToken);
		Response response = apiClient.DELETE(endPoint + "/" + bookingId, headers);
		return response;
	}
}

package com.newera.lib;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {
	private String baseURI;
	private RequestSpecification reqSpec;

	public RestAssuredClient(String baseURI) {
		// TODO Auto-generated constructor stub
		this.baseURI = baseURI;
		reqSpec = RestAssured.given();
	}

	public Response POST(String endPoint, String requestBody, Map<String, String> headers) {
		CustomLogger.info("Sending POST request to " + endPoint);
		Response response = reqSpec.body(requestBody).headers(headers).post(baseURI + endPoint);
		return response;
	}

	public Response PUT(String endPoint, String requestBody, Map<String, String> headers) {
		CustomLogger.info("Sending PUT request to " + endPoint);
		Response response = reqSpec.body(requestBody).headers(headers).put(baseURI + endPoint);
		return response;
	}

	public Response PATCH(String endPoint, String requestBody, Map<String, String> headers) {
		CustomLogger.info("Sending PATCH request to " + endPoint);
		Response response = reqSpec.body(requestBody).headers(headers).patch(baseURI + endPoint);
		return response;
	}

	public Response GET(String endPoint, Map<String, String> headers) {
		CustomLogger.info("Sending GET request to " + endPoint);
		Response response = reqSpec.headers(headers).get(baseURI + endPoint);
		return response;
	}

	public Response DELETE(String endPoint, Map<String, String> headers) {
		CustomLogger.info("Sending DELETE request to " + endPoint);
		Response response = reqSpec.headers(headers).delete(baseURI + endPoint);
		return response;
	}
}

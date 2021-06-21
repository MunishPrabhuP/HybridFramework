package com.newera.api.lib;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.newera.lib.RestAssuredClient;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Login {
	protected RestAssuredClient apiClient;
	private String endPoint = ApiHelper.END_POINT_PROP.getProperty("AUTH");
	protected String authToken;

	public Login(String baseURI, Map<String, String> authDetails) {
		// TODO Auto-generated constructor stub
		apiClient = new RestAssuredClient(baseURI);
		authToken = generateAuthToken(authenticate(authDetails));
	}

	private Response authenticate(Map<String, String> authDetails) {
		Map<String, String> headers = new HashMap<String, String>();

		headers.put("Content-Type", ContentType.JSON.toString());
		Response response = apiClient.POST(endPoint, new JSONObject(authDetails).toJSONString(), headers);
		return response;
	}

	public String generateAuthToken(Response authResp) {
		JsonPath jsonPath = authResp.jsonPath();

		return "token=" + jsonPath.getString("token");
	}
}

package restapi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithJsonObject {

	@Test
	public void PostCall() {
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		JSONObject PostBody = new JSONObject();
		
		PostBody.put("name", "EclipseJSONObject");
		PostBody.put("salary", "6500");
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(PostBody.toString())
				.post("employees/create");
		
//		Verify reponse code 
		int ActResponse = response.statusCode();
		int Expected = 201;
		Assert.assertEquals(ActResponse, Expected);
		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
		
//		Verify name
		Assert.assertTrue(ResponseBody.contains("EclipseJSONObject"));
//verify name using json path
		JsonPath jpath = response.jsonPath();
		String name = jpath.get("name");
		
		String expected = "EclipseJSONObject";
		Assert.assertEquals(name,expected);
	}
}

package restapi;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PutRequest {

	@Test
	public void PostCall() {
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		Map<Object,String> PostBody = new HashMap<Object,String>();
		PostBody.put("name", "EclipseHashMapUpdate");
		PostBody.put("salary", "3500");
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(PostBody)
				.put("employees/8");
		
//		Verify reponse code 
		int ActResponse = response.statusCode();
		int Expected = 200;
		Assert.assertEquals(ActResponse, Expected);
		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
	}
}

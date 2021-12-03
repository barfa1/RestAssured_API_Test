package restapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequest {
	
	@Test
	public void DeleteCall() {
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/employees/8");
		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
		int ActResponse = response.statusCode();
		int Expected = 200;
		Assert.assertEquals(ActResponse, Expected);
	}
}

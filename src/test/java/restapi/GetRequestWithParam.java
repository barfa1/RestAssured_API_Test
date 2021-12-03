package restapi;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParam {
	
	@Test
	public void GetCall() {
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
//		Response response = request.get("/employees/2");
		Response response = request.param("id", "1").get("/employees");

		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
		
//		Verify reponse code 
		int ActResponse = response.statusCode();
		int Expected = 200;
		Assert.assertEquals(ActResponse, Expected);
		
//		Verify reponse header 
		System.out.println(response.getHeaders());
		String ActHeader = response.getHeader("Content-Type");
		String ExpectedHeader = "application/json; charset=utf-8";
		
		Assert.assertEquals(ActHeader, ExpectedHeader);
		
		// Verify reponse body
		
		Assert.assertTrue(ResponseBody.contains("Pankaj"));
		
		JsonPath jpath = response.jsonPath();
		List <String> names = jpath.get("name");
		String actual = names.get(0);
		String expected = "Pankaj";
		Assert.assertEquals(actual,expected);
		
	}
}

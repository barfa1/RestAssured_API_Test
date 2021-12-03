package restapi;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	
	@Test
	public void GetCall() {
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/employees");
		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
		
		// Verify reponse body
		
//				Assert.assertTrue(ResponseBody.contains("Pankaj"));
				
				JsonPath jpath = response.jsonPath();
				List <String> names = jpath.get("name");
				for (String name : names) {
					
					System.out.println(name);
				}
		
	}
}

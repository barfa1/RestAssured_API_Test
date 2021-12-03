package restapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostCallWithJSONFile {


	@Test
	public void PostCall() throws IOException {
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification request = RestAssured.given();
		String Jsonbody = ReadJsonFile("data.json");
		Response response = request.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Jsonbody)
				.post("employees/create");
		
//		Verify reponse code 
		int ActResponse = response.statusCode();
		int Expected = 201;
		Assert.assertEquals(ActResponse, Expected);
		String ResponseBody = response.body().asString();
		System.out.println(ResponseBody);
		
//		Verify name
		Assert.assertTrue(ResponseBody.contains("JsonFile"));
//verify name using json path
		JsonPath jpath = response.jsonPath();
		String name = jpath.get("name");
		
		String expected = "JsonFile";
		Assert.assertEquals(name,expected);
	}
	
	public String ReadJsonFile(String Filename) throws IOException {
		String data = new String(Files.readAllBytes(Paths.get(Filename)));
		return data;
		
	}
}

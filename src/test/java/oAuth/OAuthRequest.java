package oAuth;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class OAuthRequest {

	@Test
	public void PostCall() {
		RestAssured.given()
		.auth()
		.oauth2("access_token")
		.baseUri("https://api.github.com")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body("{\n" + 
				"    \"name\": \"PostmanTest2\",\n" + 
				"    \"desription\": \"Test Repo\",\n" + 
				"    \"homepage\": \"https://github.com/\",\n" + 
				"    \"private\": false,\n" + 
				"    \"has_issues\": true,\n" + 
				"    \"has_projects\": true,\n" + 
				"    \"has_wiki\": true\n" + 
				"}")
		.when()
		.post("/user/repos")
		.then()
		.statusCode(201)
		.log()
		.all();
	}
}

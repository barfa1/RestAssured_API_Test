package restAPIBDD;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequest {
	@Test
	public void PostCall() {
		Map<Object,String> PostBody = new HashMap<Object,String>();
		PostBody.put("name", "BDDPost");
		PostBody.put("salary", "8700");
		RestAssured.given()
		.baseUri("http://localhost:7000")
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(PostBody)
		.when()
		.post("/employees/create")
		.then()
		.statusCode(201)
		.log()
		.all();
	}
}

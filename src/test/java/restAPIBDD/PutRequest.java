package restAPIBDD;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequest {

@Test
public void PutCall() {
	Map<Object,String> PostBody = new HashMap<Object,String>();
	PostBody.put("name", "BDDPut");
	PostBody.put("salary", "8100");
	RestAssured.given()
	.baseUri("http://localhost:7000")
	.contentType(ContentType.JSON)
	.accept(ContentType.JSON)
	.body(PostBody)
	.when()
	.put("/employees/23")
	.then()
	.statusCode(200)
	.log()
	.all();
}
		
}

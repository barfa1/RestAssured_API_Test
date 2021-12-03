package restapiChaining;

import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class EndToEndTest {

	@Test
	public void test1() {
		
		// ---------------------POST Call---------------------------
		
		RestAssured.baseURI = "http://localhost:7000";
		RequestSpecification PostRequest = RestAssured.given();
		Map<Object,String> PostBody = new HashMap<Object,String>();
		PostBody.put("name", "PostApiChain");
		PostBody.put("salary", "3500");
		
		Response PostResponse = PostRequest.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(PostBody)
				.post("employees/create");
		
		String PostResponseBody = PostResponse.body().asString();
		System.out.println(PostResponseBody);
		

		JsonPath jpath = PostResponse.jsonPath();
		String PostResponseId = jpath.get("id").toString();
		
		int PostActResponse = PostResponse.statusCode();
		System.out.println(PostActResponse);
		int PostExpected = 201;
		Assert.assertEquals(PostActResponse, PostExpected);

		
        // ---------------------PUT Call---------------------------
		
		RequestSpecification PutRequest = RestAssured.given();

		Map<Object,String> PutBody = new HashMap<Object,String>();
		PutBody.put("name", "PutApiChain");
		PutBody.put("salary", "3600");
		Response PutResponse = PutRequest.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(PutBody)
				.put("employees/"+PostResponseId);
		
		String PutResponseBody = PutResponse.body().asString();
		System.out.println(PutResponseBody);
		
		int PutActResponse = PutResponse.statusCode();
		int PutExpected = 200;
		Assert.assertEquals(PutActResponse, PutExpected);
		
		// ---------------------Delete Call---------------------------

		
		RequestSpecification DeleteRequest = RestAssured.given();
		Response DeleteResponse = DeleteRequest.delete("/employees/"+PostResponseId);
		
		String DeleteResponseBody = DeleteResponse.body().asString();
		System.out.println(DeleteResponseBody);
		
		
		int DelActResponse = DeleteResponse.statusCode();
		int DelExpected = 200;
		Assert.assertEquals(DelActResponse, DelExpected);
		
		// ---------------------Get Call---------------------------
		
		RequestSpecification GetRequest = RestAssured.given();
		Response GetResponse = GetRequest.get("/employees/"+PostResponseId);
//		Response GetResponse = GetRequest.param("id", PostResponseId).get("/employees");

		String GetResponseBody = GetResponse.body().asString();
		System.out.println(GetResponseBody);
		
//		Verify reponse code 
		int GetActResponse = GetResponse.statusCode();
		int GetExpected = 404;
		Assert.assertEquals(GetActResponse, GetExpected);
	}
}

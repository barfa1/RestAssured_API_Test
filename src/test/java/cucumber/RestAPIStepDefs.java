package cucumber;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestAPIStepDefs {
	
	RequestSpecification request;
	Response response ;
	
	
	@Given("^I have the base URI \"([^\"]*)\"$")
    public void i_have_the_base_uri_something(String baseURI) throws Throwable {
        request = RestAssured.given().baseUri(baseURI);
    }

    @When("^I perform the Get Operation$")
    public void i_perform_the_get_operation() throws Throwable {
        response = request.get();
    }

    @Then("^I should get the response$")
    public void i_should_get_the_response() throws Throwable {
        Assert.assertNotNull(response);
    }
    
    @Then("Response Code should be {int}")
    public void response_code_should_be(Integer status) {
        // Write code here that turns the phrase above into concrete actions
    	//Verify reponse code 
    			int ActResponse = response.statusCode();
    			int Expected = status;
    			Assert.assertEquals(ActResponse, Expected);
    	    }
    
    	
}

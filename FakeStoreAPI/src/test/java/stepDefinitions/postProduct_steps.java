package stepDefinitions;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import hooks.hook_spec;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import utils.configReader;
import utils.jsonUtils;

public class postProduct_steps {
@Given("the product post API is available")
public void the_product_post_api_is_available() {
		hook_spec.httpRequest= RestAssured.given();
	}

	@When("I request add product of {string}")
	public void i_request_add_product_of(String fileName) throws IOException {
	    
	    
		hook_spec.response= hook_spec.httpRequest.header("Content-Type", "application/json")
	   .body(
	   jsonUtils.fileToNodeConverter
	   (new File(configReader.getProperty("testDataPath")+"/"+fileName))
	   ).post(configReader.getProperty("endpoint_postProduct"));
	   
	  
	}
	@Then("the response should matches to response code {string}")
	public void the_response_should_matches_to_response_code(String expStatusCode) {
		
		hook_spec.response
		    .then()
		    .statusCode(Integer.valueOf(expStatusCode));
		 System.out.println(hook_spec.response.getBody().asString());
	}
	@Then("response should matches the expected product from {string}")
	public void response_should_matches_the_expected_product_from(String fileName) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	
	    Assert.assertEquals(
	    		jsonUtils.fileToNodeConverter(new File(configReader.getProperty("testDataPath")+"/"+fileName)) ,
	    		jsonUtils.stringToNodeConverter(hook_spec.response.asString()),
	    		"Assertion fail - product not added properly");
	    
	    
	}
}

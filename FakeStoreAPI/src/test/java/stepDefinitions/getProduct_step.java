package stepDefinitions;

import hooks.hook_spec;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import utils.configReader;

public class getProduct_step {
	
	
	@Given("the get product API is available")
	public void the_get_product_api_is_available() {
		hook_spec.httpRequest= RestAssured.given();
	}

	@When("I request  product having id  {string}")
	public void i_request_product_having_id(String id) {
		hook_spec.response = hook_spec.httpRequest
			     .given()
			     .when()
			     .get(configReader.getProperty("endpoint_getProduct")+id);
	}

	@Then("the response should match to response code {string} and returns product object")
	public void the_response_should_match_to_response_code_and_returns_product_object(String exp_statusCode) {
		hook_spec.response
	    .then()
	    .statusCode(Integer.valueOf(exp_statusCode));
	}
}

package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.configReader;
import utils.jsonUtils;

public class GetAllProducts_steps {
	private Response response;
	private RequestSpecification httpRequest;
	private final ObjectMapper mapper = new ObjectMapper();
	
@Given("the product API is available")
public void product_API_is_available() {
	RestAssured.baseURI= configReader.getProperty("baseURI");
	httpRequest = RestAssured.given(); 
}

@When("I request all products")
public void request_all_products() {
	  response = httpRequest
			     .given()
			     .when()
			     .get(configReader.getProperty("endpoint_getAllProject"));
}

@Then("the response should match to response code {string}")
public void the_response_should_match_to_response_code(String exp_statusCode ) {
    // Write code here that turns the phrase above into concrete actions
    response
    .then()
    .statusCode(Integer.valueOf(exp_statusCode));
}

@Then("the response should match the expected products from {string}")
public void the_response_should_match_the_expected_products_from(String FileName) throws IOException {
    // Write code here that turns the phrase above into concrete actions
    //throw new io.cucumber.java.PendingException();
	 JsonNode expectedJson = jsonUtils.fileToNodeConverter(new File(configReader.getProperty("testDataPath")+"/"+FileName));

     // Load actual response JSON
     JsonNode actualJson = jsonUtils.stringToNodeConverter(response.asString());

     // Compare
     Assert.assertEquals(expectedJson, actualJson,"Actual response does not match expected JSON");
}
}
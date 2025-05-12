package stepDefinitions;


import POJO.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.asserts.LoggingAssert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import hooks.hook_spec;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import utils.configReader;
import utils.jsonUtils;

public class GetAllProducts_steps {
	
	List <Product> products;
	
	@Given("the FakeStore API is availables")
	public void the_fake_store_api_is_availables() {
		hook_spec.httpRequest= RestAssured.given();
	    
	}

	@When("I send a GET request to getAllProduct endpoint")
	public void i_send_a_get_request_to_get_all_product_endpoint() {
		hook_spec.response = hook_spec.httpRequest
			     .given()
			     .when()
			     .get(configReader.getProperty("endpoint_getAllProduct"));
	}

	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(Integer expCode) {
		hook_spec.response.then().statusCode(expCode);
	}

	@Then("the response should contain a list of products")
	public void the_response_should_contain_a_list_of_products() throws IOException {
	  Assert.assertEquals(
			  jsonUtils.fileToNodeConverter(new File(configReader.getProperty("testDataPath")+"/"+"allProducts.json")),
			  jsonUtils.stringToNodeConverter(hook_spec.response.asString()),
			  "all Products avalable");
	}

	@Then("each product should have an id, title, price, and category")
	public void each_product_should_have_an_id_title_price_and_category() throws JsonMappingException, JsonProcessingException {
		products = jsonUtils.getMapper().readValue(hook_spec.response.asString(), jsonUtils.getMapper().getTypeFactory().constructCollectionType(List.class, Product.class));
		for (Product product : products) {
            // Validate basic product attributes
            Assert.assertTrue(product.getId() > 0, "Product ID should be positive");
            
            Assert.assertNotNull(product.getTitle(), "Product title should not be null");
            Assert.assertFalse(product.getTitle().isEmpty(), "Product title should not be empty");
            
            Assert.assertTrue(product.getPrice() > 0, "Product price should be positive");
            
            Assert.assertNotNull(product.getDescription(), "Product description should not be null");
            Assert.assertFalse(product.getDescription().isEmpty(), "Product description should not be empty");
            
            Assert.assertNotNull(product.getCategory(), "Product category should not be null");
            Assert.assertFalse(product.getCategory().isEmpty(), "Product category should not be empty");
            
            Assert.assertNotNull(product.getImage(), "Product image URL should not be null");
            Assert.assertTrue(product.getImage().startsWith("http"), "Product image should be a valid URL");
        }
	}
}
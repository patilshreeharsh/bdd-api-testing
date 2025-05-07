package hooks;


import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.configReader;


public class hook_spec {
	public static RequestSpecification httpRequest;
	public static Response response;
	@Before
	public void setup() {
		RestAssured.baseURI = configReader.getProperty("baseURI");
	  
	    
	}

	

	

	
}

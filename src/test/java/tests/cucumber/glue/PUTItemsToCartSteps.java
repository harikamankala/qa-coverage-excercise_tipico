package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.Constants;

public class PUTItemsToCartSteps {

    @Autowired
    private TestRestTemplate restTemplate;
    
    private ResponseEntity<String> response;
    
    @When("PUT items with item id {int} is invoked")	
    public void put_cart_items_is_invoked(Integer id) {    	
    	response = restTemplate.exchange(Constants.ITEMS_ID, HttpMethod.PUT, null, String.class, id);    		
    }
    
    @Then("response code for PUT items is {int}")
    public void response_code_is(Integer code) {
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));
    }

    @When("PUT items with item id {string} is invoked")
    public void put_cart_items_abc_is_invoked(String id) {
    	response = restTemplate.exchange(Constants.ITEMS_ID, HttpMethod.PUT, null, String.class, id);   
    }
 
}

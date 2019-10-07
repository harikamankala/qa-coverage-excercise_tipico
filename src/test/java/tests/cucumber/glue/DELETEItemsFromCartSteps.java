package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.data.Item;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.Constants;

public class DELETEItemsFromCartSteps {

    @Autowired
    private TestRestTemplate restTemplate;
    
    private ResponseEntity<String> response;
    private ObjectMapper mapper = new ObjectMapper();
    
    @When("DELETE items with item id {int} is invoked")	
    public void delete_cart_items_is_invoked(Integer id) {    	
    	response = restTemplate.exchange(Constants.ITEMS_ID, HttpMethod.DELETE, null, String.class, id);    		
    }
    
    @Then("response code for DELETE items is {int}")
    public void response_code_is(Integer code) {
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));
    }

    @When("DELETE items with item id {string} is invoked")
    public void delete_cart_items_abc_is_invoked(String id) {
    	response = restTemplate.exchange(Constants.ITEMS_ID, HttpMethod.DELETE, null, String.class, id);   
    }
    
    @Given("cart is empty")	
    public void cart_is_empty() throws Exception {    	
    	response = restTemplate.getForEntity(Constants.GET_ITEMS, String.class);
    	Item[] items = mapper.readValue(response.getBody(), Item[].class);
    	for (Item item : items) {
    		restTemplate.exchange(Constants.ITEMS_ID, HttpMethod.DELETE, null, String.class, item.getId());
		}
    	restTemplate.exchange(Constants.DELETE_VOUCHER, HttpMethod.DELETE, null, String.class);
    }
 
}

package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.data.Item;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.Constants;

public class GETItemsSteps {
	
	@Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;    
    private ObjectMapper mapper = new ObjectMapper();

    @When("GET items is invoked")
    public void get_items_is_invoked() {
    	response = restTemplate.getForEntity(Constants.GET_ITEMS, String.class);
    }

    @Then("response code is {int}")
    public void response_code_is(Integer code) {
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));
    }

    @Then("response has all the valid {int} items")
    public void response_has_all_the_valid_items(int numberOfElements) throws Exception {
    	Item[] items = mapper.readValue(response.getBody(), Item[].class);
    	assertThat(items).hasSize(numberOfElements);    	
    }
 
}

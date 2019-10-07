package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.Constants;

public class DELETEVoucherFromCartSteps {

	@Autowired
    private TestRestTemplate restTemplate;
    
    private ResponseEntity<String> response;
    
	@When("DELETE voucher is invoked")
	public void delete_voucher_is_invoked() {
    	response = restTemplate.exchange(Constants.DELETE_VOUCHER, HttpMethod.DELETE, null, String.class);
	}

	@Then("response code for DELETE voucher is {int}")
	public void response_code_for_DELETE_voucher_is(Integer code) {
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));	    
	}  
 
}

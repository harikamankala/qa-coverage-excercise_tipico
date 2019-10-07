package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.Constants;

public class PUTVoucherToCartSteps {

    @Autowired
    private TestRestTemplate restTemplate;
    
    private ResponseEntity<String> response;
  
    @When("PUT voucher with voucher id {long} is invoked")
    public void put_voucher_with_voucher_id_is_invoked(Long id) {
    	response = restTemplate.exchange(Constants.PUT_VOUCHER, HttpMethod.PUT, null, String.class, id);
    }

    @Then("response code for PUT voucher is {int}")
    public void response_code_for_PUT_voucher_is(Integer code) {
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));
    }

    @When("PUT voucher with voucher id {string} is invoked")
    public void put_voucher_with_voucher_id_is_invoked(String id) {
    	response = restTemplate.exchange(Constants.PUT_VOUCHER, HttpMethod.PUT, null, String.class, id);
    }
 
}

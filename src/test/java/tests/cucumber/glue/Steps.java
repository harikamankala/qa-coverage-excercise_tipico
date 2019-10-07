package tests.cucumber.glue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.OnlineShop;
import com.tipico.data.Item;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = OnlineShop.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Steps {

    private ResponseEntity<String> responseEntity;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @When("endpoint {word} is called")
    public void urlBeingCalled(String url) {
        responseEntity = restTemplate.getForEntity(url, String.class);
    }

    @Then("call is successful")
    public void verifyCallSuccessful() {
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Then("response body contains Item array of {int} elements")
    public void verifyResponseContainsArrayOfElements(int numberOfElements) throws IOException {
        Item[] items = mapper.readValue(responseEntity.getBody(), Item[].class);
        assertThat(items).hasSize(numberOfElements);
    }


}

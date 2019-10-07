package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.data.Item;
import com.tipico.endpoints.Order;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.CommonFunctions;
import tests.cucumber.utils.Constants;

public class POSTOrderSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;
    private ObjectMapper mapper = new ObjectMapper();
    CommonFunctions commonFunctions = new CommonFunctions();
  
    @When("POST order is invoked")
    public void post_order_is_invoked() {
    	response = restTemplate.exchange(Constants.POST_ORDER, HttpMethod.POST, null, String.class);
    }

    @Then("response code for POST order is {int}")
    public void response_code_for_POST_order_is(Integer code) {
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));
    }

    @Then("order confirmation has all valid discounts and voucher discount as {int}")
    public void order_confirmation_has_following(Integer voucherDiscount) throws Exception {
    	
    	Order order = mapper.readValue(response.getBody(), Order.class);              
        List<Item> items = order.getItems();
        List<Float> itemsPrice = items.stream().map(Item::getPrice).collect(Collectors.toList());
        
        float originalPrice = itemsPrice.stream().reduce((float) 0, Float::sum);
        float paid = commonFunctions.getDiscountedPrice(originalPrice, voucherDiscount);
        Integer discount = commonFunctions.getDiscount(originalPrice);
        
        String description = "You've paid " + BigDecimal.valueOf(paid).setScale(2, RoundingMode.UP) + "$";
          	
        assertThat(order.getOriginalPrice()).isEqualTo(BigDecimal.valueOf(originalPrice).setScale(2, RoundingMode.UP));
        assertThat(order.getPaid()).isEqualTo(BigDecimal.valueOf(paid).setScale(2, RoundingMode.UP));
        assertThat(order.getDiscount()).isEqualTo(discount);
        assertThat(order.getVoucherDiscount()).isEqualTo(voucherDiscount);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        assertThat(formatter.format(order.getOrderDate())).isEqualTo(formatter.format(new Date()));
        assertThat(order.getDescription()).isEqualTo(description);
    
    }
 
}

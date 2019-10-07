package tests.cucumber.glue;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipico.data.Item;
import com.tipico.endpoints.CartDTO;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import tests.cucumber.utils.CommonFunctions;
import tests.cucumber.utils.Constants;

public class GETCartSteps {

	@Autowired
    private TestRestTemplate restTemplate;
	
    private ResponseEntity<String> response;
    private ObjectMapper mapper = new ObjectMapper();
    CommonFunctions commonFunctions = new CommonFunctions();
    
    @When("GET cart is invoked")
    public void get_items_is_invoked() {
    	response = restTemplate.getForEntity(Constants.GET_CART, String.class);
    }
    
    @Then("response code for GET cart is {int}")
    public void response_code_for_GET_cart_is(Integer code) {
    	System.out.println("Cart " + response.getBody());
    	assertThat(response.getStatusCode().toString().split(" ")[0]).isEqualTo(String.valueOf(code));
    }

    @Then("cart has all the valid {int} items")
    public void cart_has_all_the_valid_items(Integer numberOfElements) throws Exception {
    	CartDTO cart = mapper.readValue(response.getBody(), CartDTO.class);
    	assertThat(cart.getItems().size()).isEqualTo(numberOfElements);
    }
    
    @Then("discount is {int} and voucher discount is {int}")
    public void discount_is_and_voucher_discount_is(Integer discount, Integer voucherDiscount) throws Exception {
    	CartDTO cart = mapper.readValue(response.getBody(), CartDTO.class);
    	assertThat(cart.getDiscount()).isEqualTo(discount);
    	assertThat(cart.getVoucherDiscount()).isEqualTo(voucherDiscount);
    }

    @Then("total price and price after discount are same")
    public void total_price_and_price_after_discount_are_same() throws Exception {
    	CartDTO cart = mapper.readValue(response.getBody(), CartDTO.class);
    	assertThat(cart.getTotal_price()).isEqualTo(cart.getPrice_after_discount());
    }

    @Then("price after discount is less than total price")
    public void price_after_discount_is_less_than_total_price() throws Exception {
    	CartDTO cart = mapper.readValue(response.getBody(), CartDTO.class);
    	assertThat(cart.getPrice_after_discount()).isLessThan(cart.getTotal_price());
    }
    
    @Then("the cart has all valid values and voucher discount as {int}")
    public void the_cart_has_following(Integer voucherDiscount) throws Exception {
        
        CartDTO cart = mapper.readValue(response.getBody(), CartDTO.class);              
        List<Item> items = cart.getItems().stream().map(CartDTO.Tuple::getItem).collect(Collectors.toList());
        List<Float> itemsPrice = items.stream().map(Item::getPrice).collect(Collectors.toList());
        
        float total_price = itemsPrice.stream().reduce((float) 0, Float::sum);
        float price_after_discount = commonFunctions.getDiscountedPrice(total_price, voucherDiscount);
        Integer discount = commonFunctions.getDiscount(total_price);
        
        assertThat(cart.getTotal_price()).isEqualTo(total_price);
        assertThat(cart.getPrice_after_discount()).isEqualTo(price_after_discount);
        
        assertThat(cart.getDiscount()).isEqualTo(discount);
        assertThat(cart.getVoucherDiscount()).isEqualTo(voucherDiscount);
        
    }
 
}

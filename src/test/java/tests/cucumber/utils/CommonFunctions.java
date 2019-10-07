package tests.cucumber.utils;

public class CommonFunctions {
	
	public Integer getDiscount(float totalPrice) {
		
		return (0 <= totalPrice && totalPrice <= 100 ) ? 0 :
            (101 <= totalPrice && totalPrice <= 500) ? 5 :
		    (501 <= totalPrice && totalPrice <= 1000) ? 10 : 0;
	}
	
	public float getDiscountedPrice(float totalPrice, Integer voucherDiscount) {
		
		float discount = getDiscount(totalPrice) + voucherDiscount ;
		return (float) (totalPrice - ((totalPrice * discount)/100.00));
		
	}

}

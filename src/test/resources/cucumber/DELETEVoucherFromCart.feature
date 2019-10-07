Feature: As a user of Online shop
		I want to delete voucher
		So that I can apply suitable voucher

  Scenario: Verify DELETE `/cart/voucher` removes the voucher from cart
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 8 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200     
    When DELETE voucher is invoked
    Then response code for DELETE voucher is 200
    
  Scenario: Verify DELETE `/cart/voucher` when no voucher applied to cart
    
    Given cart is empty
    When DELETE voucher is invoked
    Then response code for DELETE voucher is 400
  
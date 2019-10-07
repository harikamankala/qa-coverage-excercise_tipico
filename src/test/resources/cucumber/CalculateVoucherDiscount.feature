Feature: As a user of Online shop
		I want to apply voucher and calculate discount
		So that I can place my order

  Scenario: Verify voucher discount when 0 vouchers applied
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 8 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    Then discount is 0 and voucher discount is 0  
    And total price and price after discount are same
    And the cart has all valid values and voucher discount as 0
    
  Scenario: Verify voucher discount when 1 vouchers applied
    
    Given cart is empty
    When PUT items with item id 8 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    Then discount is 0 and voucher discount is 2  
    And price after discount is less than total price
    And the cart has all valid values and voucher discount as 2
    
  Scenario: Verify voucher discount when 2 vouchers applied
    
    Given cart is empty
    When PUT items with item id 8 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200
    When PUT voucher with voucher id 1237557890 is invoked
    Then response code for PUT voucher is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    Then discount is 0 and voucher discount is 4  
    And price after discount is less than total price
    And the cart has all valid values and voucher discount as 4
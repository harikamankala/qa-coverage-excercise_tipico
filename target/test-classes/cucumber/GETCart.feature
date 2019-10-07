Feature: As a user of Online shop
		I want to see all items of my Cart
		So that I can place order
		
  Scenario: Verify GET /cart when cart is empty
    
    Given cart is empty
    When GET cart is invoked
    Then response code for GET cart is 200
    And cart has all the valid 0 items

  Scenario: Verify GET /cart returns the cart
    
    Given cart is empty
    When PUT items with item id 1 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 2 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    
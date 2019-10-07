Feature: As a user of Online shop
		I want to apply voucher
		So that I can discount on my order

  Scenario: Verify PUT `/cart/voucher/{id}` applies the voucher to cart
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 8 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200  
    
  Scenario: Verify PUT `/cart/voucher/{id}` when the voucher is already applied
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 8 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200  
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 400  
    
  Scenario: Verify PUT `/cart/voucher/{id}` when the voucher is with bad data 
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 'abc765' is invoked
    Then response code for PUT voucher is 400  
    
  Scenario: Verify PUT `/cart/voucher/{id}` when the voucher id is more than 10 digits
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 12345678910 is invoked
    Then response code for PUT voucher is 400 
    
  Scenario: Verify PUT `/cart/voucher/{id}` when the voucher is with special characters
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id '%/&%&&' is invoked
    Then response code for PUT voucher is 400
    
  Scenario: Verify PUT `/cart/voucher/{id}` when the voucher id is empty
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id ' ' is invoked
    Then response code for PUT voucher is 400
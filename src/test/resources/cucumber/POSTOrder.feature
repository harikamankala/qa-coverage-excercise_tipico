Feature: As a user of Online shop
		I want to place the order
		So that I can buy products online

  Scenario: Verify POST `/order` when items are in cart
    
    Given cart is empty
    When PUT items with item id 1 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 2 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
   
  Scenario: Verify POST `/order` when voucher discount is applied
    
    Given cart is empty
    When PUT items with item id 1 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 2 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 2
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 2
    
  Scenario: Verify POST `/order` when discount is applied
  
    Given cart is empty
    When PUT items with item id 5 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
      
  Scenario: Verify POST `/order` when discount and voucher discount is applied
    
    Given cart is empty
    When PUT items with item id 5 is invoked
    Then response code for PUT items is 200
    When PUT voucher with voucher id 1234567890 is invoked
    Then response code for PUT voucher is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 2
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 2
       
  Scenario: Verify POST `/order` when no items in cart
    
    Given cart is empty
    When POST order is invoked
    Then response code for POST order is 400
    
  Scenario: Verify POST `/order` and discount when total price less than 100
  
    Given cart is empty
    When PUT items with item id 1 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
  Scenario: Verify POST `/order` and discount when total price is 100
  
    Given cart is empty
    When PUT items with item id 3 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
  Scenario: Verify POST `/order` and discount when total price is 101
  
    Given cart is empty
    When PUT items with item id 3 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 4 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
  Scenario: Verify POST `/order` and discount when total price greater than 101 and less than 500
  
    Given cart is empty
    When PUT items with item id 5 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
  Scenario: Verify POST `/order` and discount when total price is 500
  
    Given cart is empty
    When PUT items with item id 6 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
   Scenario: Verify POST `/order` and discount when total price is 501
  
    Given cart is empty
    When PUT items with item id 6 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 4 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
  Scenario: Verify POST `/order` and discount when total price greater than 501 and less than 1000
    
    Given cart is empty
    When PUT items with item id 6 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 4 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 1 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
      
  Scenario: Verify POST `/order` and discount when total price is 1000
  
    Given cart is empty
    When PUT items with item id 7 is invoked
    Then response code for PUT items is 200
    When GET cart is invoked
    Then response code for GET cart is 200
    And the cart has all valid values and voucher discount as 0
    When POST order is invoked
    Then response code for POST order is 200
    And order confirmation has all valid discounts and voucher discount as 0
    
  
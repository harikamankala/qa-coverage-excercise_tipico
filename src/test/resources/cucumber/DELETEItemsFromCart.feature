Feature: As a user of Online shop
		I want to remove items from cart
		So that I can order item of my interest

  Scenario: Verify DELETE `/cart/items/{id}` removes the item from cart
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When DELETE items with item id 9 is invoked
    Then response code for DELETE items is 200
    
  Scenario: Verify DELETE `/cart/items/{id}` remove item when item is not in cart
    
    Given cart is empty
    When DELETE items with item id 9 is invoked
    Then response code for DELETE items is 400
     
  Scenario: Verify DELETE `/cart/items/{id}` with bad data
    
    Given cart is empty
    When DELETE items with item id 'abc' is invoked
    Then response code for DELETE items is 400
    
  Scenario: Verify DELETE `/cart/items/{id}` with empty data
    
    Given cart is empty
    When DELETE items with item id ' ' is invoked
    Then response code for DELETE items is 400
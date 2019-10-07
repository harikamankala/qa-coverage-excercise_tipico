Feature: As a user of Online shop
		I want to add items to cart
		So that I can order item of my interest

  Scenario: Verify PUT `/cart/items/{id}` adds the items to cart
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    
  Scenario: Verify PUT `/cart/items/{id}` when item is not in store
    
    Given cart is empty
    When PUT items with item id 100 is invoked
    Then response code for PUT items is 404
    
  Scenario: Verify PUT `/cart/items/{id}` when item already in cart is added again
    
    Given cart is empty
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    When PUT items with item id 9 is invoked
    Then response code for PUT items is 200
    
  Scenario: Verify PUT `/cart/items/{id}` with bad data
    
    Given cart is empty
    When PUT items with item id 'abc' is invoked
    Then response code for PUT items is 400
    
  Scenario: Verify PUT `/cart/items/{id}` with empty data
    
    Given cart is empty
    When PUT items with item id ' ' is invoked
    Then response code for PUT items is 400
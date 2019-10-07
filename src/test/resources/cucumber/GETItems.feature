Feature: As a user of Online shop
		I want to see all items
		So that I can order item of my interes

  Scenario: Verify GET /items fetches all the items
    
    When GET items is invoked
    Then response code is 200
    And response has all the valid 9 items
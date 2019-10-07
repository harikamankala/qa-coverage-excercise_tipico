Feature: the events are exposed

  Scenario: Items can be queries
    When endpoint /items is called

    Then call is successful
    And response body contains Item array of 9 elements
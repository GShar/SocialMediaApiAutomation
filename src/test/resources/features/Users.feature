Feature: Verify Get Posts in Api

  Scenario: Fetch List of All Users
    Given I have a rest service endpoint for users
    When I verify that the response schema is correct
    And I verify Users list is not empty





Feature: Verify Get Posts in Api

  @EndToEnd
  Scenario: Create a new posts
    Given I have a rest service which is available for endpoint "/posts"
    When I create a post for user with body and title and userId
      |JsonKey | JsonValue        |
      | title  | Testing Patterns |
      | body   | testing          |
      | userId | 1                |
    Then I verify that post endpoint response contains title "Testing Patterns"
    And I verify that post endpoint response contains body "testing"
    And I verify that post endpoint response contains userId "1"
    And I verify post endpoint response header content type is Json
    And I verify post endpoint response time is less than 1000 milliseconds

   # Scenario: Get All the posts

#    Scenario: As a user of the online socila media platform I would like to add a comment to my post
 #     When I create a post for  user {int} with body {string} and title {string}
  #    And I add a comment to the post





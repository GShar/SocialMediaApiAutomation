Feature: Verify Get Posts in Api

  @EndToEnd
  Scenario Outline: Create a new posts
    Given I have a rest service endpoint for posts
    When I create a post for user with body <body> and title <title> and userId 1
    Then I verify that post endpoint response contains title <expectedTitle>
    And I verify that post endpoint response contains body <expectedBody>
    And I verify that post endpoint response contains userId 1
    And I verify post endpoint response header content type is Json
    And I verify post endpoint response time is less than <seconds> milliseconds
    Examples:
      | uri      | title              | body      | seconds | expectedTitle         | expectedBody |
      | "/posts" | "Testing Patterns" | "testing" | 1000    | "Testing Patterns"    | "testing"    |

   # Scenario: Get All the posts

#    Scenario: As a user of the online socila media platform I would like to add a comment to my post
 #     When I create a post for  user {int} with body {string} and title {string}
  #    And I add a comment to the post





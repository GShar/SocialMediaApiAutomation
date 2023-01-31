Feature: Make posts using the post endpoint

  Scenario Outline: Create a new posts
    Given I have a rest service endpoint for posts
    When I create a post for user with body <body> and title <title> and userId 1
    Then I verify that post endpoint response contains title <expectedTitle>
    And I verify that post endpoint response contains body <expectedBody>
    And I verify that post endpoint response contains userId 1
    And I verify post endpoint response header content type is Json
    And I verify post endpoint response time is less than <seconds> milliseconds
    Examples:
      | title              | body                   | seconds | expectedTitle         | expectedBody           |
      | "Testing Patterns" | "testing patterns in " | 1000    | "Testing Patterns"    | "testing patterns in " |
      | "<<<<<<<<"         | "<<<<<<<<"             | 1000    | "<<<<<<<<"            | "<<<<<<<<"             |
      | "test '''ment"     | "test '''ment"         | 1000    |  "test '''ment"       |  "test '''ment"        |
      | "ambigüedad"       | "ambigüedad"           | 1000    |  "ambigüedad"         |  "ambigüedad"          |
      | "test 是"          | "test 是"               | 1000    |  "test 是"            |  "test 是"             |






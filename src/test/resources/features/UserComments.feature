Feature: Test feature add comments on Post
  Background: As a user of the online social media platform,
    I should be able to add comments to posts
    This does not break existing feature that all comments for a post can still be successfully fetched

  Scenario Outline: Add comment to a Post using the endpoint and verify the response
    The endpoint is tested with multiple test data for the following
    Its ability to deal with special characters
    Its ability to handle multiple languages such as chinese and spanish(accented characters)

    Given I have a rest service endpoint for comments
    When I comment on a post using text <comment> and postId <postId>
    Then I verify Status Code is <expectedStatusCode>
    And I verify comment endpoint response body contains text <expectedComment>
    And I verify comment endpoint response body contains postId <postId>
    And I verify comment endpoint response header Content Type is Json
    And I verify comment endpoint response time is less than <seconds> milliseconds
    Examples:
      | comment        | postId | expectedStatusCode | expectedComment | seconds |
      | "test comment" | 2      | 201                | "test comment"  | 1000    |
      | "<<<<<<<<     "| 2      | 201                | "<<<<<<<<     " | 1000    |
      | "test '''ment" | 2      | 201                | "test '''ment"  | 1000    |
      | "ambigüedad  " | 2      | 201                | "ambigüedad  "  | 1000    |
      | "test 是     " | 2      | 201                | "test 是     "   | 1000    |

  Scenario: Basic regression test to verify comments on post are still being returning successfully
    When I get all comments on a post with postId "1"
    Then I verify total 5 comments are returned
    And I verify list contains comment with email "Eliseo@gardner.biz"
    And I verify Ids in the comments are in order
      | 1 | 2| 3 | 4 | 5|
    And I verify postId is 1 in all the comments
    And I verify comment with "Eliseo@gardner.biz" contains text "laudantium enim quasi est quidem magnam voluptate"
    And I verify list contains comment with name "id labore ex et quam laborum"

  Scenario Outline: A Boundary test, assuming that the max size of comment is 100 chars
    When I comment on a post using text "<comment>" and postId 2
    Then I verify Status Code is 201
    Examples:
      | comment                                                                                              |
      | dskjfhaksjdfkajsbdfkhbaljshbfhbsjdhfbjhasbdfjhbasjdhbafhsbjdhfbshdbfhbshdbfhsbdhfbsjdhfbhsabdjfhb123 |

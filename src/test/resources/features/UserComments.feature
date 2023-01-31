Feature: Verify Get Comments in Api
  Scenario: get all comments
    Given I have a rest service endpoint for comments
    When I get all comments on a post with postId "1"
    Then I verify total 5 comments are returned
    And I verify list contains comment with email "Eliseo@gardner.biz"
    And I verify Ids in the comments are in order
      | 1 | 2| 3 | 4 | 5|
    And I verify postId is 1 in all the comments
    And I verify comment with "Eliseo@gardner.biz" contains text "laudantium enim quasi est quidem magnam voluptate"
    And I verify list contains comment with name "id labore ex et quam laborum"


  @EndToEnd
  Scenario Outline: Add a comment on Post
    When I comment on a post using text <comment> and postId <postId>
    Then I verify Status Code is <expectedStatusCode>
    And I verify comment endpoint response body contains text <expectedComment>
    And I verify comment endpoint response body contains postId <postId>
    And I verify comment endpoint response header Content Type is Json
    And I verify comment endpoint response time is less than <seconds> milliseconds
    Examples:
      | comment        | postId | expectedStatusCode | expectedComment | seconds |
      | "test comment" | 2      | 201                | "test comment"  | 1000    |

  Scenario Outline: Add a variety of comments on Post
    When I comment on a post using text "<comment>" and postId 2
    Then I verify Status Code is <status code>
    Examples:
      | comment                                                                                           | status code|
      | dskjfhaksjdfkajsbdfkhbaljshbfhbsjdhfbjhasbdfjhbasjdhbafhsbjdhfbshdbfhbshdbfhsbdhfbsjdhfbhsabdjfhb | 201        |
      | ,,,,,,,,,,,,,,,,,,,,,,,,,,,bfhbsjdhfbjhasbdfjhbasjdhbafhsbjdhfbshdbfhbshdbfhsbdhfbsjdhfbhsabdjfhba| 201        |



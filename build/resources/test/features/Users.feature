Feature: Verify Get Posts in Api

  @EndToEnd
  Scenario: Fetch List of All Users
    Given I have a rest service get all users with uri "/users"
    When I verify that the response schema is correct
    And I verify Users list is not empty
    #And I verify the user metadata is correct
    #| id  | name          | email            | username | phone                 | website      |
    #| 1   | Leanne Graham | Sincere@april.biz| Bret     | 1-770-736-8031 x56442 | hildegard.org|





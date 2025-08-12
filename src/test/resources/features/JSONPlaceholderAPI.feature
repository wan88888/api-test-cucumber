Feature: JSONPlaceholder API Testing
  As a tester
  I want to test JSONPlaceholder API endpoints
  So that I can ensure the API works correctly for posts, users, and comments

  @posts
  Scenario Outline: Validate GET posts API
    Given I hit the JSONPlaceholder posts API endpoint
    When I send a GET request to posts
    Then I should receive status code <statusCode>
    Examples:
      | statusCode |
      | 200        |

  @posts
  Scenario Outline: Validate GET single post API
    Given I hit the JSONPlaceholder posts API endpoint
    When I send a GET request to posts with id <postId>
    Then I should receive status code <statusCode>
    And the response should contain post id <postId>
    Examples:
      | postId | statusCode |
      | 1      | 200        |
      | 10     | 200        |

  @posts
  Scenario Outline: Validate POST create post API
    Given I hit the JSONPlaceholder posts API endpoint
    When I send a POST request to posts with title "<title>" and body "<body>"
    Then I should receive status code <statusCode>
    And the response should contain "title" field with value "<title>"
    Examples:
      | title        | body                    | statusCode |
      | Test Post    | This is a test post     | 201        |
      | Another Post | Another test post body  | 201        |

  @posts
  Scenario Outline: Validate PUT update post API
    Given I hit the JSONPlaceholder posts API endpoint
    When I send a PUT request to posts <postId> with title "<title>" and body "<body>"
    Then I should receive status code <statusCode>
    And the response should contain "title" field with value "<title>"
    And the response should contain post id <postId>
    Examples:
      | postId | title         | body                     | statusCode |
      | 1      | Updated Post  | This is an updated post  | 200        |
      | 5      | Modified Post | Modified post content    | 200        |

  @posts
  Scenario Outline: Validate DELETE post API
    Given I hit the JSONPlaceholder posts API endpoint
    When I send a DELETE request to posts <postId>
    Then I should receive status code <statusCode>
    Examples:
      | postId | statusCode |
      | 1      | 200        |
      | 10     | 200        |

  @users
  Scenario Outline: Validate GET users API
    Given I hit the JSONPlaceholder users API endpoint
    When I send a GET request to users
    Then I should receive status code <statusCode>
    Examples:
      | statusCode |
      | 200        |

  @users
  Scenario Outline: Validate GET single user API
    Given I hit the JSONPlaceholder users API endpoint
    When I send a GET request to users with id <userId>
    Then I should receive status code <statusCode>
    And the response should contain user name "<userName>"
    Examples:
      | userId | statusCode | userName        |
      | 1      | 200        | Leanne Graham   |
      | 2      | 200        | Ervin Howell    |

  @comments
  Scenario Outline: Validate GET comments API
    Given I hit the JSONPlaceholder comments API endpoint
    When I send a GET request to comments
    Then I should receive status code <statusCode>
    Examples:
      | statusCode |
      | 200        |

  @comments
  Scenario Outline: Validate GET comments for specific post
    Given I hit the JSONPlaceholder comments API endpoint
    When I send a GET request to comments for post <postId>
    Then I should receive status code <statusCode>
    Examples:
      | postId | statusCode |
      | 1      | 200        |
      | 5      | 200        |
Feature: Products API Testing
  As a tester
  I want to test all CRUD operations on products API
  So that I can ensure the API works correctly

  Scenario Outline: Validate GET products API
    Given I hit the url of get product api endpoint
    When I pass the url of products in the request
    Then I receive the response code as <statusCode>
    And I receive the response body with price <price> at index <index>
    Examples:
      | statusCode | price  | index |
      | 200        | 109.95 | 0     |

  Scenario Outline: Validate POST product API
    Given I hit the url of post product api endpoint
    When I pass the url of products in the request
    And I pass the request body of product title <ProductTitle>
    Then I receive the response code as <statusCode>
    And I receive the response body with id as <id>
    Examples:
      | ProductTitle | statusCode | id |
      | Shoes        | 201        | 21 |

  Scenario Outline: Validate PUT product API
    Given I hit the url of put product api endpoint
    When I pass the url of products in the request with <Productnumber>
    Then I receive the response code as <statusCode>
    Examples:
      | Productnumber | statusCode |
      | 6             | 200        |

  Scenario Outline: Validate DELETE product API
    Given I hit the url of delete product api endpoint
    When I pass the url of delete products in the request with <Productnumber>
    Then I receive the response code as <statusCode>
    Examples:
      | Productnumber | statusCode |
      | 5             | 200        |
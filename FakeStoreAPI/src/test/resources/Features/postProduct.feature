Feature: post a product

    Scenario: Validate post product api adds a product
    Given the product post API is available
    When I request add product of "newProduct.json"
    Then the response should matches to response code "200"
    And response should matches the expected product from "newProduct.json"

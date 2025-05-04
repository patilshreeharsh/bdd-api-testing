Feature: Get All Products

  Scenario: Validate product API returns expected products
    Given the product API is available
    When I request all products
    Then the response should match to response code "200"
    And the response should match the expected products from "allproducts.JSON"
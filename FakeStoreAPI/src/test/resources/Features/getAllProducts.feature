Feature: FakeStore API - Get All Products
  As an API consumer
  I want to retrieve all products from the FakeStore API
  So that I can display them in my application

  @regression @smoke
  Scenario: Successfully retrieve all products
    Given the FakeStore API is availables
    When I send a GET request to getAllProduct endpoint
    Then the response status code should be 200
    And the response should contain a list of products
    And each product should have an id, title, price, and category

Feature: Get Product by Id

    Scenario: Validate product get API returns  appropate 
    Given the get product API is available
    When I request  product having id  "1"
    Then the response should match to response code "200" and returns product object
    

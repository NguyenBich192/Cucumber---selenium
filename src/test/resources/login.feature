Feature: Login to an e-commerce website

  Scenario: Verify users can login to portal with valid credentials
    Given User visits e-commerce website
    When User enters valid credentials
    Then User can logged in successfully

